package com.ecommerce.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.domain.Produto;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/cadastros")
public class CadastroController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Produto produto) {
		return "/cadastro/produto";
	}
	
	@GetMapping("/listar")
	public String lista(ModelMap model) {
		model.addAttribute("produtos", service.buscarTodos());
		return "/lista/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produtos", service.buscarPorId(id));
		return "/lista/lista";		
	}
	
	@PostMapping("/editar")
	public String editar(Produto produto) {
		service.editar(produto);
		return "/lista/lista";		
	}


}
