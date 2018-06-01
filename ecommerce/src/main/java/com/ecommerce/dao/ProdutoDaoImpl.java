package com.ecommerce.dao;

import org.springframework.stereotype.Repository;

import com.ecommerce.domain.Produto;

@Repository
public class ProdutoDaoImpl extends AbstractDao<Produto, Long> implements ProdutoDao{

}
