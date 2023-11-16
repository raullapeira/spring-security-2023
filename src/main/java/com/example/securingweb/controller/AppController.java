package com.example.securingweb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.securingweb.entity.User;
import com.example.securingweb.repository.UserRepository;

@Controller
@RequestMapping("/")
public class AppController {
 
	@Autowired
    private UserRepository userRepo;
	
	@GetMapping("/")
    public String indexPage() {   
        return "home";    
    }   
    @GetMapping("/home")
    public String homePage() {   
        return "home";    
    }  

    @GetMapping("/hello")
    public String helloPage() {   
        return "hello";    
    } 
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
    	System.out.println("Llega aqui: userRegister");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         
        userRepo.save(user);
        
         
        return "home";
    }
}