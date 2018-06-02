package com.ecommerce.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	public String salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/cadastro/produto";
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
	public String editar(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/cadastro/produto";
		}
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
