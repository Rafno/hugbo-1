package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.Users;
import project.service.StringManipulationService;
import project.service.UserService;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class myAreaController {

	private UserService userService;

	@Autowired
	public myAreaController(UserService userService){this.userService = userService;}

	@RequestMapping(value = "/myHome", method = RequestMethod.GET)
	public String myAreas(){
		System.out.println("komst í control");
		// The string "Index" that is returned here is the name of the view
		// (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
		// If you change "Index" to something else, be sure you have a .jsp
		// file that has the same name
		return "myArea/myArea";
	}


}
