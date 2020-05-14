package com.marcos.minhasFinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.minhasFinancas.exception.ErroAutenticacaoException;
import com.marcos.minhasFinancas.exception.RegraNegocioException;
import com.marcos.minhasFinancas.model.entity.Usuario;
import com.marcos.minhasFinancas.model.repository.UsuarioRepository;
import com.marcos.minhasFinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> usuario = repository.findByEmail(email);
		if (!usuario.isPresent()) { //verificar se esta presente na base de dados
			throw new ErroAutenticacaoException("Usuário não encontrado para o email informado.");
		}
		
		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacaoException("Senha invalida.");
		}
		
		return usuario.get(); //passando a instancia do usuario
	}

	@Override
	@Transactional// serve para abrir uma transacao, executa o metodo de salvar o usuario ai ele comita.
	public Usuario salvarUsuario(Usuario usuario) {
		
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe = repository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com esse email.");
		}
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		
		return repository.findById(id);
	}

}
