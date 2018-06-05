package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.CarrinhoDao;
import com.ecommerce.domain.Carrinho;

@Service @Transactional(readOnly = false)
public class CarrinhoServiceImpl implements CarrinhoService{
	
	@Autowired
	private CarrinhoDao dao;

	@Override
	public void salvar(Carrinho carrinho) {
		dao.save(carrinho);
	}

	@Override
	public void editar(Carrinho carrinho) {
		dao.update(carrinho);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public Carrinho buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Carrinho> buscarTodos() {
		return dao.findAll();
	}

}
