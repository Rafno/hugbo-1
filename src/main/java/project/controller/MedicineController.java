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
    private MedicineService postitNoteService;

    // Dependency Injection
    @Autowired
    public MedicineController(MedicineService postitNoteService) {
        this.postitNoteService = postitNoteService;
    }

    // Method that returns the correct view for the URL /postit
    // This handles the GET request for this URL
    // Notice the `method = RequestMethod.GET` part
    @RequestMapping(value = "/postit", method = RequestMethod.GET)
    public String postitNoteViewGet(Model model){

        // Add a new Medicine Note to the model for the form
        // If you look at the form in Medicines.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("postitNote",new Medicine());

        // Here we get all the Medicine Notes (in a reverse order) and add them to the model
        model.addAttribute("postitNotes",postitNoteService.findAllReverseOrder());

        // Return the view
        return "medicines/Medicines";
    }

    // Method that receives the POST request on the URL /postit
    // and receives the ModelAttribute("postitNote")
    // That attribute is the attribute that is mapped to the form, so here
    // we can save the postit note because we get the data that was entered
    // into the form.
    // Notice the `method = RequestMethod.POST` part
    @RequestMapping(value = "/postit", method = RequestMethod.POST)
    public String postitNoteViewPost(@ModelAttribute("postitNote") Medicine postitNote,
                                     Model model){

        // Save the Medicine Note that we received from the form
        postitNoteService.save(postitNote);

        // Here we get all the Medicine Notes (in a reverse order) and add them to the model
        model.addAttribute("postitNotes", postitNoteService.findAllReverseOrder());

        // Add a new Medicine Note to the model for the form
        // If you look at the form in Medicines.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("postitNote", new Medicine());

        // Return the view
        return "medicines/Medicines";
    }

    // Method that returns the correct view for the URL /postit/{name}
    // The {name} part is a Path Variable, and we can reference that in our method
    // parameters as a @PathVariable. This enables us to create dynamic URLs that are
    // based on the data that we have.
    // This method finds all Medicine Notes posted by someone with the requested {name}
    // and returns a list with all those Medicine Notes.
    @RequestMapping(value = "/postit/{name}", method = RequestMethod.GET)
    public String postitNoteGetNotesFromName(@PathVariable String name,
                                             Model model){

        // Get all Medicine Notes with this name and add them to the model
        model.addAttribute("postitNotes", postitNoteService.findByName(name));

        // Add a new Medicine Note to the model for the form
        // If you look at the form in Medicines.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("postitNote", new Medicine());

        // Return the view
        return "medicines/Medicines";
    }
}
