package com.ecommerce.controller;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.domain.Despesas;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/despesas")
public class DespesasController {
	Despesas despesa = new Despesas();
	BigDecimal rateio = new BigDecimal("0.00");
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/listar")
	public String listar(ModelMap model, Despesas despesa) {
		model.addAttribute("produtos", service.buscarTodos());
		return "/lista/lista";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, Despesas despesa) {
		service.excluir(id);
		model.addAttribute("success", "Produto excluído com sucesso.");
		return listar(model, despesa);
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produto", service.buscarPorId(id));
		return "/cadastro/produto";
	}
	@PostMapping("/calcula")
	public String calcula(Despesas despesa) {
		if(despesa.getDespesa() == null || despesa.getMargem() == null) {
			despesa.setDespesa(new BigDecimal("400.00"));
			despesa.setMargem(10);
		} else {
			
		}
		return "/lista/lista";
	}

}
