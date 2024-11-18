package com.atividades.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividades.modelos.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
