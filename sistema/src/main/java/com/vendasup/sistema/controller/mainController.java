package com.vendasup.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

	@GetMapping("/admin")
	public String acessMain() {
		return "admin/home";
	}
	
}
