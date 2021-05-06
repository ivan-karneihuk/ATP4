package main.java.Vitebsk.ATP4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping("/")
	public String home(Model model) 
	{
		model.addAttribute("title", "Главная страница");
		return "home";
	}




	@GetMapping("/services")
	public String services(Model model) 
	{
		model.addAttribute("title", "Главная страница");
		return "services";
	}

	@GetMapping("/shareholders")
	public String shareholders(Model model) 
	{
		model.addAttribute("title", "Главная страница");
		return "shareholders";
	}

	@GetMapping("/job")
	public String job(Model model) 
	{
		model.addAttribute("title", "Главная страница");
		return "job";
	}

	@GetMapping("/about_us")
	public String about_us(Model model) 
	{
		model.addAttribute("title", "Главная страница"); 
		return "about_us";
	}

}