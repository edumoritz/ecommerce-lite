package com.ecommerce.domain;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


@SuppressWarnings("serial")
public class Despesas extends AbstractEntity<Long> {
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal despesa;
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
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
