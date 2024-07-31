package com.ar.spring_boot_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
//@ComponentScan(basePackages={"com.ar.spring_boot_01.others"})
public class SpringBoot01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot01Application.class, args);
	}

}
