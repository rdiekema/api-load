package org.diekema.skunk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rdiekema on 2/24/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class StandaloneCliApp
{

	public static void main(String[] args)
	{
		SpringApplication.run(StandaloneCliApp.class, args);
	}


}
