package com.ecommerce.domain;

import java.math.BigDecimal;


public class Despesas extends AbstractEntity<Long> {
	
	
	private BigDecimal despesa;
	private BigDecimal margem;
	
	
	public BigDecimal getDespesa() {
		return despesa;
	}
	public void setDespesa(BigDecimal despesa) {
		this.despesa = despesa;
	}
	public BigDecimal getMargem() {
		return margem;
	}
	public void setMargem(BigDecimal margem) {
		this.margem = margem;
	}
	
}
