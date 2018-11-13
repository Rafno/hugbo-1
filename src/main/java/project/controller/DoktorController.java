package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.Cabinet;
import project.persistence.entities.DoctorPatients;
import project.persistence.entities.Medicine;
import project.persistence.entities.Users;
import project.service.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


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

		this.stringService = stringService; List<Medicine> medicine = new ArrayList<Medicine>();
	}

	// Request mapping is the path that you want to map this method to
	// In this case, the mapping is the root "/", so when the project
	// is running and you enter "localhost:8080" into a browser, this
	// method is called
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public String allUsers()
	{
		return "allUsers/allUsers";
	}

	@RequestMapping(value = "/allusers", method = RequestMethod.POST)
	public String doctorPost()
	{
		// Dagsetning - Nafn - Bæjarfélag - póstnúmer - heimilsfang - email
		List<Users> allUsers = userService.findAll();
		return "allUsers/allUsers";
	}
}