package com.ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.domain.Produto;
import com.ecommerce.service.CarrinhoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoService service;
	
	@PostMapping("/salvar")
	public String salvar(Produto produto) {
		return "redirect:/cadastros/cadastrar";
	}

}
