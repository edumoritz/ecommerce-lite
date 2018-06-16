package com.ecommerce.domain;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Carrinho implements Serializable {

	
	private Integer quantidade;
	private BigDecimal valor;
	private BigDecimal total;
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public void setResult(String qtd, BigDecimal vl, BigDecimal sum) {
		this.quantidade = Integer.parseInt(qtd);
		this.valor = vl;	
		this.total = sum;
	}
	
	
}
