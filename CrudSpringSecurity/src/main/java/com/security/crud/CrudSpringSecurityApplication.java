package com.security.crud;

import com.security.crud.model.*;
import com.security.crud.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CrudSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			Permission create = Permission.builder()
					.name(PermissionEnum.CREATE)
					.build();
			Permission read = Permission.builder()
					.name(PermissionEnum.READ)
					.build();
			Permission update = Permission.builder()
					.name(PermissionEnum.UPDATE)
					.build();
			Permission delete = Permission.builder()
					.name(PermissionEnum.DELETE)
					.build();

			Role admin = Role.builder()
					.name(RoleEnum.ADMIN)
					.permissions(Set.of(read,create,update,delete))
					.build();
			Role user = Role.builder()
					.name(RoleEnum.USER)
					.permissions(Set.of(create,read))
					.build();

			User adminUser = User.builder()
					.username("alan")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roles(Set.of(admin))
					.build();

			User normalUser = User.builder()
					.username("user1234")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roles(Set.of(user))
					.build();

			userRepository.saveAll(List.of(adminUser,normalUser));
		};
	}
}
