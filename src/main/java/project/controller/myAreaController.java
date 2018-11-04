package project.controller;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.persistence.entities.*;
import project.service.*;
import com.cloudinary.Cloudinary;

import javax.transaction.Transactional;
import java.io.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Time;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class myAreaController {

	@Autowired
	private UserService userService;
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private CabinetService cabinetService;
	@Autowired
	private ReminderService reminderService;

	private UserDetails userDetails;
	private Cloudinary cloudinary;
	private StringManipulationService stringManipulationService;

	private Users myUser;

	@Autowired
	public myAreaController(UserService userService){
		// connect to cloudinary
		Map config = ObjectUtils.asMap
			(
			"cloud_name", "dfhjyjyg1",
			"api_key", "262159979451586",
			"api_secret", "seHjAkN2IxZmE2lisxYoVyiD3vk"
		);

		this.cloudinary = new Cloudinary(config);
		this.userService = userService;

	}

	@RequestMapping(value = "/myHome", method = RequestMethod.GET)
	public String myAreas(Model model){
		// The string "Index" that is returned here is the name of the view
		// (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
		// If you change "Index" to something else, be sure you have a .jsp
		// file that has the same name
		getUser();
		// Interval
		int delay = 0;   // delay for 5 sec.
		int interval = 600000;  // iterate every sec.
		Timer timer = new Timer();

		//    TEST
		String myDateString1 = "22:35:40";
		String myDateString2 = "22:40:40";
		String myDateString3 = "22:45:40";
		String myDateString4 = "23:00:40";

		reminderService.save(new Reminder(1L, 2L,  myDateString1 , myDateString2, myDateString3, myDateString4));

		ZoneId z = ZoneId.of( "Atlantic/Reykjavik" );
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.systemDefault());

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				//Hér þarf að skoða Db inn.

				List <Reminder> amining = reminderService.findAll();
				for (Reminder item:amining) {
					LocalTime current_time = zdt.toLocalTime();
					LocalTime localTime1 = LocalTime.parse(item.getHour1(), DateTimeFormatter.ofPattern("HH:mm:ss"));
					LocalTime localTime2 = LocalTime.parse(item.getHour2(), DateTimeFormatter.ofPattern("HH:mm:ss"));
					LocalTime localTime3 = LocalTime.parse(item.getHour3(), DateTimeFormatter.ofPattern("HH:mm:ss"));
					LocalTime localTime4 = LocalTime.parse(item.getHour4(), DateTimeFormatter.ofPattern("HH:mm:ss"));

					if( Math.abs(localTime1.getMinute() - current_time.getMinute()) < 10){
						System.out.println("Senda notification  : " + localTime2) ;
						System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.findOne(item.getUsersId()));
					}
					if( Math.abs(localTime2.getMinute() - current_time.getMinute()) < 10){
						System.out.println("Senda notification kl : " + localTime2);
						System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.findOne(item.getUsersId()));
					}
					if( Math.abs(localTime3.getMinute() - current_time.getMinute()) < 10){
						System.out.println("Senda notification : " + localTime3);
						System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.findOne(item.getUsersId()));
					}
					if( Math.abs(localTime4.getMinute() - current_time.getMinute()) < 10){
						System.out.println("Senda notification : " + localTime4);
						System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.findOne(item.getUsersId()));
					}
				}
			}

		}, delay, interval);
		// add medicine to my home table
		if(cabinetService.findAll().size() != 0) {
			Long userId = userService.getUser(userDetails.getUsername()).getId();
			List<Cabinet> cab = cabinetService.getMedsByUser(userId);
			List<Medicine> medicine = new ArrayList<>();
			for (int i = 0; i < cab.size(); i++) {
				medicine.add(i, medicineService.findOne(cab.get(i).getMedicineId()));
			}
			model.addAttribute("medicine", medicine);
		}

		model.addAttribute("loggedInn",true);
		model.addAttribute("image",myUser.getImagePublicId());

		return "myArea/myArea";
	}
	@RequestMapping(value = "/myHome", method = RequestMethod.POST)
	public String myAreasPost(Model model,@RequestParam("pic") MultipartFile file) throws IOException {

		// load image to cloudinary

		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

		String img = (String)cloudinary.url().imageTag((String)uploadResult.get("public_id"));

		userService.updateImageId(img,userDetails.getUsername());



		model.addAttribute("image",img);
		model.addAttribute("loggedInn",true);
		return "myArea/myArea";
	}

	public void getUser(){

		this.userDetails =
			(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getUsername());
		this.myUser = userService.getUser(userDetails.getUsername());
	}
}
