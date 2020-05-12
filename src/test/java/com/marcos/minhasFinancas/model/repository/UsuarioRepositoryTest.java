package com.marcos.minhasFinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcos.minhasFinancas.model.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Test // teste de integração
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = new Usuario();
		usuario.setNome("fulano");
		usuario.setEmail("fulano@email.com");
		repository.save(usuario);
		
		//ação / execução
		boolean resultado = repository.existsByEmail("fulano@email.com");
		
		//verificação
		
		Assertions.assertThat(resultado).isTrue();
		
	}
}
