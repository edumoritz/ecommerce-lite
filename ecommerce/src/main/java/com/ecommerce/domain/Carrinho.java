package com.ecommerce.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "CARRINHO")
public class Carrinho extends AbstractEntity<Long> {

	@OneToMany(mappedBy = "carrinho")
	private List<Produto> produto;
	@Column(name = "quantidade")
	private Integer quantidade;
	@Column(name = "valor_produto")
	private BigDecimal valorProduto;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
	
}
