package com.marcos.minhasFinancas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.minhasFinancas.exception.RegraNecocioException;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if (existe) {
			throw new RegraNecocioException("Já existe um usuário cadastrado com esse email.");
		}
	}

}
