package projekat.veb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import projekat.veb.repo.userRepo;

@RestController
@RequestMapping()
@CrossOrigin
public class UserController {

    private userRepo userRepo;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    /*
     * @RequestMapping("/registration")
     * public String RegistrationForm(Model model) {
     * model.addAttribute("user", new User());
     * return "registration.html";
     * }
     * 
     */
}
