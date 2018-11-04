package project.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorController implements ErrorViewResolver
{
	//TODO, this does absolutely nothing.
	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request,
										 HttpStatus status, Map<String, Object> model) {
		
		ModelAndView errorPage = new ModelAndView("errorPage");
		String errorMsg = "";
		
				errorMsg = "Http Error Code: 500. Internal Server Error";
				
			
		
		errorPage.addObject("errorMsg", errorMsg);
		return errorPage;
	}
	
	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest
			.getAttribute("javax.servlet.error.status_code");
	}
}
