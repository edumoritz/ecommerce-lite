package com.ecommerce.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


public class Despesas {
	
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
