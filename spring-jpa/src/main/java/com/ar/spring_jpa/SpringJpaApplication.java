package com.ar.spring_jpa;

import com.ar.spring_jpa.infraestructura.CarritoJpaRepository;
import com.ar.spring_jpa.infraestructura.ProductoJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CarritoJpaRepository carritoJpaRepository, ProductoJpaRepository productoJpaRepository){
		return args->{



		};
	}
}
