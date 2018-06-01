package com.ecommerce.domain;

import java.math.BigDecimal;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "PRODUTOS")
public class Produto extends AbstractEntity<Long> {

	@Column(name = "nome", nullable = false, unique = true, length = 50)
	private String nome;
	@Column(name = "custo")
	private BigDecimal custo;
	@Column(name = "venda")
	private BigDecimal venda;
	@Column(name = "descricao", length = 200)
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "id_carrinho_fk")
	private Carrinho carrinho;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getCusto() {
		return custo;
	}
	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
	public BigDecimal getVenda() {
		return venda;
	}
	public void setVenda(BigDecimal venda) {
		this.venda = venda;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	
	

}
