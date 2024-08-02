package com.ar.spring_jpa;

import com.ar.spring_jpa.dominio.Carrito;
import com.ar.spring_jpa.dominio.Producto;
import com.ar.spring_jpa.dominio.Usuario;
import com.ar.spring_jpa.infraestructura.CarritoJpaRepository;
import com.ar.spring_jpa.infraestructura.ProductoJpaRepository;
import com.ar.spring_jpa.infraestructura.UsuarioJpaRepository;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
public class SpringJpaApplication {

	private final UsuarioJpaRepository usuarioJpaRepository;

	public SpringJpaApplication(UsuarioJpaRepository usuarioJpaRepository) {
		this.usuarioJpaRepository = usuarioJpaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	//Inyecto un bean para ejecutar en consola y usar estos repositorios
	@Bean
	@Transactional
	CommandLineRunner commandLineRunner(CarritoJpaRepository carritoJpaRepository, ProductoJpaRepository productoJpaRepository, UsuarioJpaRepository usuarioJpaRepository){
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


			//Ejecutando querys customizables
			Set<Producto> productosPorCarrito = productoJpaRepository.findAllByCarrito(carritoObtenido);
			productosPorCarrito.stream().forEach(System.out::println);


			//Agregandno usuarios

			Usuario usuario = new Usuario(null,"Alan",null);
			Usuario usuario2 = new Usuario(null,"Juan",null);
			Usuario usuario3 = new Usuario(null,"Pepe",null);
			Usuario usuario4 = new Usuario(null,"Karlos",null);

			usuarioJpaRepository.save(usuario);
			usuarioJpaRepository.save(usuario2);
			usuarioJpaRepository.save(usuario3);
			usuarioJpaRepository.save(usuario4);

			Usuario usuarioAgregado1 = usuarioJpaRepository.findById(1L).get();
			Usuario usuarioAgregado2 = usuarioJpaRepository.findById(2L).get();
			Usuario usuarioAgregado3 = usuarioJpaRepository.findById(3L).get();

			usuarioAgregado1.setUsuariosAgregados(Set.of(usuarioAgregado2,usuarioAgregado3));
			Usuario aux = usuarioJpaRepository.save(usuarioAgregado1);

			//Los muestro
			aux.getUsuariosAgregados().stream().forEach(System.out::println);

		};
	}
}
