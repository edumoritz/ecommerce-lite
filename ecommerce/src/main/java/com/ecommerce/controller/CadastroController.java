package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/salvar")
	public String salvar(Produto produto) {
		service.salvar(produto);
		return "redirect:/cadastros/cadastrar";
	}


}
