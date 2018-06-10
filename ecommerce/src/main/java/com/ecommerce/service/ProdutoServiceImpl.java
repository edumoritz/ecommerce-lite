package com.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.ProdutoDao;
import com.ecommerce.domain.Despesas;
import com.ecommerce.domain.Produto;

@Service @Transactional(readOnly = false)
public class ProdutoServiceImpl implements ProdutoService {
	BigDecimal rateio = new BigDecimal("0.00");
	BigDecimal result;
	
	@Autowired
	private ProdutoDao dao;
	

	public void salvar(Produto produto) {
		if(produto.getCusto() == null) {
			produto.setCusto(new BigDecimal("0.00"));
		} else if(produto.getVenda() == null) {
			produto.setVenda(new BigDecimal("0.00"));
		} 
		dao.save(produto);		
	}

	public void editar(Produto produto) {
		if(produto.getCusto() == null) {
			produto.setCusto(new BigDecimal("0.00"));
		} else if(produto.getVenda() == null) {
			produto.setVenda(new BigDecimal("0.00"));
		} 
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
	
	public void calculaService(Despesas despesa) {
		BigDecimal m = new BigDecimal("0.10");
		BigDecimal d = new BigDecimal("400.00");
		if (despesa.getDespesa() == null) {
			if (despesa.getMargem() == null) {
				despesa.setDespesa(d);
				despesa.setMargem(m);
				calculaA(despesa);
			} else {
				despesa.setDespesa(d);
				calculaA(despesa);
			}
		} else {
			if (despesa.getMargem() == null) {
				despesa.setMargem(m);
				calculaA(despesa);
			} else {
				calculaA(despesa);
			}
		}
	}
	
	private void calculaA(Despesas despesa) {
		int countCusto = 0;
		int recebeId = 0;
		
		for (long i = 1; i <= buscarTodos().size(); i++) {
			recebeId = Integer.parseInt(onlyNumbers(buscarTodos().get((int) i-1).toString()));
			if (!buscarPorId((long) recebeId).getCusto().equals(new BigDecimal("0.00"))) {
				countCusto++;
			}
		}

		rateio = despesa.getDespesa();
		rateio = rateio.divide(new BigDecimal(countCusto), BigDecimal.ROUND_HALF_EVEN);

		for (long i = 1; i <= buscarTodos().size(); i++) {
			recebeId = Integer.parseInt(onlyNumbers(buscarTodos().get((int) i-1).toString()));
			if (!buscarPorId((long) recebeId).getCusto().equals(new BigDecimal("0.00"))) {
				result = buscarPorId((long) recebeId).getCusto().add(rateio)
						.multiply((despesa.getMargem().add(new BigDecimal("1"))));
				buscarPorId((long) recebeId).setVenda(result);
				editar(buscarPorId((long) recebeId));
			}
		}
	}

	public static String onlyNumbers(String str) {
		if (str != null) {
			return str.replaceAll("[^0123456789]", "");
		} else {
			return "";
		}
	}

}
