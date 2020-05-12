package com.marcos.minhasFinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.minhasFinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
