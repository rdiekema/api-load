package org.diekema.skunk.components.async;

import org.diekema.skunk.components.AuthenticationProvider;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by rdiekema on 2/24/2016.
 */
public class BatchApiRequest implements Callable<Batch>
{
	private String host;
	private String action;
	private Batch batch;
	private RestTemplate restTemplate;
	private AuthenticationProvider authenticationProvider;
	private int retryCount = 3;

	public BatchApiRequest(String host, String action, Batch batch, RestTemplate restTemplate, AuthenticationProvider authenticationProvider)
	{
		this.host = host;
		this.action = action;
		this.batch = batch;
		this.restTemplate = restTemplate;
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public Batch call() throws Exception
	{
		Date start = new Date();
		while (true)
		{
			try
			{
				HttpEntity<String> request = new HttpEntity<>(batch.getPayload(), authenticationProvider.getHeaders());
				ResponseEntity<String> response = restTemplate.exchange(host + action, HttpMethod.POST, request, String.class);
				if (response.getStatusCode().is2xxSuccessful())
				{
					break;
				}
			}
			catch (RestClientException e)
			{
				authenticationProvider.authenticate();
			}
		}

		Date end = new Date();
		batch.setTime(end.getTime() - start.getTime());
		return batch;
	}
}
