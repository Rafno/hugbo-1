package project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	private UserDetails userDetails;

	@Autowired
	public UserController(UserService userService){this.userService = userService;}
	/*
	 * This fuction reciews path login and sends the user to the view Login/login
	 */
	// To call this method, enter "localhost:8080/user" into a browser
	
	/***
	 * Get method á login, tekur inn error messages til að skila hvort notandi sló rétt inn.
	 * Values sem eru gefin þurfa að vera breytt í login.jsp skjalinu.
	 * logout parameter virkar ekki þar sem það þarf á tengingu að halda.
	 * @param error
	 * @param logout
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "logout", required = false) String logout,
						Model model) {
		String errorMessage = null;
		if(error != null) {
			errorMessage = "Notendanafn eða lykilorð er ekki rétt, athugaðu að hástafir og lágstafir skipta máli.";
		}
		if(logout != null) {
			errorMessage = "You have been successfully logged out !!";
		}
		model.addAttribute("errorMsg", errorMessage);
		// Búum til try catch sem skoðar hvort user er skráður inn
		try{
			this.userDetails =
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("loggedInn",true);
		}catch(Exception e){
			System.out.println("er í skjali userCOntroller lína 67");
		}

		return "/Login/login";
	}
	
	// To call this method, enter "localhost:8080/user" into a browser
	/*TODO Passa að bæjarfélag, póstnúmer og heimilisfang gildin koma aftur ef það kemur upp villa*/
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){
		// Búum til try catch sem skoðar hvort user er skráður inn
		try{
			this.userDetails =
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				model.addAttribute("loggedInn",true);
		}catch(Exception e){
			System.out.println("er í skjali userControl lína 83");
		}


		return "/Register/register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(Model model, @RequestParam("username") String username,
						   				@RequestParam("password") String password,
						   				@RequestParam("passwordRepeat") String passwordRepeat,
						   				@RequestParam("name") String name,
							   			@RequestParam("role") String role,
							   			@RequestParam("homeAddress") String homeAddress,
							   			@RequestParam("homeTown") String homeTown,
							   			@RequestParam("zipCode") String zipCode){

		// TODO senda villuskilaboð
		//gefum div togunum sín value sem user sló inn


		model.addAttribute("nafn",name);
		// hreinsum arrayListana
		notendaVillur.clear();
		lykilordVillur.clear();
		// kalla hér á fall sem skoðar hvort username og password séu lögleg
		System.out.println(role);
		allGood = true;
		getErrors(username,password,passwordRepeat);
		if(allGood == true){
			model.addAttribute("succesfull","Til hamingju "+ name+ ". Aðgangurinn þinn hefur verið búinn til");
			System.out.println(name);
			System.out.println(username);
			System.out.println(password);
			System.out.println(role);
			System.out.println(homeAddress);
			System.out.println(homeTown);
			System.out.println(zipCode);

			//Cloudinary link
			String img = "<img src='http://res.cloudinary.com/dfhjyjyg1/image/upload/zkitbd9veqxrcdpmhnlj'/>";

			Users newUser = new Users(name, username, password, role,img, homeAddress, homeTown, zipCode, true);
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
