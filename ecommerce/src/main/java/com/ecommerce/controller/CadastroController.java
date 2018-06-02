package com.ecommerce.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String listar(ModelMap model) {
		model.addAttribute("produtos", service.buscarTodos());
		return "/lista/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Produto produto, RedirectAttributes attr) {
		if(produto.getCusto() == null) {
			produto.setCusto(new BigDecimal("0.00"));
			produto.setVenda(new BigDecimal("0.00"));
		} else if(produto.getVenda() == null) {
			produto.setVenda(new BigDecimal("0.00"));
		}
		service.salvar(produto);
		attr.addFlashAttribute("success", "Produto inserido com sucesso.");
		return "redirect:/cadastros/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produto", service.buscarPorId(id));
		return "/cadastro/produto";
	}

	@PostMapping("/editar")
	public String editar(Produto produto, RedirectAttributes attr) {
		service.editar(produto);
		attr.addFlashAttribute("success", "Produto editado com sucesso.");
		return "redirect:/cadastros/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("success", "Produto excluído com sucesso.");
		return listar(model);
	}

}
