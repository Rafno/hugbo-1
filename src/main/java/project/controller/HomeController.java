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

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.net.HttpURLConnection;
@Controller
public class HomeController {

    // Instance
	@Autowired
    StringManipulationService stringService;
	@Autowired
    MedicineService medicineService;
	List<Medicine> medicine;
    // Dependency Injection
	private static HttpURLConnection con;
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
		}


		return "searchEngine/searchEngine";
	}
 
	private void sendPost() throws Exception {
		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	}
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(){

		return "About/about";
	}

}