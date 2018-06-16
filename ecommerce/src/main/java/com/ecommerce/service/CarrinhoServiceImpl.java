package com.ecommerce.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.domain.Carrinho;

@Service @Transactional(readOnly = false)
public class CarrinhoServiceImpl implements CarrinhoService {

	BigDecimal valorVenda;
	BigDecimal multiply;
	BigDecimal sum = new BigDecimal("0.00");
	String quantidade;
	
	List<BigDecimal> list = new ArrayList<BigDecimal>();

	@Autowired ProdutoService produtoService;

	
	public void addProduto(BigDecimal p){
		list.add(p);
    }
	
	public void deleteProduto(BigDecimal p){
		list.clear();
    }

    public List<BigDecimal> getProduto () {
        return list;
    }


	public void calculos(Long id, Carrinho carrinho) {
		
		quantidade = carrinho.getQuantidade().toString();
		valorVenda = produtoService.buscarPorId(id).getVenda().setScale(2, RoundingMode.HALF_EVEN);
		multiply = valorVenda.multiply(new BigDecimal(quantidade));
		addProduto(multiply);
		
		
		for (BigDecimal k: list) 
			sum = sum.add(k);
		
		carrinho.setResult(quantidade, multiply, sum);
		sum = new BigDecimal("0.00");
	}

}
