package org.diekema.skunk.components;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by rdiekema on 2/24/16.
 */
@Component
public class ApiLoadCommandLineRunner implements ApplicationRunner {

    Logger log = Logger.getLogger(ApiLoadCommandLineRunner.class);

    static final String ENDPOINT = "host";
    static final String FILE_OPTION = "file";
    static final String COUNT_OPTION = "count";

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if(applicationArguments.containsOption(FILE_OPTION) && applicationArguments.containsOption(COUNT_OPTION)){
            // Continue
            File file = new File(applicationArguments.getOptionValues(FILE_OPTION).get(0));
            Integer count = Integer.valueOf(applicationArguments.getOptionValues(COUNT_OPTION).get(0));

            if(file.exists()){
                if(count > 0){
                      AuthenticationProvider authenicationProvider = new AuthenticationProvider(applicationArguments.getOptionValues(ENDPOINT).get(0));
                }
                else{
                    log.error("Invalid count specified.");
                }
            }
            else{
                log.error("Specified File does not exist.");
            }

        }
        else{
            if(!applicationArguments.containsOption(ENDPOINT)){
                log.error("No host provided.");
            }

            if(!applicationArguments.containsOption(FILE_OPTION)){
               log.error("No file path provided.");
            }

            if(!applicationArguments.containsOption(COUNT_OPTION)){
                log.error("No count option provided");
            }

            System.out.println("Usage:\n\tjava -jar <jar> --file=<path> --count=<count>\n");
        }
    }
}
