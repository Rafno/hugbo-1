package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.service.StringManipulationService;


/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class LoginController {
	/*
	 * This fuction reciews path login and sends the user to the view Login/login
	 */
	// To call this method, enter "localhost:8080/user" into a browser
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		return "Login/login";
	}
}
