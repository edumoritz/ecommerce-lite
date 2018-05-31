package com.ecommerce.model;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class Produto {
	
	private String nome;
	private BigDecimal custo;
	private BigDecimal venda;
	private String descricao;
	private BufferedImage foto;
	
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
	public BufferedImage getFoto() {
		return foto;
	}
	public void setFoto(BufferedImage foto) {
		this.foto = foto;
	}
	

	
	

}
