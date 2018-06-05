package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.domain.Carrinho;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(Carrinho carrinho) {
		return "/home";
	}


}
