package com.ecommerce.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class Carrinho {

	private Produto produto;
	private Integer quantidade;
	private Double total;
	
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
}
