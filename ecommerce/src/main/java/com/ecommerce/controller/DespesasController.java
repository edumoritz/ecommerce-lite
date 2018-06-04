package com.ecommerce.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Despesas;
import com.ecommerce.domain.Produto;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/despesas")
public class DespesasController {
	Despesas despesa = new Despesas();
	Produto produto = new Produto();
	Carrinho cart = new Carrinho();
	BigDecimal rateio = new BigDecimal("0.00");
	BigDecimal result;

	@Autowired
	private ProdutoService service;

	@GetMapping("/listar")
	public String listar(ModelMap model, Despesas despesa) {
		model.addAttribute("produtos", service.buscarTodos());
		return "/lista/lista";
	}
	
	/**
	 * 
	 * 
	 * PRODUTO
	 * 
	 * **/
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, Despesas despesas) {
		service.excluir(id);
		model.addAttribute("success", "Produto excluído com sucesso.");
		return listar(model, despesa);
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produto", service.buscarPorId(id));
		return "/cadastro/produto";
	}

	
	/**
	 * 
	 * 
	 * DESPESA
	 * 
	 * **/
	@PostMapping("/calcula")
	public String calcula(ModelMap model, Despesas despesa) {
		BigDecimal m = new BigDecimal("0.10");
		BigDecimal d = new BigDecimal("400.00");
		if(despesa.getDespesa() == null) {
			if(despesa.getMargem() == null) {
				despesa.setDespesa(d);
				despesa.setMargem(m);
				calculaA(despesa);
			} else {
				calculaA(despesa);
			}
		} else {
			if(despesa.getMargem() == null) {
				d = despesa.getDespesa();
				despesa.setMargem(m);
				calculaA(despesa);
			} else {
				m = despesa.getMargem();
				calculaA(despesa);
			}
		}
		
		return "redirect:/despesas/listar";
	}
	
	

	private void calculaA(Despesas despesa) {
		int count = 0;
		List<ProdutoService> lista = new ArrayList<>();
		for (long i = 1; i <= service.buscarTodos().size(); i++) {
			
			if (!service.buscarPorId(i).getCusto().equals(new BigDecimal("0.00"))) {
				count++;
			} 
		}
		//BigDecimal convertDespesa = new BigDecimal(despesa.getDespesa());
		System.out.println("Count: "+ count+" Despesa: "+despesa.getDespesa());
		
		//BigDecimal divisao = convertDespesa.divide(new BigDecimal(count));
		rateio = despesa.getDespesa().setScale(0, RoundingMode.HALF_EVEN);
		
		rateio = rateio.divide(new BigDecimal(count)).setScale(0, RoundingMode.HALF_EVEN);
		System.out.println("Rateio convertido: "+ rateio);
		
	
		for (long i = 1; i <= service.buscarTodos().size(); i++) {
			if (!service.buscarPorId(i).getCusto().equals(new BigDecimal("0.00"))) {
				result = service.buscarPorId(i).getCusto().add(rateio).multiply((despesa.getMargem().add(new BigDecimal("1"))));
				service.buscarPorId(i).setVenda(result);
				service.editar(service.buscarPorId(i));
			} 
		}
	}
	

}
