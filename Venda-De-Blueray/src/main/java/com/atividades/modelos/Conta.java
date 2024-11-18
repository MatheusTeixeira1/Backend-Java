package com.atividades.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Boolean isAdm;
	private String imagem;
	
	public Conta() {
		super();
	}
	public Conta(String nome, String email, String senha, Boolean isAdm, String imagem) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.isAdm = isAdm;
		this.imagem = imagem;
	}
	public Conta(Long id, String nome, String email, String senha, Boolean isAdm, String imagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.isAdm = isAdm;
		this.imagem = imagem;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getIsAdm() {
		return isAdm;
	}
	public void setIsAdm(Boolean isAdm) {
		this.isAdm = isAdm;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
