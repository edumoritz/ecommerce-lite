package com.ecommerce.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.CarrinhoDao;
import com.ecommerce.domain.Carrinho;

@Service @Transactional(readOnly = false)
public class CarrinhoServiceImpl implements CarrinhoService{
	
	BigDecimal valor;
	BigDecimal multiply;
	String quantidade;
	
	@Autowired
	private ProdutoService produtoService;
	
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
	
	public void calculos(Long id, Carrinho carrinho) {
		quantidade = carrinho.getQuantidade().toString();
		valor = produtoService.buscarPorId(id).getVenda().setScale(2, RoundingMode.HALF_EVEN);
		multiply = valor.multiply(new BigDecimal(quantidade));
		carrinho.setResult(multiply, quantidade);
	}

}
