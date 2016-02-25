package org.diekema.skunk.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.beanio.builder.CsvParserBuilder;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.ParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.diekema.skunk.components.async.Batch;
import org.diekema.skunk.components.async.BatchApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by rdiekema on 2/24/16.
 */
@Component
public class ApiLoadCommandLineRunner implements ApplicationRunner
{
	static final String RECORD_NAME = "testFile";
	static final String ENDPOINT = "host";
	static final String FILE_OPTION = "file";
	static final String COUNT_OPTION = "count";
	static final String THREADS = "threads";
	Logger log = Logger.getLogger(ApiLoadCommandLineRunner.class);

	@Autowired
	AuthenticationProvider authenticationProvider;

	XmlMapper xmlMapper = new XmlMapper();
	ObjectMapper jsonMapper = new ObjectMapper();

	StreamFactory streamFactory = StreamFactory.newInstance();

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception
	{
		if (applicationArguments.containsOption(FILE_OPTION) && applicationArguments.containsOption(COUNT_OPTION))
		{
			// Continue
			File file = new File(applicationArguments.getOptionValues(FILE_OPTION).get(0));
			Integer count = Integer.valueOf(applicationArguments.getOptionValues(COUNT_OPTION).get(0));

			log.info("Processing file: " + file.getAbsolutePath());
			if (file.exists())
			{
				if (count > 0)
				{
					ParserBuilder parserBuilder = new DelimitedParserBuilder(',').recordTerminator("\r\n");
					StreamBuilder streamBuilder = new StreamBuilder(RECORD_NAME).format("delimited").parser(parserBuilder);

					streamBuilder.addRecord(CsvEntity.class);
					streamFactory.define(streamBuilder);
					BeanReader beanReader = streamFactory.createReader(RECORD_NAME, new FileReader(file));

					List<Entity> entityList = new ArrayList<>();
					Object record = null;
					while ((record = beanReader.read()) != null)
					{
						entityList.add(new Entity((CsvEntity) record));
					}

					List<List<Entity>> entityBatches = Lists.partition(entityList, count);

					List<String> payloads = new ArrayList<>();
					for (List<Entity> batch : entityBatches)
					{
						Entities entities = new Entities();
						entities.Entity = batch;
						payloads.add(xmlMapper.writeValueAsString(entities));
					}

					RestTemplate restTemplate = new RestTemplate();
					final ExecutorService pool = Executors.newFixedThreadPool(applicationArguments.containsOption(THREADS) ? Integer.valueOf(applicationArguments.getOptionValues(THREADS).get(0)) : Runtime.getRuntime().availableProcessors());
					final ExecutorCompletionService<Batch> completionService = new ExecutorCompletionService<>(pool);

					final String apiHost = applicationArguments.getOptionValues(ENDPOINT).get(0);
					authenticationProvider.setENDPOINT(apiHost);
					authenticationProvider.authenticate();
					long batchId = 0;
					for (String payload : payloads)
					{
						Map<String, String> jsonMap = new HashMap<>();
						jsonMap.put("entityList", payload);
						String jsonPayload = jsonMapper.writeValueAsString(jsonMap);
						completionService.submit(new BatchApiRequest(apiHost, ApiEndpoints.SCORER_SCORE, new Batch(batchId, jsonPayload), restTemplate, authenticationProvider));
						batchId++;
					}

					for (int i = 0; i < payloads.size(); i++)
					{
						final Future<Batch> future = completionService.take();
						try
						{
							Batch batch = future.get();
							log.info("Batch " + batch.getBatchId() + " completed in : " + batch.getTime() + "ms");
						}
						catch (ExecutionException e)
						{
							log.error(e.getMessage());
						}

					}

					System.exit(0);
				}
				else
				{
					log.error("Invalid count specified.");
				}
			}
			else
			{
				log.error("Specified File does not exist.");
			}

		}
		else
		{
			if (!applicationArguments.containsOption(ENDPOINT))
			{
				log.error("No host provided.");
			}

			if (!applicationArguments.containsOption(FILE_OPTION))
			{
				log.error("No file path provided.");
			}

			if (!applicationArguments.containsOption(COUNT_OPTION))
			{
				log.error("No count option provided");
			}

			System.out.println("Usage:\n\tjava -jar <jar> --file=<path> --count=<count>\n");
		}
	}
}
