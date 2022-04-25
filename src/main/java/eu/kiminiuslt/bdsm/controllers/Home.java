package eu.kiminiuslt.bdsm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Home {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/home")
    public String homePost(Model model){

    System.out.println(model.getAttribute("login"));
        return "home";
    }
}
