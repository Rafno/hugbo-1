package project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.Users;

import project.service.UserService;

import java.util.ArrayList;
import java.util.List;


/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class UserController {
	
	private UserService userService;
	private static List<String> notendaVillur = new ArrayList<String>();
	private static List<String> lykilordVillur = new ArrayList<String>();
	private static Boolean allGood;
	
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
	/* TODO Kannski eyða þessu, WebMvCConfiguration sér um þetta
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(Model model, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password){
	/*
		try{
			Users user = userService.userLogin(username,password);
			Cookie myCookie = new Cookie("User", user.getUsername());
			response.addCookie(myCookie);
			model.addAttribute("user", user);
		} catch (NullPointerException e){
			//
		}
		// If you look at the form in Medicines.jsp, you can see that we
		// reference this attribute there by the name `medicine`.
	
		return "/Login/login";
	}
	*/
	// To call this method, enter "localhost:8080/user" into a browser
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){



		return "/Register/register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(Model model, @RequestParam("username") String username,
						   				@RequestParam("password") String password,
						   				@RequestParam("passwordRepeat") String passwordRepeat,
						   				@RequestParam("name") String name){

		// TODO senda villuskilaboð
		//gefum div togunum sín value sem user sló inn
		model.addAttribute("nafn",name);
		// hreinsum arrayListana
		notendaVillur.clear();
		lykilordVillur.clear();
		// kalla hér á fall sem skoðar hvort username og password séu lögleg
		allGood = true;
		getErrors(username,password,passwordRepeat);
		if(allGood == true){
			model.addAttribute("succesfull","Til hamingju "+ name+ ". Aðgangurinn þinn hefur verið búinn til");
			Users newUser = new Users(name, username, password);
			userService.save(newUser);
		}
		model.addAttribute("notendaVillur",notendaVillur);
		model.addAttribute("lykilordVillur",lykilordVillur);
		return "/Register/register";
	}
	public void getErrors(String notendanafn, String lykilord, String lykilordRepeat){
		if(!lykilord.equals(lykilordRepeat))
		{
			lykilordVillur.add("Lykilorðin verða að vera eins");
		}
		String[] islenskirStafir = { "á", "Á", "ð", "Ð", "é", "É", "í", "Í", "ó", "Ó", "ú", "Ú", "ý", "Ý", "þ", "Þ", "æ", "Æ", "Ö", "ö"," "};
		for (String item:islenskirStafir)
		{
			if (notendanafn.contains(item))
			{
				notendaVillur.add("Bil í orði eða íslenskir sérstafir eru ekki leyfðir í notendanafni");
				allGood = false;
				break;
			}
		}
		if(notendanafn.length() == 0)
		{
			notendaVillur.add("Notendanafn má ekki vera tómt");
			allGood = false;
		}
		if(lykilord.length() < 6)
		{
			lykilordVillur.add("Lengd á lykilorði verður að vera að lágmarki 6 stafir");
			allGood = false;
		}
		for (String item:islenskirStafir)
		{
			if (lykilord.contains(item))
			{
				lykilordVillur.add("íslenskir sérstafir eru ekki leyfðir í lykilorði");
				allGood = false;
				break;
			}
		}
		for (Boolean item : userService.userNameExists(notendanafn)){
			if(item == true){
				notendaVillur.add("Notendanafn er nú þegar til");
				allGood = false;
				break;
			}
		}
	}
	
}
