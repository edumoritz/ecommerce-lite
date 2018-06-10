package com.ecommerce.service;

import java.util.List;

import com.ecommerce.domain.Despesas;
import com.ecommerce.domain.Produto;

public interface ProdutoService {

	void salvar(Produto produto);
	
	void editar(Produto produto);
	
	void excluir(Long id);
	
	Produto buscarPorId(Long id);
	
	List<Produto> buscarTodos();
	
	void calculaService(Despesas despesa);
}
