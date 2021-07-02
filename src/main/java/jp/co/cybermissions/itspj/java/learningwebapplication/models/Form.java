package jp.co.cybermissions.itspj.java.learningwebapplication.models;

import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Form {
    @NotBlank
    @Size(max = 100)
    private String questionTitle;

    @NotBlank
    @Size(max = 500)
    private String questionText;

    @NotBlank
    @Size(max = 1000)
    private String explanation;

    @NotBlank
    @Size(max = 200)
    private String choiceText1;

    @NotBlank
    @Size(max = 200)
    private String choiceText2;

    @NotBlank
    @Size(max = 200)
    private String choiceText3;

    @NotBlank
    @Size(max = 200)
    private String choiceText4;

    private boolean correct1;

    private boolean correct2;

    private boolean correct3;

    private boolean correct4;


    @OneToMany(mappedBy = "question")
    private List<Choice> choices;

}
