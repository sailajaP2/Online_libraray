package com.vcube.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vcube.library.Entity.Books;
import com.vcube.library.Entity.User;
import com.vcube.library.service.BookService;
import com.vcube.library.service.Userservice;




@Controller
public class Bookcontroller {

	@Autowired
	private BookService serv;
	@Autowired
	private Userservice userserv;
	
	@GetMapping("/adminhome")
	public String getdata() {
		return "Adminpage";
	}

	@GetMapping("/book_register")
	public String BookRegister() {
		return "bookregister";
	}
	
	@PostMapping("/savebook")
	public String addBook(@ModelAttribute Books b) {
		serv.saveBook(b);
		return "redirect:/available_books";
	}
	@GetMapping("/available_books")
	public ModelAndView getAllBooks() {
		List<Books> res = serv.getAll();
		ModelAndView model=new ModelAndView("AdminAvailablebooks");
		model.addObject("book",res);
		return model;
	}
	@RequestMapping("/editBook/{id}")
	public ModelAndView EditBook(@PathVariable("id") int id) {
		Books b1 = serv.getBookById(id);
		ModelAndView m = new ModelAndView("Bookedit");
		m.addObject("book",b1);
		return m;
	}
	@RequestMapping("/deleteBookbyid/{id}")
	public String deletebookbyid(@PathVariable("id") int id) {
		serv.deletebyId(id);
		return "redirect:/available_books";
	}
	
	
	public Books getbyid(int id) {
		return serv.getBookbyId(id);
	}
	@GetMapping("/getAdminavailablebooks")
	public String  getAdminAllBooks(Model model) {
		List<Books> res = serv.getAll();
		System.out.println(res+"======================================");
		model.addAttribute("book", res);
		
		return "AdminAvailablebooks";
	}

	@GetMapping("/getInactiveBooks")
    public String getInactiveBooks(Model model) {
        model.addAttribute("addallinactive",serv.getInactiveBooks());
         return "ShowAllInactivebooks";
    }
	@GetMapping("/ChangeInactivttoactive/{id}")
	public String ChangeInactivetoActive(@PathVariable("id")int id) {
		serv.activateBook(id);
		return "redirect:/getInactiveBooks";
	}
	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable int id, Model model) {
	    String message = serv.deleteBook(id);
	    model.addAttribute("message", message);
	    
	    return "redirect:/getInactiveBooks"; 
	}

}
