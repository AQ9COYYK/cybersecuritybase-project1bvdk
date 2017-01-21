package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.domain.Event;
import sec.project.repository.SignupRepository;
import sec.project.repository.EventRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }
    
    @RequestMapping(value = "/signups", method = RequestMethod.GET)
    /*public String loadSignups() {
        signupRepository.findAll();
        return "signups";
    }*/
    public String view(Model model) {
        model.addAttribute("signups", signupRepository.findAll());
        return "signups";
    }
    

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        Signup s = new Signup(name, address);
        
        signupRepository.save(new Signup(name, address));
        return "done";
        //return "admin";
    }
    
    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String submitAddEventForm(@RequestParam String name, @RequestParam String address) {
        
        Event e = new Event(name, address);
        eventRepository.save(new Event(name, address));
        //return "redirect:/admin";
        //vulnerability: unvalidated redirect 
        return "redirect:/redirect?uri=/admin";
        //return "admin";
    }
    
    //vulnerability: unvalidated redirect (uri must include the protocol (http:// or https://))
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect(@RequestParam String uri){
           return "redirect:" + uri;
    }
    
   
    
    @RequestMapping(value = "/addevent", method = RequestMethod.GET)
    public String addeventView(Model model) {
       
        
        return "addevent";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminview(Model model) {
        return "admin";
    }

}
