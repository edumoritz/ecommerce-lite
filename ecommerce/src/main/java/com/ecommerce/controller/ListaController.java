package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListaController {

	@GetMapping("/lista")
	public String lista() {
		return "/lista/lista";
	}
}
