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
public class myAreaController
{
	
	@Autowired
	private UserService userService;
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private CabinetService cabinetService;
	@Autowired
	private ReminderService reminderService;
	@Autowired
	private DoctorPatientsService doctorPatientsService;
	
	
	private UserDetails userDetails;
	private Cloudinary cloudinary;
	private StringManipulationService stringManipulationService;
	
	private Users myUser;
	
	@Autowired
	public myAreaController(UserService userService)
	{
		// connect to cloudinary
		Map config = ObjectUtils.asMap("cloud_name", "dfhjyjyg1", "api_key", "262159979451586", "api_secret", "seHjAkN2IxZmE2lisxYoVyiD3vk");
		
		this.cloudinary = new Cloudinary(config);
		this.userService = userService;
		
	}
	
	@RequestMapping(value = "/myHome", method = RequestMethod.GET)
	public String myAreas(Model model)
	{
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
		String myDateString1 = "22:53:10";
		String myDateString2 = "22:53:30";
		String myDateString3 = "22:53:47";
		String myDateString4 = "22:53:43";
		
		reminderService.save(new Reminder(1L, 87L, myDateString1, myDateString2, myDateString3, myDateString4));
		
		ZoneId z = ZoneId.of("Atlantic/Reykjavik"); ZonedDateTime zdt = ZonedDateTime.now(ZoneId.systemDefault());
		
		timer.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				//Hér þarf að skoða Db inn.
				
				List<Reminder> amining = reminderService.findAll(); for(Reminder item : amining)
			{
				LocalTime current_time = zdt.toLocalTime();
				LocalTime localTime1 = LocalTime.parse(item.getHour1(), DateTimeFormatter.ofPattern("HH:mm:ss"));
				LocalTime localTime2 = LocalTime.parse(item.getHour2(), DateTimeFormatter.ofPattern("HH:mm:ss"));
				LocalTime localTime3 = LocalTime.parse(item.getHour3(), DateTimeFormatter.ofPattern("HH:mm:ss"));
				LocalTime localTime4 = LocalTime.parse(item.getHour4(), DateTimeFormatter.ofPattern("HH:mm:ss"));
				String ids = item.getUsersId().toString();
				String testing = "87";
				System.out.println("Waiting" + localTime1);

				if(Math.abs(localTime1.getMinute() - current_time.getMinute()) < 10)
				{
					System.out.println("Senda notification  : " + localTime2);

					System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.getUsersByUsername(userService.findOne(item.getUsersId()).getName()));
					UserController a = new UserController(userService);
					try {
						a.sendHttp("helgigretargunnars.96@gmail.com", userService.getUsersByUsername(userService.findOne(item.getUsersId()).getName()),false);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} if(Math.abs(localTime2.getMinute() - current_time.getMinute()) < 10)
			{
				System.out.println("Senda notification kl : " + localTime2);
				System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.getUsersByUsername(userService.findOne(item.getUsersId()).getName()));
			} if(Math.abs(localTime3.getMinute() - current_time.getMinute()) < 10)
			{
				System.out.println("Senda notification : " + localTime3);
				System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.getUsersByUsername(userService.findOne(item.getUsersId()).getName()));
			} if(Math.abs(localTime4.getMinute() - current_time.getMinute()) < 10)
			{
				System.out.println("Senda notification : " + localTime4);
				System.out.println(medicineService.findOne(item.getMedicineId()).getName() + " Sjúklingur: " + userService.getUsersByUsername(userService.findOne(item.getUsersId()).getName()));
			}

			}
			
			}
			
		}, delay, interval);
		// add medicine to my home table
		if(cabinetService.findAll().size() != 0)
		{
			Long userId = userService.getUser(userDetails.getUsername()).getId();
			List<Cabinet> cab = cabinetService.getMedsByUser(userId);
			List<Medicine> medicine = new ArrayList<>();
			for(int i = 0; i < cab.size(); i++)
			{
				medicine.add(i, medicineService.findOne(cab.get(i).getMedicineId()));
			} model.addAttribute("medicine", medicine);
		}
		// Find role returns the appropriate column name, patients for doctors, doctors for patients.
		// If this user is a doctor, show all of their patients.
		model.addAttribute("role",findRole(this.myUser.getRole()));
		model.addAttribute("image", myUser.getImagePublicId());
		Long id = this.myUser.getId();
		model.addAttribute("loggedInn", true);
		String name = findName(userDetails);
		
		model.addAttribute("name", name);
		addPatientsOrDoctorsById(id, this.myUser.getRole(), model);
		String role = userService.getUser(userDetails.getUsername()).getRole();
		if(role.equals("DOCTOR"))
		{
			model.addAttribute("doctorLoggadurInn", true);
		}else {
			// Doctor er ekki loggaður inn
		}
		return "myArea/myArea";
	}
	
	private String findName(UserDetails userDetails)
	{
		return userService.getUsersByUsername(userDetails.getUsername());
	}
	
	private void addPatientsOrDoctorsById(Long id, String role, Model model)
	{
		if(role.matches("DOCTOR"))
		{
			model.addAttribute("patients", userService.findAllPatients(id));
		}
		else if(role.matches("USER"))
		{
			model.addAttribute("patients", userService.findDoctor(id));
		}
	}
	
	private String findRole(String role)
	{
		if(role.matches("DOCTOR")){
			role = "Sjúklingar";
		}
		else {
			role = "Læknir";
		}
		
		return role;
	}
	
	@RequestMapping(value = "/myHome", method = RequestMethod.POST)
	public String myAreasPost(Model model, @RequestParam("pic") MultipartFile file) throws IOException
	{
		
		// load image to cloudinary
		
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		
		String img = (String) cloudinary.url().imageTag((String) uploadResult.get("public_id"));
		
		userService.updateImageId(img, userDetails.getUsername());
		
		
		model.addAttribute("image", img); model.addAttribute("loggedInn", true);
		String role = userService.getUser(userDetails.getUsername()).getRole();
		if(role.equals("DOCTOR"))
		{
			System.out.println("þessi er læknir");
			model.addAttribute("doctorLoggadurInn", true);
		}else {
			// Doctor er ekki loggaður inn
		}
		return "myArea/myArea";
	}
	
	public void getUser()
	{
		
		this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.myUser = userService.getUser(userDetails.getUsername());
	}

}
