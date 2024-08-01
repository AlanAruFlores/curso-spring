package com.ar.spring_jpa;

import com.ar.spring_jpa.dominio.Carrito;
import com.ar.spring_jpa.dominio.Producto;
import com.ar.spring_jpa.infraestructura.CarritoJpaRepository;
import com.ar.spring_jpa.infraestructura.ProductoJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	//Inyecto un bean para ejecutar en consola y usar estos repositorios
	@Bean
	CommandLineRunner commandLineRunner(CarritoJpaRepository carritoJpaRepository, ProductoJpaRepository productoJpaRepository){
		return args->{
			Carrito carritoObtenido = carritoJpaRepository.save(new Carrito());

			//Añado productos
			productoJpaRepository.save(new Producto(null,"Coca Cola", 400.0, null));
			productoJpaRepository.save(new Producto(null,"Papas", 400.0, null));
			productoJpaRepository.save(new Producto(null,"Oreo", 400.0, null));
			productoJpaRepository.save(new Producto(null,"Saladix", 400.0, null));

			//Obtengo Productos
			Set<Producto> setProductos = productoJpaRepository.findAll().stream().collect(Collectors.toSet());
			//Actualizo ambas partes de la relacion (no solo una)
			setProductos.forEach(p->{
				p.setCarrito(carritoObtenido);
			});
			productoJpaRepository.saveAll(setProductos);
			carritoObtenido.setListProductos(setProductos);
			carritoJpaRepository.save(carritoObtenido);



		};
	}
}
