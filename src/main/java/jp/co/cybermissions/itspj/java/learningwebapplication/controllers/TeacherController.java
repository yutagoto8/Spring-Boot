package jp.co.cybermissions.itspj.java.learningwebapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final QusetionRepository qusetionRepository;

    private final ChoiceRepository choiceRepository;

    @GetMapping("/home")
    public String teacherHome(Model model) {
        model.addAttribute("teacher", lRepository.findAll());
        return "teacher/home";
    }

    @GetMapping("/new")
    public String newQusetion(@ModelAttribute Form form, Model model) {
        model.addAttribute("question", form);
        return "teacher/new";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute("question") Form form,BindingResult result, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            // model.addAttribute("question", form);
            return "teacher/new";
        }

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
        
        attrs.addFlashAttribute("success", "質問の登録に成功しました。");
        return "redirect:/teacher/home";
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

}
