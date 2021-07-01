package jp.co.cybermissions.itspj.java.learningwebapplication.controllers;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.ChoiceRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.LoginUserRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.QusetionRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Choice;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Form;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.Question;
import lombok.RequiredArgsConstructor;

@RequestMapping("/teacher")
@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final LoginUserRepository lRepository;

    // private final CategoryRepository categoryRepository;

    private final QusetionRepository qusetionRepository;

    private final ChoiceRepository choiceRepository;

    @GetMapping("/home")
    public String teacherHome(Model model) {
        model.addAttribute("teacher", lRepository.findAll());
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
        cho.setCorrect(form.isCorrect1());
        cho.setQuestion(newQuestion);
        choiceRepository.save(cho);

        cho = new Choice();
        cho.setChoiceText(form.getChoiceText2());
        cho.setCorrect(form.isCorrect2());
        cho.setQuestion(newQuestion);
        choiceRepository.save(cho);

        cho = new Choice();
        cho.setChoiceText(form.getChoiceText3());
        cho.setCorrect(form.isCorrect3());
        cho.setQuestion(newQuestion);
        choiceRepository.save(cho);

        cho = new Choice();
        cho.setChoiceText(form.getChoiceText4());
        cho.setCorrect(form.isCorrect4());
        cho.setQuestion(newQuestion);
        choiceRepository.save(cho);
        
        return "teacher/home";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", qusetionRepository.findAll());
        return "teacher/list";

    }

    @GetMapping("/question/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("question", qusetionRepository.findById(id).get());
        return "teacher/question";
    }

    @GetMapping("/question/{id}/edit")
    public String edit(@PathVariable int id,Model model) {
        Question q = qusetionRepository.findById(id).get();
        model.addAttribute("question", q);
        return "teacher/edit";
    }

    // @PatchMapping("/question/{id}/edit")
    // public String update(@PathVariable int id, @ModelAttribute Form form,@ModelAttribute Question question,@ModelAttribute Choice choice, Model model) {
    //     question.setId(id);
    //     Question q = new Question();
    //     q.setQuestionTitle(form.getQuestionTitle());
    //     q.setQuestionText(form.getQuestionText());
    //     q.setExplanation(form.getExplanation());
    //     q.setChoices(form.getChoices());

    //     Question newQuestion = qusetionRepository.save(q);
        
    //     choice.setQuestion(question);
    //     Choice cho = new Choice();
    //     cho.setChoiceText(form.getChoiceText1());
    //     cho.setCorrect(form.isCorrect1());
    //     cho.setQuestion(newQuestion);
    //     choiceRepository.save(cho);

    //     cho = new Choice();
    //     cho.setChoiceText(form.getChoiceText2());
    //     cho.setCorrect(form.isCorrect2());
    //     cho.setQuestion(newQuestion);
    //     choiceRepository.save(cho);

    //     cho = new Choice();
    //     cho.setChoiceText(form.getChoiceText3());
    //     cho.setCorrect(form.isCorrect3());
    //     cho.setQuestion(newQuestion);
    //     choiceRepository.save(cho);

    //     cho = new Choice();
    //     cho.setChoiceText(form.getChoiceText4());
    //     cho.setCorrect(form.isCorrect4());
    //     cho.setQuestion(newQuestion);
    //     choiceRepository.save(cho);
        
    //     return "teacher/home";
    // }



}
