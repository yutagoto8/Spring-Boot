package jp.co.cybermissions.itspj.java.learningwebapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.ChoiceRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.QusetionRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.TeacherRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Choice;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Form;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Question;
import lombok.RequiredArgsConstructor;

@RequestMapping("/teacher")
@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherRepository tRepository;

    // private final CategoryRepository categoryRepository;

    private final QusetionRepository qusetionRepository;

    private final ChoiceRepository choiceRepository;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("teacher", tRepository.findAll());
        return "teacher/home";
    }

    // @GetMapping("/newcat")
    // public String newCategory(@ModelAttribute Category category, Model model) {
    //     model.addAttribute("question", category);
    //     return "teacher/newcat";
    // }

    // @PostMapping("/savecat")
    // public String saveCategory(@ModelAttribute Category category,Model model) {
    //     Category ca =new Category();
    //     ca.setCategoryName(category.getCategoryName());
    //     categoryRepository.save(category);
    //     return "teacher/home";
    // }

    @GetMapping("/new")
    public String newQusetion(@ModelAttribute Form form, Model model) {
        model.addAttribute("question", form);
        return "teacher/new";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute Form form, Model model) {
        Question q = new Question();
        q.setQuestionTitle(form.getQuestionTitle());
        q.setQuestionText(form.getQuestionText());
        q.setExplanation(form.getExplanation());
        q.setChoices(form.getChoices());

        Question newQuestion = qusetionRepository.save(q);

        Choice cho = new Choice();
        cho.setChoiceText(form.getChoiceText1());
        cho.setChoiceText(form.getChoiceText2());
        cho.setChoiceText(form.getChoiceText3());
        cho.setChoiceText(form.getChoiceText4());
        cho.setQuestion(newQuestion);
        cho.setCorrect(form.isCorrect1());
        cho.setCorrect(form.isCorrect2());
        cho.setCorrect(form.isCorrect3());
        cho.setCorrect(form.isCorrect4());
        choiceRepository.save(cho);

        return "teacher/home";
    }
    
}
