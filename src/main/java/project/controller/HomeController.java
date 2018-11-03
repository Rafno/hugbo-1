package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.persistence.entities.Medicine;
import project.service.MedicineService;
import project.service.StringManipulationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class HomeController {

    // Instance
	@Autowired
    StringManipulationService stringService;
	@Autowired
    MedicineService medicineService;
	List<Medicine> medicine;
    // Dependency Injection
    @Autowired
    public HomeController(StringManipulationService stringService) {

    	this.stringService = stringService;
		List<Medicine> medicine = new ArrayList<Medicine>();
    }

    // Request mapping is the path that you want to map this method to
    // In this case, the mapping is the root "/", so when the project
    // is running and you enter "localhost:8080" into a browser, this
    // method is called
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
		
        // The string "Index" that is returned here is the name of the view
        // (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
        // If you change "Index" to something else, be sure you have a .jsp
        // file that has the same name
        return "Index/Index";
    }
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(Model model,@RequestParam("search") String leita,
						   @RequestParam("nafn") String nafn,
						   @RequestParam("styrkur") String styrkur,
						   @RequestParam("lyfjaform") String lyfjaform,
						   @RequestParam("utgafudagur") String utgafudagur){
    	// get search results
		medicine =  medicineService.findPlaceContainingKeywordAnywhere(stringService.convertStringToLowerCase(leita));
		model.addAttribute("leita",leita);
		model.addAttribute("medicine", medicine);

		// make substrings
		if(nafn.contains(": ")) {
			String[] s1 = nafn.split(Pattern.quote(": "));
			String[] s2 = styrkur.split(Pattern.quote(": "));
			String[] s3 = lyfjaform.split(Pattern.quote(": "));
			String[] s4 = utgafudagur.split(Pattern.quote(": "));
			System.out.println(medicineService.getMedId(s1[1], s2[1], s3[1], s4[1]));
		}


		return "searchEngine/searchEngine";
	}
    // To call this method, enter "localhost:8080/user" into a browser
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model){

        // Here we will show how to add attributes to a model and send it to the view

        // Since this small example is for a user, let's create some attributes
        // that users might usually have in a system
        String name = "Rincewind";
        String job  = "Wizzard";
        String email = "rincewizz@unseenuni.edu";
        String description = "most likely to survive in a dungeon dimension.";


        // Since we want our attributes regarding the user always in the same format,
        // we are going to convert some strings using our StringManipulationService

        // Let's assume that the name, job and description always have
        // the first character in upper case
        name = stringService.convertsFirstCharInStringToUpperCase(name);
        job = stringService.convertsFirstCharInStringToUpperCase(job);
        description = stringService.convertsFirstCharInStringToUpperCase(description);

        // Let's assume that we always want e-mail in lower case
        email = stringService.convertStringToLowerCase(email);


        // Now let's add the attributes to the model
        model.addAttribute("name",name);
        model.addAttribute("job",job);
        model.addAttribute("email",email);
        model.addAttribute("description",description);

        // By adding attributes to the model, we can pass information from the controller
        // to the view (the .jsp file).
        // Look at the User.jsp file in /main/webapp/WEB-INF/jsp/ to see how the data is accessed
        return "User";
    }
	/*
    @RequestMapping(value = "/medicine", method = RequestMethod.GET)
    public String medicine(Model model)
    {

        String name = "Ibofen";
        String type  = "Verkjalyf";


        // Now let's add the attributes to the model
        model.addAttribute("name",name);
        model.addAttribute("type", type);

        return "Medicine";
    }
    */
	/*
		* This function reciewves the path /about and goes straight to jsp file about. There we hanve
		* Html code that displays some text and images
	 */
	// To call this method, enter "localhost:8080/user" into a browser
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model){
		return "About/about";
	}




}
