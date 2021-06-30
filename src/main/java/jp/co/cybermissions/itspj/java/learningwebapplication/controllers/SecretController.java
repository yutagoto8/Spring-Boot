package jp.co.cybermissions.itspj.java.learningwebapplication.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class SecretController {
    
    @GetMapping("")
    public String home() {
        return "auth/home";
    }
}
