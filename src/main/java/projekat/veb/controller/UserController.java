package projekat.veb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//import projekat.veb.repo.userRepo;

@Controller
public class UserController {

    // @Autowired
    // private userRepo userRepo;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
