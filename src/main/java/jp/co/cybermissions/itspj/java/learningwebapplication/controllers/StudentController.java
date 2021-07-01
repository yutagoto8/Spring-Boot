package jp.co.cybermissions.itspj.java.learningwebapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.ChoiceRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.LoginUserRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.QusetionRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Choice;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.LoginUser;
import lombok.RequiredArgsConstructor;

@RequestMapping("/student")
@Controller
@RequiredArgsConstructor
public class StudentController {

    private final LoginUserRepository lRepository;
    
    private final QusetionRepository qRepository;

    private final ChoiceRepository choiceRepository;


    @GetMapping("/home")
    public String studentHome(Model model, LoginUser user) {
        model.addAttribute("student",lRepository.findAll());
        return "student/home";
    }

    @PostMapping("/home")
    public String randamQuestion(Model model){
        model.addAttribute("nextId", qRepository.ran());
        return "student/home";
    }

    @GetMapping("/question/{id}")
    public String question(@PathVariable int id,Model model) {
        model.addAttribute("question", qRepository.findById(id).get());

        return "student/question";
    }

    @PatchMapping("/question/{id}")
    public String choice(@RequestParam(name = "choice") int choiceId, @PathVariable int id,Model model) {
        model.addAttribute("question", qRepository.findById(id).get());
        model.addAttribute("answer" , qRepository.findById(id).get());
        Choice ch =  choiceRepository.findById(choiceId).get();
        model.addAttribute("correct",ch.isCorrect());
        model.addAttribute("nextId", qRepository.ran());

        return "student/question";  

    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", qRepository.findAll());
        return "student/list";
    }

}
