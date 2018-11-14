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
public class HomeController
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
	public HomeController(StringManipulationService stringService)
	{

		this.stringService = stringService; List<Medicine> medicine = new ArrayList<Medicine>();
	}

	// Request mapping is the path that you want to map this method to
	// In this case, the mapping is the root "/", so when the project
	// is running and you enter "localhost:8080" into a browser, this
	// method is called
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, Principal principal)
	{

		// The string "Index" that is returned here is the name of the view
		// (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
		// If you change "Index" to something else, be sure you have a .jsp
		// file that has the same name
		try
		{
			this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String name = userService.getUsersByUsername(userDetails.getUsername()).getName();
			model.addAttribute("name", name); model.addAttribute("userLoggedInn", true);
			model.addAttribute("loggedInn", true);
			String role = userService.getUser(userDetails.getUsername()).getRole();
			// Skoða hvort að notandi er læknir eða ekki
			if(role.equals("DOCTOR"))
			{
				System.out.println("þessi er læknir");
				model.addAttribute("doctorLoggadurInn", true);
			}else {
				// Doctor er ekki loggaður inn
			}

		}
		catch(Exception e)
		{
		}


		return "Index/Index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(Model model, Principal principal, @RequestParam("search") String leita,
						   @RequestParam("nafn") String nafn, @RequestParam("styrkur") String styrkur,
						   @RequestParam("lyfjaform") String lyfjaform, @RequestParam("utgafudagur") String utgafudagur
	)
	{
		// get search results
		medicine = medicineService.findPlaceContainingKeywordAnywhere(stringService.convertStringToLowerCase(leita));
		model.addAttribute("leita", leita); model.addAttribute("medicine", medicine);


		if(principal != null)
		{
			// Find patients for Doctor
			userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String name = userService.getUsersByUsername(userDetails.getUsername()).getName();
			// assign medicine to user

			if(nafn.contains(": "))
			{
				String[] s1, s2, s3, s4;
				// make substrings
				s1 = nafn.split(Pattern.quote(": ")); s2 = styrkur.split(Pattern.quote(": "));
				s3 = lyfjaform.split(Pattern.quote(": ")); s4 = utgafudagur.split(Pattern.quote(": "));

				Long medicineId = medicineService.getMedId(s1[1], s2[1], s3[1], s4[1]);

				Long userId = userService.getUser(userDetails.getUsername()).getId();
				Cabinet cabinet = new Cabinet(medicineId, userId); cabinetService.save(cabinet);
			}
		}
		// hér þarf að skoða hvort user er loggaður inn því þeira fara á mismunandi pop up glugga
		//doctorPatientsService.save(new DoctorPatients(91L, 87L));
		//doctorPatientsService.save(new DoctorPatients(91L, 89L));
		try
		{
			this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String name = userService.getUsersByUsername(userDetails.getUsername()).getName();
			model.addAttribute("userLoggedInn", true); model.addAttribute("loggedInn", true);
			model.addAttribute("name", name);
			String role = userService.getUser(userDetails.getUsername()).getRole();

			if(role.equals("DOCTOR"))
			{
				try
				{
					model.addAttribute("doctor", true);
					model.addAttribute("doctorLoggadurInn", true);
					//model.addAttribute("loggedInn", true);
					Long doctorId = userService.getUser(userDetails.getUsername()).getId();
					List<Long> userids = doctorPatientsService.getPatientIdByDoctorId(doctorId);
					List<Users> patients = userService.getUsersById(userids);

					model.addAttribute("patients", patients );

				}
				catch(Exception e)
				{

				}
			}
			//annars er hann þá sjúklingur


		}
		catch(Exception e)
		{

		} return "searchEngine/searchEngine";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model, Principal principal)
	{
		// hér þarf að skoða hvort user er loggaður inn því þeira fara á mismunandi pop up glugga
		try
		{
			this.userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String name = userService.getUsersByUsername(userDetails.getUsername()).getName();
			model.addAttribute("loggedInn", true); model.addAttribute("name", name);
		}
		catch(Exception err)
		{

		} return "About/about";
	}
	@RequestMapping(value = "/netfangstadfest", method = RequestMethod.GET)
	public String email(HttpServletRequest request) throws IOException {
		// Hér næ ég í ID
		System.out.println(request.getQueryString());
		// Hér þarf að bera saman Id sem server sendi og mið við hvað email notandi gerði
		return ("email/emails");

	}
}