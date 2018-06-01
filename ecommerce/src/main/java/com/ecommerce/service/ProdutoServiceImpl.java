package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.ProdutoDao;
import com.ecommerce.domain.Produto;

@Service @Transactional(readOnly = false)
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoDao dao;
	

	public void salvar(Produto produto) {
		dao.save(produto);		
	}

	public void editar(Produto produto) {
		dao.update(produto);		
	}

	public void excluir(Long id) {
		dao.delete(id);		
	}

	@Transactional(readOnly = true)
	public Produto buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Produto> buscarTodos() {
		return dao.findAll();
	}

}
