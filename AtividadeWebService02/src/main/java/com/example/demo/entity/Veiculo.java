package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String descricao;
	@NotEmpty(message="O modelo deve ser informado!")
	private String modelo;
	@NotEmpty(message="A cor deve ser informado!")
	private String cor;
	@NotEmpty(message="O preço deve ser informado!")
	private String preco;
	
	public Veiculo() {
	}
	
	public Veiculo(long id, String descricao, String modelo, String cor, String preco) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.modelo = modelo;
		this.cor = cor;
		this.preco = preco;
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
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
}
