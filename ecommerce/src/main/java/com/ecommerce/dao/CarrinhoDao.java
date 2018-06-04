package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Produto;

public interface CarrinhoDao {

	void save(Carrinho carrinho );

    void update(Carrinho carrinho);

    void delete(Long id);

    Carrinho findById(Long id);

    List<Carrinho> findAll();
}
