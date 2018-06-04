package com.ecommerce.service;

import java.util.List;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Produto;

public interface CarrinhoService {

	void salvar(Carrinho carrinho);

	void editar(Carrinho carrinho);

	void excluir(Long id);

	Carrinho buscarPorId(Long id);

	List<Carrinho> buscarTodos();

}
