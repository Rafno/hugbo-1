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
	private StringManipulationService stringManipulationService;

	Map config;

	@Autowired
	public myAreaController(UserService userService){
		this.userService = userService;
		// connect to cloudinary
		Map config = ObjectUtils.asMap(
			"cloud_name", "dfhjyjyg1",
			"api_key", "262159979451586",
			"api_secret", "seHjAkN2IxZmE2lisxYoVyiD3vk");
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

		// load image to cloudinary
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

		// get image from cloudinary
		model.addAttribute("image",
			(String)cloudinary.url().imageTag((String)uploadResult.get("public_id")));

		return "myArea/myArea";
	}

}
