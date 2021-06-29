package jp.co.cybermissions.itspj.java.learningwebapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.CategoryRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.ChoiceRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.QusetionRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.StudentRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Choice;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Question;
import jp.co.cybermissions.itspj.java.learningwebapplication.service.RandomQuestionService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/student")
@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository sRepository;
    
    private final QusetionRepository qRepository;

    // private final CategoryRepository categoryRepository;

    private final ChoiceRepository choiceRepository;


    @GetMapping("/home")
    public String studentHome(Model model) {
        model.addAttribute("student", sRepository.findAll());
        return "student/home";
    }

    // @GetMapping("/category")
    // public String questionCategory(Model model) {
    //     model.addAttribute("category", categoryRepository.findAll());
    //     return "student/category";
    // }

    // @PostMapping("/category/{id}/sellect")
    // public String sellect(@RequestParam(name = "category") int categoryId,@PathVariable int id, Model model ) {
    //     model.addAttribute("question", qRepository.findByCategory(categoryId));
    //     return "redirect:/student/question/{id}";
    // }

    @GetMapping("/question/{id}")
    public String question(@PathVariable int id,Model model) {
        model.addAttribute("question", qRepository.findById(id).get());

        return "student/question";
    }

    // public String choiceCorrect() {
    // List<Choice> choices;
    // for(Choice c : choices) {
    //     if(c.isCorrect()) {
    //         return c.getChoiceText();
    //     }
    // }
    // }

    @PatchMapping("/question/{id}")
    public String choice(@RequestParam(name = "choice") int choiceId, @PathVariable int id,Model model) {
        model.addAttribute("question", qRepository.findById(id).get());
        model.addAttribute("answer" , qRepository.findById(id).get());
        Choice ch =  choiceRepository.findById(choiceId).get();

        model.addAttribute("correct",ch.isCorrect());
        return "student/question";  

    }

    RandomQuestionService random;

    @PostMapping("/question/{id}")
    public String randomQuestion(@PathVariable int id, @ModelAttribute Question question, Model model) {
        id = random.RandomQuestion();
        return "student/question";
    }
    

}
