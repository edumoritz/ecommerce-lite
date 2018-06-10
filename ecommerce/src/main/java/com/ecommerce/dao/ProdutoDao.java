package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.domain.Despesas;
import com.ecommerce.domain.Produto;

public interface ProdutoDao {

	void save(Produto produto);

    void update(Produto produto);

    void delete(Long id);

    Produto findById(Long id);

    List<Produto> findAll();
    
}
