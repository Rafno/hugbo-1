package project.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.Users;

import project.service.UserService;

import java.io.*;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;


/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static List<String> notendaVillur = new ArrayList<String>();
	private static List<String> lykilordVillur = new ArrayList<String>();
	private static Boolean allGood;
	
	private UserDetails userDetails;
	
	private Users myUser;
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
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
						@RequestParam(value = "deleteAccount", required = false) String deleteAccount,
						Model model) {
		String errorMessage = null;
		if (error != null) {
			errorMessage = "Notendanafn eða lykilorð er ekki rétt, athugaðu að hástafir og lágstafir skipta máli.";
		}
		if (logout != null) {
			errorMessage = "Þú hefur verið útskráður";
		}
		if(deleteAccount !=null){
			errorMessage = "Aðgangi eytt";
		}
		model.addAttribute("errorMsg", errorMessage);
		// Búum til try catch sem skoðar hvort user er skráður inn
		try {
			this.userDetails =
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("loggedInn", true);
		} catch (Exception e) {
		}

		return "/Login/login";
	}
	@RequestMapping(value ="/deletemyuserrightnow", method = RequestMethod.GET)
	public RedirectView delete(){
		getUser();
		userService.delete(myUser);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/logout");
		return redirectView;
	}
	// To call this method, enter "localhost:8080/user" into a browser
	/*TODO Passa að bæjarfélag, póstnúmer og heimilisfang gildin koma aftur ef það kemur upp villa*/
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		// Búum til try catch sem skoðar hvort user er skráður inn
		try {
			this.userDetails =
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("loggedInn", true);
		} catch (Exception e) {

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
							   @RequestParam("emailAddress") String emailAddress,
							   @RequestParam("homeTown") String homeTown,
							   @RequestParam("zipCode") String zipCode
							   ) throws IOException {

		model.addAttribute("nafn", name);
		// hreinsum arrayListana
		notendaVillur.clear();
		lykilordVillur.clear();
		// kalla hér á fall sem skoðar hvort username og password séu lögleg
		allGood = true;
		getErrors(username, password, passwordRepeat, zipCode);
		if (allGood) {
			// Senda Confrimation email

			String number = sendHttp(emailAddress, name,"", "");

			model.addAttribute("succesfull", "Til hamingju " + name + ". Aðgangurinn þinn hefur verið búinn til, tölvupóstur hefur  verið sendur á póstfangið þitt til að staðfesta aðganginn.");

			//Cloudinary link
			String img = "<img src='http://res.cloudinary.com/dfhjyjyg1/image/upload/zkitbd9veqxrcdpmhnlj'/>";

			ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Atlantic/Reykjavik"));
			Users newUser = new Users(
										name,
										username,
										password,
										role,
										img,
										homeAddress,
										homeTown,
										zipCode,
										emailAddress,
										number,
										zdt.now().toLocalDate().toString(),
										OffsetDateTime.now().toEpochSecond(),
										false ,
										true
			);
			userService.save(newUser);

		}
		model.addAttribute("notendaVillur", notendaVillur);
		model.addAttribute("lykilordVillur", lykilordVillur);
		return "/Register/register";
	}
	
	public String sendDoctorHttp(String emailAddress, String docName,String patientName) throws IOException{
		String url = "https://hugbo1.herokuapp.com/doctorSend";
		String urlParameters ="to=" + emailAddress + ";SPLITER;" + docName + ";SPLITER;" + patientName;
		return sendingHttp(url, urlParameters);
	}
	public String sendHttp(String emailAddress, String name, String medicineName, String urlLocation) throws IOException {
		String url = "";
		String urlParameters ="";
		url = "https://hugbo1.herokuapp.com" + urlLocation;
		if(medicineName.length() > 0){
			urlParameters = "to=" + emailAddress + ";SPLITER;" + name + ";SPLITER;" + medicineName;
		} else {
			urlParameters = "to=" + emailAddress + ";SPLITER;" + name;
		}

		return sendingHttp(url,urlParameters);
	}
	
	private String sendingHttp(String url,String urlParameters) throws IOException{
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "IS;q=0.5");
		
		byte[] bytes = new byte[10];
		String finalUrl = new String(bytes, Charset.forName("UTF-8"));


		// Send post request
		con.setDoOutput(true);
		DataOutputStream test = new DataOutputStream(con.getOutputStream());
		OutputStreamWriter wr = new OutputStreamWriter(test, "UTF-8");
		wr.write(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		String number = response.toString().substring(response.toString().lastIndexOf(":") + 1);
		number = number.substring(0,number.length()-1);
		// þurfum að generata hash key sem er gert í servernum.
		return number;
	}
	private void getErrors(String notendanafn, String lykilord, String lykilordRepeat, String zipCode) {
		if(!StringUtils.isNumeric(zipCode)){
			lykilordVillur.add("Póstnúmer verður að vera númer");
			allGood = false;
		}
		if (!lykilord.equals(lykilordRepeat)) {
			lykilordVillur.add("Lykilorðin verða að vera eins");
			allGood = false;
		}
		String[] islenskirStafir = {"á", "Á", "ð", "Ð", "é", "É", "í", "Í", "ó", "Ó", "ú", "Ú", "ý", "Ý", "þ", "Þ", "æ", "Æ", "Ö", "ö", " "};
		for (String item : islenskirStafir) {
			if (notendanafn.contains(item)) {
				notendaVillur.add("Bil í orði eða íslenskir sérstafir eru ekki leyfðir í notendanafni");
				allGood = false;
				break;
			}
		}
		if (notendanafn.length() == 0) {
			notendaVillur.add("Notendanafn má ekki vera tómt");
			allGood = false;
		}
		if (lykilord.length() < 6) {
			lykilordVillur.add("Lengd á lykilorði verður að vera að lágmarki 6 stafir");
			allGood = false;
		}
		for (String item : islenskirStafir) {
			if (lykilord.contains(item)) {
				lykilordVillur.add("íslenskir sérstafir eru ekki leyfðir í lykilorði");
				allGood = false;
				break;
			}
		}
		for (Boolean item : userService.userNameExists(notendanafn)) {
			if (item) {
				notendaVillur.add("Notendanafn er nú þegar til");
				allGood = false;
				break;
			}
		}
	}
	public void getUser()
	{
		
		this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.myUser = userService.getUser(userDetails.getUsername());
	}
}
