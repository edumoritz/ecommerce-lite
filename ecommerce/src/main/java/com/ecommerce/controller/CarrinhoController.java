package com.ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Produto;
import com.ecommerce.service.CarrinhoService;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	
	@SuppressWarnings("unused")
	@Autowired
	private CarrinhoService service;
	
	@PostMapping("/salvar")
	public String salvar(Carrinho produto, RedirectAttributes attr) {
		service.salvar(produto);
		attr.addFlashAttribute("success", "Produto inserido com sucesso.");
		return "redirect:/cadastros/cadastrar";
	}

}
