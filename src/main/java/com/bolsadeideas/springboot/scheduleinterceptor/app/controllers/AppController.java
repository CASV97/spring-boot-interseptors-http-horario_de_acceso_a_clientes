package com.bolsadeideas.springboot.scheduleinterceptor.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	//método de atencion del cliente, va a funcionar a travez de un rango de hora
	@GetMapping({"/index","/",""})
	public String index(Model model) {
		model.addAttribute("title","Welcome to <<the opening hours clients>>");
		
		return "index";
	}
	}
