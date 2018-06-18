package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Despesas;
import com.ecommerce.service.CarrinhoService;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ListarController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CarrinhoService carrinhoService;

	/**
	 * 
	 * 
	 * CARRINHO
	 * 
	 **/
	@PostMapping("/captura/{id}")
	public String preCaptura(@PathVariable("id") Long id, ModelMap model, 
			Carrinho carrinho, RedirectAttributes attr,
			Despesas despesa) {
		carrinhoService.calculos(id, carrinho);
		return listar(model, despesa, carrinho);
	}

	/**
	 * 
	 * 
	 * LISTAR
	 * 
	 **/
	@GetMapping("/listar")
	public String listar(ModelMap model, Despesas despesa, Carrinho carrinho) {
		carrinho.setQuantidade(1);
		model.addAttribute("produtos", produtoService.orderById());
		return "/lista/lista";
	}

	/**
	 * 
	 * 
	 * PRODUTO
	 * 
	 **/
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, Despesas despesas, Carrinho carrinho) {
		produtoService.excluir(id);
		model.addAttribute("success", "Produto excluído com sucesso.");
		return listar(model, despesas, carrinho);
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produto", produtoService.buscarPorId(id));
		return "/cadastro/produto";
	}

	/**
	 * 
	 * 
	 * DESPESA
	 * 
	 **/
	@PostMapping("/calcula")
	public String calcula(ModelMap model, Despesas despesa) {
		produtoService.calculaService(despesa);
		return "redirect:/produtos/listar";
	}

}
