package com.ecommerce.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "DESPESAS")
public class Despesas extends AbstractEntity<Long> {
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "despesa", columnDefinition = "DECIMAL(7,2) default 0.00")
	private BigDecimal despesa;
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "margem", columnDefinition = "DECIMAL(7,2) default 0.00")
	private Integer margem;
	
	
	public BigDecimal getDespesa() {
		return despesa;
	}
	public void setDespesa(BigDecimal despesa) {
		this.despesa = despesa;
	}
	public Integer getMargem() {
		return margem;
	}
	public void setMargem(Integer margem) {
		this.margem = margem;
	}
	
	

}
