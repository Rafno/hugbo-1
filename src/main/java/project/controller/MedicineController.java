package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Medicine;
import project.service.MedicineService;

@Controller
public class MedicineController {

    // Instance Variables
	@Autowired
    private MedicineService medicineService;

    // Dependency Injection
    @Autowired
    public MedicineController(MedicineService medicineService) {this.medicineService = medicineService;
    }

    // Method that returns the correct view for the URL /medicinePost
    // This handles the GET request for this URL
    // Notice the `method = RequestMethod.GET` part
    @RequestMapping(value = "/medicinePost", method = RequestMethod.GET)
    public String medicineViewGet(Model model){

        // Add a new Medicine Note to the model for the form
        // If you look at the form in Medicines.jsp, you can see that we
        // reference this attribute there by the name `medicine`.
        model.addAttribute("medicine",new Medicine());

        // Here we get all the Medicine Notes (in a reverse order) and add them to the model
        model.addAttribute("medicines",medicineService.findAllReverseOrder());
        // Return the view
        return "medicines/Medicines";
    }

    // Method that receives the POST request on the URL /medicinePost
    // and receives the ModelAttribute("medicine")
    // That attribute is the attribute that is mapped to the form, so here
    // we can save the medicinePost note because we get the data that was entered
    // into the form.
    // Notice the `method = RequestMethod.POST` part
    @RequestMapping(value = "/medicinePost", method = RequestMethod.POST)
    public String medicineViewPost(@ModelAttribute("medicine") Medicine medicine,
                                     Model model){

        // Save the Medicine Note that we received from the form
        medicineService.save(medicine);

        // Here we get all the Medicine Notes (in a reverse order) and add them to the model
        model.addAttribute("medicines", medicineService.findAllReverseOrder());

        // Add a new Medicine Note to the model for the form
        // If you look at the form in Medicines.jsp, you can see that we
        // reference this attribute there by the name `medicine`.
        model.addAttribute("medicine", new Medicine());

        // Return the view
        return "medicines/Medicines";
    }

    // Method that returns the correct view for the URL /medicinePost/{name}
    // The {name} part is a Path Variable, and we can reference that in our method
    // parameters as a @PathVariable. This enables us to create dynamic URLs that are
    // based on the data that we have.
    // This method finds all Medicine Notes posted by someone with the requested {name}
    // and returns a list with all those Medicine Notes.
    @RequestMapping(value = "/medicinePost/{name}", method = RequestMethod.GET)
    public String medicineGetNotesFromName(@PathVariable String name,
                                             Model model){

        // Get all Medicine Notes with this name and add them to the model
        model.addAttribute("medicines", medicineService.findByName(name));

        // Add a new Medicine Note to the model for the form
        // If you look at the form in Medicines.jsp, you can see that we
        // reference this attribute there by the name `medicine`.
        model.addAttribute("medicine", new Medicine());

        // Return the view
        return "medicines/Medicines";
    }
}
