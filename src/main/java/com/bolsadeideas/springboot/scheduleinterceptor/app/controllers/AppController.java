package com.bolsadeideas.springboot.scheduleinterceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	@Value("${config.schedule.open}")
	private Integer open;
	@Value("${config.schedule.close}")
	private Integer close;

	// método de atencion del cliente, va a funcionar a travez de un rango de hora
	@GetMapping({ "/index", "/", "" })
	public String index(Model model) {
		model.addAttribute("title", "Welcome to <<the opening hours clients>>");

		return "index";
	}

	// creando metrodo handler cerrado
	@GetMapping("/closed")
	public String closed(Model model) {
		StringBuilder message = new StringBuilder();
		message.append(
				"Hi! casvgroup here. You’ve just missed us! We’ll be back in action during shop hours: Monday through Saturday, ");
		message.append(open);
		message.append("am to ");
		message.append(close);
		message.append("pm 💃");
		model.addAttribute("title", "SORRY, out of office hours");
		model.addAttribute("message", message.toString());
		return "closed";
	}
}
