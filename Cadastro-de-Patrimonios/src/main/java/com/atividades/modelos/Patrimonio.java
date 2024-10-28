package com.atividades.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Patrimonio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//@NotEmpty(message="O codigo deve ser informado")
	private long codBem;
	private String descricao;
	private String detalhamento;
	//@NotEmpty(message="O preço deve ser informado")
	private Double preco;
	
	public Patrimonio() {
		
	}
	
	public Patrimonio(String descricao, String detalhamento, Double preco, long codBem) {
		super();
		this.descricao = descricao;
		this.detalhamento = detalhamento;
		this.preco = preco;
		this.codBem = codBem;
	}
	
	public long getCodBem() {
		return codBem;
	}

	public void setCodBem(long codBem) {
		this.codBem = codBem;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDetalhamento() {
		return detalhamento;
	}
	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
