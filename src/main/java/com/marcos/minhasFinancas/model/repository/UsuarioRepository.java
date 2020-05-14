package com.marcos.minhasFinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.minhasFinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//	Optional<Usuario> findByEmail(String email); // aqui retorna se existe ou nao um email na base;
	
	// verificar se existe email na base de dados
	boolean existsByEmail(String email); // aqui retorna se existe ou nao um email na base;
	
	// buscar usuario por email
	Optional<Usuario> findByEmail(String email);
	
}
