package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.DoctorPatients;
import project.persistence.entities.Medicine;
import project.persistence.entities.Users;
import project.service.*;
import java.util.List;



@Controller
public class DoktorController
{
	
	// Instance
	@Autowired
	StringManipulationService stringService;
	@Autowired
	MedicineService medicineService;
	@Autowired
	UserService userService;
	@Autowired
	CabinetService cabinetService;
	@Autowired
	DoctorPatientsService doctorPatientsService;
	
	List<Medicine> medicine;
	// Dependency Injection
	
	private UserDetails userDetails;
	
	
	@Autowired
	public DoktorController(StringManipulationService stringService)
	{
	
	}
	
	// Request mapping is the path that you want to map this method to
	// In this case, the mapping is the root "/", so when the project
	// is running and you enter "localhost:8080" into a browser, this
	// method is called
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public String allUsers(Model model)
	{
		List<Users> allUsers = userService.getPatients();
		model.addAttribute("users", allUsers);
		
		return "allUsers/allUsers";
	}
	
	@RequestMapping(value = "/allusers", method = RequestMethod.POST)
	public String doctorPost(Model model,
							 @RequestParam(value = "Accept", required=false) Long userId)
	{
		if(userId == null){
			userId = -1L;
		}
		if(userId > 0L){
			this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Long doctorID = userService.getUsersByUsername(userDetails.getUsername()).getId();
			if(!doctorPatientsService.getPatientIdByDoctorId(doctorID).contains(userId))
				doctorPatientsService.save(new DoctorPatients(doctorID,userId));
		}
		
		List<Users> allUsers = userService.getPatients();
		// Skoða hvort sá user sem er smellt á hefur gilt email og staðfest það.
		model.addAttribute("users", allUsers);
		Users patient = userService.findOne(userId);
		if(patient.getConfirmed()){
			// TODO tengja við helgaFAll
		} else {
			// TODO senda á html, að notandi er ekki með staðfest email eða með rétt email
			
		}
		
		return "allUsers/allUsers";
	}
}