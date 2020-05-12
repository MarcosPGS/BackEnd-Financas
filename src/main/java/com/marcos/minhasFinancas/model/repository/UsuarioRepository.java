package com.marcos.minhasFinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.minhasFinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
