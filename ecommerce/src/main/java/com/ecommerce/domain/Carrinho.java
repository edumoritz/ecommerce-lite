package com.ecommerce.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Entity
@Table(name = "CARRINHO")
public class Carrinho extends AbstractEntity<Long> {

	@Column(name = "quantidade")
	private Integer quantidade;
	@Column(name = "total")
	private BigDecimal total;
	
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	
	}
}
