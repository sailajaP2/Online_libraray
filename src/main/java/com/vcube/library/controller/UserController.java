package com.vcube.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.vcube.library.Entity.Books;
import com.vcube.library.Entity.User;
import com.vcube.library.service.BookService;
import com.vcube.library.service.Userservice;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes
public class UserController {
	@Autowired
	private Userservice serv;
	@Autowired
	public HttpSession session;
	@Autowired
	private BookService bookserv;

	@GetMapping("//")
	public String getpage() {
		return "UserLogin";
	}

	@PostMapping("/login")
	@ResponseBody
	public Map<String, String> loginUser(@RequestBody Map<String, String> loginData) {
		String email = loginData.get("email");
		String password = loginData.get("password");

		Map<String, String> response = new HashMap<>();
		try {
			if ("sudhakarreddy55@gmail.com".equalsIgnoreCase(email) && "123456".equals(password)) {
				response.put("status", "admin");
			} else {
				User res = serv.login(email, password);
				if (res != null && res.getEmail().equalsIgnoreCase(email) && res.getPassword().equals(password)) {
					session.setAttribute("userdetails", res);
					response.put("status", "user");
				} else {
					response.put("status", "fail");
				}
			}
		} catch (Exception e) {
			response.put("status", "fail");
		}
		return response;
	}

	@GetMapping("/adminfirstsite")
	public String GotoAdmin(Model model) {
		return "Adminpage";
	}

	@GetMapping("/Userhome")
	public String usersession(Model model) {

		return "UserHome";
	}
//to get useer home

	@GetMapping("/home")
	public String getHome() {
		return "UserHome";
	}

//	to logout user 
	@GetMapping("/logoutuser")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

//	to save user details after he edit 
	@PostMapping("/saveafteredit")
	public String Registeruser(@ModelAttribute User user) {
		User resdata = serv.saveUser(user);
		session.removeAttribute("userdetails");
		session.setAttribute("userdetails", resdata);
		return "redirect:/home";
	}

//	to save User details 

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
		Map<String, Object> response = new HashMap<>();
		try {
			User resData = serv.saveUser(user);
			if (resData != null) {
				session.setAttribute("userdetails", resData);
				response.put("success", true);
				response.put("message", "Registration successful!");
			} else {
				response.put("success", false);
				response.put("message", "User could not be registered.");
			}
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "An error occurred: " + e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

//	 to get register page to user  

	@GetMapping("/user_register")
	public String getregsiterform() {
		return "Registerform";
	}

//	 to show user details for edit 
	@GetMapping("/updateuser")
	public String Updateuser(Model model) {

		User upuser = (User) session.getAttribute("userdetails");
		User userByid = serv.getUserByid(upuser.getId());
		model.addAttribute("resuser", userByid);
		return "EditUser";
	}

//	 to show the all available book to user book 

	@GetMapping("/userbooks")
	public String userbooks(Model model) {
		List<Books> all = bookserv.getAll();
		model.addAttribute("allbooks", all);
		return "Useravailablebooks";
	}

//	 to add user book to his cart 

	@RequestMapping("/addtomybook/{id}")
	public String addtolist(@PathVariable("id") int id) {
		Books book = bookserv.getBookbyId(id);
		User user = (User) session.getAttribute("userdetails");
		User userByid = serv.getUserByid(user.getId());
		Set<Books> li = userByid.getListofbooks();
		li.add(book);
		user.setListofbooks(li);
		User user2 = serv.saveUser(user);
		session.removeAttribute("userdetails");
		session.setAttribute("userdetails", user2);
		return "redirect:/mybook";

	}

//	 show user whistlelist book 

	@GetMapping("/mybook")
	public String mybook(Model model) {
		User user = (User) session.getAttribute("userdetails");
		User userByid = serv.getUserByid(user.getId());
		Set<Books> books = userByid.getListofbooks();
		model.addAttribute("mybooks", books);
		return "MyBooks";

	}

//	to remove user whishlist books

	@RequestMapping("/deletemybook/{id}")
	public String deletelistbook(@PathVariable("id") int id) {
		User user = (User) session.getAttribute("userdetails");
		User userByid = serv.getUserByid(user.getId());

		Set<Books> list = userByid.getListofbooks();
		for (Books b1 : list) {

			if (b1.getId() == id) {
				list.remove(b1);
			}
		}
		User user3 = serv.saveUser(user);
		session.removeAttribute("userdetails");
		session.setAttribute("userdetails", user3);

		return "redirect:/mybook";
	}

//	to show avilable books to admin

	@GetMapping("adminavailablebooks")
	public String AdminBooks(Model model) {
		List<Books> all = bookserv.getAll();
		model.addAttribute("allbooks", all);
		return "AdminAvailablebooks";
	}

//	 to see total book 

	@GetMapping("/showbookbyid/{id}")
	public ModelAndView showlinkbyid(@PathVariable("id") int id) {
		Books book = bookserv.getBookbyId(id);
		String booklink = book.getBooklink();
		ModelAndView view = new ModelAndView("Showbook");
		view.addObject("booklink", booklink);
		return view;
	}

	@GetMapping("/adminshowbookbyid/{id}")
	public ModelAndView adminshowlinkbyid(@PathVariable("id") int id) {
		Books book = bookserv.getBookbyId(id);
		String booklink = book.getBooklink();
		ModelAndView view = new ModelAndView("Adminpopup");
		view.addObject("booklink", booklink);
		return view;
	}

//	 get inactive user

	@GetMapping("/getInactiveUsers")
	public String getInactiveUsers(Model model) {
		model.addAttribute("allinactive", serv.getInactiveUsers());
		return "ShowAllinActiveusers.html";
	}

//	 get all users who are active 
	@GetMapping("getallusers")
	public String AllUsers(Model model) {
		List<User> allusers = serv.getallUsers();
		model.addAttribute("alluser", allusers);
		return "Allusersinfo";
	}

//	inactive user byid 
	@RequestMapping("/deleteUserByid/{id}")
	public String deleteUserByid(@PathVariable("id") int id) {
		
		serv.deleteuserByid(id);
		return "redirect:/getallusers";
	}

//	active user by id 
	@GetMapping("/getuserbyidtoactive/{id}")
	public String changeStatusToActive(@PathVariable("id") int id) {
		serv.activetheinactiveuser(id);
		return "redirect:/getInactiveUsers";
	}
//	user to delete completely

	@GetMapping("/getuserbyidtodelete/{id}")
	public String deleteuser(@PathVariable("id") int id) {
		serv.deleteuser(id);
		return "redirect:/getInactiveUsers";
	}
	@GetMapping("/getuserdetailstoshow")
	public String getUserDetails(Model model) {
	
		User user = (User) session.getAttribute("userdetails");
		User userByid = serv.getUserByid(user.getId());
		model.addAttribute("user", userByid);
		return "SHowUserProfile";

	}

}
