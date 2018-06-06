package com.ecommerce.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.domain.Carrinho;
import com.ecommerce.domain.Despesas;
import com.ecommerce.domain.Produto;
import com.ecommerce.service.CarrinhoService;
import com.ecommerce.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ListarController {
	Despesas despesa = new Despesas();
	Produto produto = new Produto();
	BigDecimal rateio = new BigDecimal("0.00");
	BigDecimal result;
	BigDecimal valor;
	BigDecimal multiply;
	String quantidade;

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
	public String preCaptura(@PathVariable("id") Long id, ModelMap model, Carrinho carrinho, RedirectAttributes attr,
			Despesas despesa) {

		quantidade = carrinho.getQuantidade().toString();
		valor = produtoService.buscarPorId(id).getVenda().setScale(2, RoundingMode.HALF_EVEN);
		multiply = valor.multiply(new BigDecimal(quantidade));
		carrinho.setResult(multiply, quantidade);

		return salvando(carrinho, despesa, model);
	}

	public String salvando(Carrinho carrinho, Despesas despesa, ModelMap model) {
		System.out.println(carrinho.getQuantidade());
		System.out.println(carrinho.getTotal());
		System.out.println(carrinhoService.buscarTodos());
		carrinhoService.editar(carrinho);
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
		model.addAttribute("produtos", produtoService.buscarTodos());
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
		return listar(model, despesa, carrinho);
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
		BigDecimal m = new BigDecimal("0.10");
		BigDecimal d = new BigDecimal("400.00");
		if (despesa.getDespesa() == null) {
			if (despesa.getMargem() == null) {
				despesa.setDespesa(d);
				despesa.setMargem(m);
				calculaA(despesa);
			} else {
				despesa.setDespesa(d);
				calculaA(despesa);
			}
		} else {
			if (despesa.getMargem() == null) {
				despesa.setMargem(m);
				calculaA(despesa);
			} else {
				calculaA(despesa);
			}
		}

		return "redirect:/produtos/listar";
	}

	private void calculaA(Despesas despesa) {
		int countCusto = 0;
		int recebeId = 0;
		
		for (long i = 1; i <= produtoService.buscarTodos().size(); i++) {
			recebeId = Integer.parseInt(onlyNumbers(produtoService.buscarTodos().get((int) i-1).toString()));
			if (!produtoService.buscarPorId((long) recebeId).getCusto().equals(new BigDecimal("0.00"))) {
				countCusto++;
			}
		}

		rateio = despesa.getDespesa();
		rateio = rateio.divide(new BigDecimal(countCusto), BigDecimal.ROUND_HALF_EVEN);

		for (long i = 1; i <= produtoService.buscarTodos().size(); i++) {
			recebeId = Integer.parseInt(onlyNumbers(produtoService.buscarTodos().get((int) i-1).toString()));
			if (!produtoService.buscarPorId((long) recebeId).getCusto().equals(new BigDecimal("0.00"))) {
				result = produtoService.buscarPorId((long) recebeId).getCusto().add(rateio)
						.multiply((despesa.getMargem().add(new BigDecimal("1"))));
				produtoService.buscarPorId((long) recebeId).setVenda(result);
				produtoService.editar(produtoService.buscarPorId((long) recebeId));
			}
		}
	}

	public static String onlyNumbers(String str) {
		if (str != null) {
			return str.replaceAll("[^0123456789]", "");
		} else {
			return "";
		}
	}

}
