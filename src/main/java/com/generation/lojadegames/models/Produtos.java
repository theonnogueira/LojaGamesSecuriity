package com.generation.lojadegames.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 255)
	private String nome;

	@Positive
	private Double preco;

	@NotBlank
	@Size(min = 3, max = 255)
	private String produtora;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Produtos() {

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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getProdutora() {
		return produtora;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}

//
//© 2022 GitHub, Inc.
//Terms
//Privacy
//Security
//Status
//Docs
//Contact
//import javax.persistence.Entity;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.Size;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table(name = "tb_produtos")
//public class Produtos {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@NotBlank
//	@Size(min = 3, max = 255)
//	private String nome;
//
//	@Positive
//	private Double preco;
//
//	@NotBlank
//	@Size(min = 3, max = 255)
//	private String produtora;
//
//	@ManyToOne
//	@JsonIgnoreProperties("produto")
//	private Categoria categoria;
//
//	public Produtos() {
//
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public Double getPreco() {
//		return preco;
//	}
//
//	public void setPreco(Double preco) {
//		this.preco = preco;
//	}
//
//	public String getProdutora() {
//		return produtora;
//	}
//
//	public void setProdutora(String produtora) {
//		this.produtora = produtora;
//	}
//
//	public Categoria getCategoria() {
//		return categoria;
//	}
//
//	public void setCategoria(Categoria categoria) {
//		this.categoria = categoria;
//	}
//
//}
//
