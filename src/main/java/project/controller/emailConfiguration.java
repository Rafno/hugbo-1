package project.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class emailConfiguration {

	@RequestMapping(value = "/netfangStadfest", method = RequestMethod.GET)
	public String home(Model model){
		final String dir = System.getProperty("user.dir");
		System.out.println("current dir = " + dir);
		System.out.println("Rafnar fokkaður þér, You will never find me ");
		// The string "Index" that is returned here is the name of the view
		// (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
		// If you change "Index" to something else, be sure you have a .jsp
		// file that has the same nam

		return "Index/Index";
	}
}
