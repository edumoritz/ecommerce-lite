package com.ecommerce.domain;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
@Entity
@Table(name = "PRODUTOS")
public class Produto extends AbstractEntity<Long> {

	@NotBlank(message = "Informe um nome.")
	@Size(min = 3, max = 50, message = "O nome do produto deve ter entre {min} e {max} caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 50)
	private String nome;
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "custo", columnDefinition = "DECIMAL(7,2) default 0.00")
	private BigDecimal custo;
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "venda", columnDefinition = "DECIMAL(7,2) default 0.00")
	private BigDecimal venda;
	@Size(min = 10, max = 200, message = "A descrição do produto deve ter entre {min} e {max} caracteres.")
	@Column(name = "descricao", length = 200)
	private String descricao;
	
	@Transient
	private MultipartFile foto;
	
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
	public MultipartFile getFoto() {
		return foto;
	}
	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}
	
	

}
