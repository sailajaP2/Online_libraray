package com.vcube.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vcube.library.Entity.Suggestbook;
import com.vcube.library.repository.Suggestrepo;
import com.vcube.library.service.Suggestservice;



@Controller
public class Suggestcontroller {

	@Autowired
	private Suggestservice serv;
	@Autowired 
	private Suggestrepo sugestrepo;
	@GetMapping("/startsuggestform")
	public String start() {
		return "Suggestform";
	}
	
	@PostMapping("/savesuggest")
	public String saveSuggest(@ModelAttribute Suggestbook book) {
		serv.addSuggestbook(book);
		return "SuggestBookbyuser";
	}
	@GetMapping("/getAllsuggests")
	public String getAllSuggestions(Model model) {
	model.addAttribute("allsuggestions",sugestrepo.findAll());
	return "ShowAllSuggestion";
	}
}
