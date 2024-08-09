package com.jpatesting.JpaTesting;

import com.jpatesting.JpaTesting.models.Usuario;
import com.jpatesting.JpaTesting.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class JpaTestingApplicationTests {

	@Autowired
	private UsuarioRepository jpaRepository;

	@Test
	@Transactional
	@Rollback
	public void getListOfUsers(){
		List<Usuario> list = List.of(
				new Usuario(null,"asd","asd1234","as@mail.com", 4),
				new Usuario(null,"asd","asd1234","as@mail.com", 4),
				new Usuario(null,"asd","asd1234","as@mail.com", 4),
				new Usuario(null,"asd","asd1234","as@mail.com", 4)
				);

		jpaRepository.saveAll(list);

		Assertions.assertEquals(4, jpaRepository.count());
	}


}
