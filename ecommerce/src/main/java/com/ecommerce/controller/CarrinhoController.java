package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Produto;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	Carrinho cart = new Carrinho();
	
	
	@Autowired
	private ProdutoService produtoservice;
	
	@GetMapping("/listar")
	public String cadastrar(Carrinho carrinho) {
		//model.addAttribute("carrinho", produtoservice.buscarTodos());
		return "redirect:/despesas/listar";
	}
	
	@GetMapping("/captura/{id}")
	public String precaptura(@PathVariable("id") Long id, ModelMap model, Carrinho carrinho, RedirectAttributes attr) {
		System.out.println(cart.getQuantidade());
		return salvar(carrinho);
	}
	
	@PostMapping("/salvar")
	public String salvar(Carrinho carrinho) {
		//model.addAttribute("carrinho", cartservice.buscarTodos());
		//service.salvar(produto);
		System.out.println(cart.getQuantidade());
		//attr.addFlashAttribute("success", "Produto inserido no carrinho com sucesso.");
		return "redirect:/despesas/listar";
	}
	


}
