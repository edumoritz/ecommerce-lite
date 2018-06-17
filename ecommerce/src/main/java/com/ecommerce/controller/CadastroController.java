package com.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Produto;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/cadastros")
public class CadastroController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CadastroController.class);
	
	private Path path;

	@Autowired
	private ProdutoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Carrinho carrinho, Produto produto) {
		return "/cadastro/produto";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("produtos", service.buscarTodos());
		return "/lista/lista";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("success", "Produto excluído com sucesso.");
		return "redirect:/produtos/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produto", service.buscarPorId(id));
		return "/cadastro/produto";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Produto produto, @RequestParam("file") MultipartFile file, BindingResult result,
			RedirectAttributes attr, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "/cadastro/produto";
		}
		produto.setFoto(file);
		service.salvar(produto);

		MultipartFile prodFoto = produto.getFoto();
		
		System.out.println(prodFoto);

		// get root directory to store the image
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		
		path = Paths.get("C:/Users/eduar/git/projetoecommerce/ecommerce/src/main/resources/static/materialize/img/"
				+ produto.getId() + ".png");

		if (prodFoto != null && !prodFoto.isEmpty()) {
			try {
				// convert the image type to png
				prodFoto.transferTo(new File(path.toString()));
			} catch (IllegalStateException | IOException e) {
				// oops! something did not work as expected
				e.printStackTrace();
				throw new RuntimeException("Saving User image was not successful", e);
			}
		}
		
		attr.addFlashAttribute("success", "Produto inserido com sucesso.");
		return "redirect:/cadastros/cadastrar";

	}

	@PostMapping("/editar")
	public String editar(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/cadastro/produto";
		}
		service.editar(produto);
		attr.addFlashAttribute("success", "Produto editado com sucesso.");
		return "redirect:/cadastros/cadastrar";
	}

}
