package project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.Users;

import project.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService){this.userService = userService;}
	/*
	 * This fuction reciews path login and sends the user to the view Login/login
	 */
	// To call this method, enter "localhost:8080/user" into a browser
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		// The string "Index" that is returned here is the name of the view
		// (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
		// If you change "Index" to something else, be sure you have a .jsp
		// file that has the same name
		return "/Login/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(Model model, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password){
		Users user = userService.userLogin(username,password);
		Cookie myCookie = new Cookie("User",user.getUsername());
		response.addCookie(myCookie);
		// Add a new Medicine Note to the model for the form
		// If you look at the form in Medicines.jsp, you can see that we
		// reference this attribute there by the name `medicine`.
		model.addAttribute("user", user);

		return "/Login/login";
	}
	
	// To call this method, enter "localhost:8080/user" into a browser
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(){
		
		return "/Register/register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(Model model, @RequestParam("username") String username,
						   				@RequestParam("password") String password,
						   				@RequestParam("passwordRepeat") String passwordRepeat,
						   				@RequestParam("name") String name){
		// TODO senda tilbaka villuskilaboð
		if(!password.equals(passwordRepeat)) {
			System.out.println("Failure");
		} else
		{
			Users newUser = new Users(name, username, password);
			userService.save(newUser);
			
		}
		return "/Register/register";
	}
	
}
