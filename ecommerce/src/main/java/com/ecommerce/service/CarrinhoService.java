package com.ecommerce.service;

import java.util.List;

import com.ecommerce.domain.Carrinho;

public interface CarrinhoService {

	void salvar(Carrinho carrinho);

	void editar(Carrinho carrinho);

	void excluir(Long id);

	Carrinho buscarPorId(Long id);

	List<Carrinho> buscarTodos();

	void calculos(Long id, Carrinho carrinho);

}
