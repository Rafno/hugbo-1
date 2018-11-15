package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class emailController
{
	@Autowired
	private UserService userService;
	
	private UserDetails userDetails;
	
	@RequestMapping(value = "/netfangstadfest", method = RequestMethod.GET)
	public String email(HttpServletRequest request) throws IOException
	{
		this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Hér næ ég í ID
		System.out.println(request.getQueryString());
		// Hér þarf að bera saman Id sem server sendi og mið við hvað email notandi gerði
		//userService.confirmEmail();
		return ("email/emails");
		
	}
}
