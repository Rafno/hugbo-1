package project.controller;

import com.cloudinary.utils.Base64Coder;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.persistence.entities.Users;
import project.service.StringManipulationService;
import project.service.UserService;
import com.cloudinary.Cloudinary;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;


/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class myAreaController {

	private UserService userService;
	private Cloudinary cloudinary;
	Map config;

	@Autowired
	public myAreaController(UserService userService){
		this.userService = userService;
		Map config = ObjectUtils.asMap(
			"cloud_name", "dusj3d8bs",
			"api_key", "411277645212787",
			"api_secret", "UhKNtcMgrbNrL--iBIhuF58Dxik");
		this.cloudinary = new Cloudinary(config);
	}

	@RequestMapping(value = "/myHome", method = RequestMethod.GET)
	public String myAreas(){
		// The string "Index" that is returned here is the name of the view
		// (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
		// If you change "Index" to something else, be sure you have a .jsp
		// file that has the same name
		return "myArea/myArea";
	}
	@RequestMapping(value = "/myHome", method = RequestMethod.POST)
	public String myAreasPost(Model model,@RequestParam("pic") MultipartFile file) throws IOException {
		System.err.println(String.format("String: %s", file.getBytes()));
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		System.out.println(cloudinary.url().imageTag("zqfvky2aetzjhr1yqhj8.jpg"));
		model.addAttribute("image", (String)cloudinary.url().imageTag("zqfvky2aetzjhr1yqhj8.jpg"));
		return "myArea/myArea";
	}

}
