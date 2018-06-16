package com.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.domain.Carrinho;

public interface CarrinhoService {

	void addProduto(BigDecimal p);

	void deleteProduto(BigDecimal p);

	List<BigDecimal> getProduto ();

	void calculos(Long id, Carrinho carrinho);

}
