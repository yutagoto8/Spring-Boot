package jp.co.cybermissions.itspj.java.learningwebapplication.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.LoginUserRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.LoginUser;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Role;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginUserRepository userRep;
    

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // @PostMapping("/login")
    // public String userLogin(@ModelAttribute("user") LoginUser user, Model model) {
    //     model.addAttribute("teacher", user.isTeacher());
    //     return "auth/home";
    // }
    @GetMapping("/")
    public String mainHome() {
        return "auth/main";
    }

    @GetMapping("/home")
    public String userHome(LoginUser user, Model model) {
        return "auth/home";
    }


    @GetMapping("/register")
    public String register(@ModelAttribute("user") LoginUser user) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String createUser(@Validated @ModelAttribute("user") LoginUser user, BindingResult result) {
        if(result.hasErrors()) {
            return "auth/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.isTeacher() ? Role.TEACHER.name() : Role.STUDENT.name());
        
        userRep.save(user);
        
        return "redirect:/login?register";
    }
    
}
