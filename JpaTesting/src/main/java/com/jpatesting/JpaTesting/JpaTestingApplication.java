package com.jpatesting.JpaTesting;

import com.jpatesting.JpaTesting.conf.DataSourceConfiguration;
import com.jpatesting.JpaTesting.conf.EnviromentVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JpaTestingApplication {


	public static void main(String[] args) {
		EnviromentVariables env = new EnviromentVariables();

		String profile = env.getProfileEnviromentVariable();
		new SpringApplicationBuilder(DataSourceConfiguration.class)
				.profiles(profile)
				.registerShutdownHook(true).run(args);
	}

}
