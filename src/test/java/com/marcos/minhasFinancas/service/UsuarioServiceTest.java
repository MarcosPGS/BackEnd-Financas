package com.marcos.minhasFinancas.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcos.minhasFinancas.exception.RegraNegocioException;
import com.marcos.minhasFinancas.model.entity.Usuario;
import com.marcos.minhasFinancas.model.repository.UsuarioRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("teste")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService servico;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test(expected = Test.None.class)//serve para validar se o metodo esteja validando uma exceção
	public void testValidarEmail() {
		
		//cenario
		repository.deleteAll();
		
		//acao
		servico.validarEmail("email@email.com");
		
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void deverLancarErroQuandoTiverEmailCadastrado() {
		//cenario
		Usuario usuario = new Usuario();
		usuario.setEmail("email@email.com");
		usuario.setNome("Fulano");
		repository.save(usuario);
		
		//acao
		servico.validarEmail("email@email.com");
		
	}

}
