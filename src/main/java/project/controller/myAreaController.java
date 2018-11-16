package project.controller;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.*;
import project.service.*;
import com.cloudinary.Cloudinary;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
	private UserController userController = new UserController(userService);
	
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
		String role = userService.getUser(userDetails.getUsername()).getRole();
		// Interval
		int delay = 0;   // delay for 5 sec.
		int interval = 600000;  // iterate every sec.
		Timer timer = new Timer();
		
		
		Timers(interval, delay, timer);
		
		// add medicine to my home table
		userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// bæta við reminder á user

		Long userId = userService.getUser(userDetails.getUsername()).getId();
		List<Cabinet> cab = cabinetService.getMedsByUser(userId);
		if(cab.size() > 0)
		{
			List<Medicine> medicine = new ArrayList<>();
			List<ReminderMeds> reminderMeds = new ArrayList<>();
			for(int i = 0; i < cab.size(); i++)
			{
				Long medId = cab.get(i).getMedicineId();
				Medicine med = medicineService.findOne(cab.get(i).getMedicineId());

				Reminder myReminder = reminderService.getRelation(userId,medId);

				// ef ekki til bua til
				if(myReminder == null){

					myReminder = new Reminder(
						 medId,
						 userId,
						"18:00",
						"18:00",
						"18:00",
						"18:00",
						false,
						false,
						false,
						false
					);

					reminderService.save(myReminder);
				}

				Reminder newReminder = reminderService.getRelation(userId,medId);

				reminderMeds.add(i, new ReminderMeds(
													med.getName(),
													med.getPharmaceutical_form(),
													med.getStrength(),
													med.getId(),
													newReminder.getHour1(),
													newReminder.getHour2(),
													newReminder.getHour3(),
													newReminder.getHour4(),
													newReminder.getEnable1(),
													newReminder.getEnable2(),
													newReminder.getEnable3(),
													newReminder.getEnable4()
					)
				);
			}
			if(myUser.getRole() != "DOCTOR"){
				model.addAttribute("reminderMeds", reminderMeds);
			}
			
		}
		// Find role returns the appropriate column name, patients for doctors, doctors for patients.
		// If this user is a doctor, show all of their patients.
		
		model.addAttribute("image", myUser.getImagePublicId());
		Long id = this.myUser.getId();
		model.addAttribute("loggedInn", true);
		String name = findName(userDetails);
		
		model.addAttribute("name", name);
		
		
		if(role.equals("DOCTOR"))
		{
			addPatientsById(id, this.myUser.getRole(), model);
			model.addAttribute("doctorLoggadurInn", true);
			model.addAttribute("role","Sjúklingar");
		}else {
			// Doctor er ekki loggaður inn
		}


		// Lastly þarf að skoða fyrir áminingarnar því það er js function semsagt client side ef pop up glugginn er
		// opnaður svo við þurfum að halda utan um fyrir hvern user hvernig reminder hann vill fá. Líklega best að setja
		// í bobjectid sem er runnað í gegnum þau lyf sem user er að taka.

		return "myArea/myArea";
	}
	@RequestMapping(value = "/myHome/{patientId}", method = RequestMethod.GET)
	public String patientAreas(@PathVariable Long patientId, Model model) throws IOException
	{
		Users patient = userService.findOne(patientId);
		
		int delay = 0;   // delay for 5 sec.
		int interval = 600000;  // iterate every sec.
		Timer timer = new Timer();
		
		Timers(interval, delay, timer);
		// bæta við reminder á user
		List<Cabinet> cab = cabinetService.getMedsByUser(patient.getId());
		if(cab.size() > 0)
		{
			List<Medicine> medicine = new ArrayList<>();
			List<ReminderMeds> reminderMeds = new ArrayList<>();
			for(int i = 0; i < cab.size(); i++)
			{
				Long medId = cab.get(i).getMedicineId();
				Medicine med = medicineService.findOne(cab.get(i).getMedicineId());
				
				Reminder myReminder = reminderService.getRelation(patient.getId(),medId);
				
				// ef ekki til bua til
				if(myReminder == null){
					
					myReminder = new Reminder(
						medId,
						patient.getId(),
						"18:00",
						"18:00",
						"18:00",
						"18:00",
						false,
						false,
						false,
						false
					);
					
					reminderService.save(myReminder);
				}
				
				Reminder newReminder = reminderService.getRelation(patient.getId(),medId);
				
				reminderMeds.add(i, new ReminderMeds(
						med.getName(),
						med.getPharmaceutical_form(),
						med.getStrength(),
						med.getId(),
						newReminder.getHour1(),
						newReminder.getHour2(),
						newReminder.getHour3(),
						newReminder.getHour4(),
						newReminder.getEnable1(),
						newReminder.getEnable2(),
						newReminder.getEnable3(),
						newReminder.getEnable4()
					)
								);
			}
			
			model.addAttribute("reminderMeds", reminderMeds);
			
		}
		// Find role returns the appropriate column name, patients for doctors, doctors for patients.
		// If this user is a doctor, show all of their patients.
		model.addAttribute("image", patient.getImagePublicId());
		
		return "myArea/myArea";
	}
	
	private void Timers(int interval, int delay, Timer timer)
	{
		timer.scheduleAtFixedRate(new TimerTask()
		{
			public void run() {
				//Hér þarf að skoða Db inn.
				
				List<Reminder> amining = reminderService.findAll();
				for (Reminder item : amining) {
					LocalTime localTime1 = LocalTime.parse(item.getHour1(), DateTimeFormatter.ofPattern("HH:mm"));
					LocalTime localTime2 = LocalTime.parse(item.getHour2(), DateTimeFormatter.ofPattern("HH:mm"));
					LocalTime localTime3 = LocalTime.parse(item.getHour3(), DateTimeFormatter.ofPattern("HH:mm"));
					LocalTime localTime4 = LocalTime.parse(item.getHour4(), DateTimeFormatter.ofPattern("HH:mm"));
					String ids = item.getUsersId().toString();
					
					// Checks if our hour is valid to Iceland, then sends the email to that our user.
					if(assertHour(localTime1)) setEmail(item);
					if(assertHour(localTime2)) setEmail(item);
					if(assertHour(localTime3)) setEmail(item);
					if(assertHour(localTime4)) setEmail(item);
					
				}
			}
		}, delay, interval);
	}
	
	/**
	 * Assert hour accepts a time, makes sure that it is valid and returns true.
	 * @param time Time value for our timezone.
	 * @return a boolean value if the time is correct
	 */
	private boolean assertHour(LocalTime time){
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Atlantic/Reykjavik"));
		LocalTime current_time = zdt.toLocalTime();
		if (time.getHour() == current_time.getHour() && Math.abs(time.getMinute() - current_time.getMinute()) < 10) {
			return true;
		}
		return false;
	}
	
	/**
	 *	Accepts an item and sets up an email service for that user.
	 * @param item reminder item from entity.
	 */
	private void setEmail(Reminder item){
		try {
			userController.sendHttp(myUser.getEmail(),
				userService.findOne(item.getUsersId()).getName(),
				medicineService.findOne(item.getMedicineId()).getName(),"/reminder");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String findName(UserDetails userDetails)
	{
		return userService.getUsersByUsername(userDetails.getUsername()).getName();
	}
	
	private void addPatientsById(Long id, String role, Model model)
	{
		if(role.matches("DOCTOR"))
		{
			model.addAttribute("patients", userService.findAllPatients(id));
		}

	}
	
	private boolean findRole(String role)
	{
		if(role.matches("DOCTOR")){
			return true;
		}
		 else return false;
		
	}
	
	@RequestMapping(value = "/myHome", method = RequestMethod.POST)
	public String myAreasPost(Model model,
							  @RequestParam("pic") MultipartFile file) throws IOException
	{
		// load image to cloudinary
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		
		String img = (String) cloudinary.url().imageTag((String) uploadResult.get("public_id"));
		
		userService.updateImageId(img, userDetails.getUsername());
		
		
		model.addAttribute("image", img); model.addAttribute("loggedInn", true);
		String role = userService.getUser(userDetails.getUsername()).getRole();
		if(role.equals("DOCTOR"))
		{
			model.addAttribute("doctorLoggadurInn", true);
		}else {
			// Doctor er ekki loggaður inn
		}
		return "myArea/myArea";
	}
	@RequestMapping(value = "/myhome", method = RequestMethod.POST)
	public RedirectView myAreaPut(Model model,
								  @RequestParam(value = "time1", required = false) String time1,
								  @RequestParam(value = "buttonFyrst", required = false) String buttonFyrst,
								  @RequestParam(value = "buttonSeckond", required = false) String buttonSeckond,
								  @RequestParam(value = "time2", required = false) String time2,
								  @RequestParam(value = "buttonThird", required = false) String buttonThird,
								  @RequestParam(value = "time3", required = false) String time3,
								  @RequestParam(value = "buttonFourth", required = false) String buttonFourth,
								  @RequestParam(value = "time4", required = false) String time4,
								  @RequestParam(value = "medicineId", required = false) Long medId)
	{

		// Time 1
		if(medId != null) {
			boolean enable1 = false, enable2 = false, enable3 = false, enable4 = false;
			if (buttonFyrst.equals("Staðfesta")) enable1 = true;
			if (buttonSeckond.equals("Staðfesta")) enable2 = true;
			if (buttonThird.equals("Staðfesta")) enable3 = true;
			if (buttonFourth.equals("Staðfesta")) enable4 = true;
			
			this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Long userId = userService.getUser(userDetails.getUsername()).getId();
			Long reminderID = reminderService.getRelation(userId, medId).getId();
		}

		//Redirect
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/myHome");
		return redirectView;
	}
	
	public void getUser()
	{
		
		this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.myUser = userService.getUser(userDetails.getUsername());
	}

}
