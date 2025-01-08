package com.app.SpringSecurityApp;

import com.app.SpringSecurityApp.persistence.entity.PermisionEntity;
import com.app.SpringSecurityApp.persistence.entity.RoleEnum;
import com.app.SpringSecurityApp.persistence.entity.RolesEntity;
import com.app.SpringSecurityApp.persistence.entity.UserEntity;
import com.app.SpringSecurityApp.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}


	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			//CREATE PERMISSIONS
			PermisionEntity readPermission = PermisionEntity.builder()
					.name("READ")
					.build();
			PermisionEntity writePermission = PermisionEntity.builder()
					.name("WRITE")
					.build();
			PermisionEntity updatePermission = PermisionEntity.builder()
					.name("UPDATE")
					.build();
			PermisionEntity deletePermission = PermisionEntity.builder()
					.name("DELETE")
					.build();

			PermisionEntity refactorPermission = PermisionEntity.builder()
					.name("REFACTOR")
					.build();

			//CREATE ROLES
			RolesEntity roleAdmin = RolesEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.setPermissions(Set.of(readPermission,deletePermission,writePermission,updatePermission))
					.build();

			RolesEntity roleUser = RolesEntity.builder()
					.roleEnum(RoleEnum.USER)
					.setPermissions(Set.of(readPermission,writePermission))
					.build();

			RolesEntity roleGuest = RolesEntity.builder()
					.roleEnum(RoleEnum.GUEST)
					.setPermissions(Set.of(readPermission))
					.build();

			RolesEntity roleDeveloper = RolesEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.setPermissions(Set.of(readPermission,deletePermission,writePermission,updatePermission,refactorPermission))
					.build();

			/*CREATE USERS*/
			UserEntity userAruflo = UserEntity.builder()
					.username("aruflo")
					.password(new BCryptPasswordEncoder().encode("aruflo1234"))
					.isEnabled(true)
					.accountNoExpired(true) //la cuenta no expiro, sigue activa
					.accountNoLocked(true) // la cuenta no esta bloqueada
					.credentialNoExpired(true) //no esta expirada las credenciales
					.setRoles(Set.of(roleAdmin))
					.build();


			UserEntity userFlores = UserEntity.builder()
					.username("flores")
					.password(new BCryptPasswordEncoder().encode("flores1234"))
					.isEnabled(true)
					.accountNoExpired(true) //la cuenta no expiro, sigue activa
					.accountNoLocked(true) // la cuenta no esta bloqueada
					.credentialNoExpired(true) //no esta expirada las credenciales
					.setRoles(Set.of(roleGuest))
					.build();


			userRepository.saveAll(List.of(userFlores,userAruflo));
		};
	}
}
