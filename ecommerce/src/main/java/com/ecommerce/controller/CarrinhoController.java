package com.ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	Carrinho cart = new Carrinho();
	
	@Autowired
	private CarrinhoService cartservice;
	
	@Autowired
	private ProdutoService produtoservice;
	
	@GetMapping("/captura/{id}")
	public String precaptura(@PathVariable("id") Long id, ModelMap model, Produto produto, RedirectAttributes attr) {
		model.addAttribute("produto", cartservice.buscarPorId(id));
		System.out.println(cart.getQuantidade());
		return salvar(model, produto, attr);
	}
	
	@PostMapping("/salvar")
	public String salvar(ModelMap model, Produto produto, RedirectAttributes attr) {
		model.addAttribute("produto", cartservice.buscarTodos());
		//service.salvar(produto);
		System.out.println(cart.getQuantidade());
		attr.addFlashAttribute("success", "Produto inserido no carrinho com sucesso.");
		return "/lista/lista";
	}
	
	@ModelAttribute("produtos")
	public List<Carrinho> getProdutos(){
		return cartservice.buscarTodos();
	}

}
