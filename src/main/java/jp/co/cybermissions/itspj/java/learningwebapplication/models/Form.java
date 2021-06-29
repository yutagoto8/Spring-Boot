package jp.co.cybermissions.itspj.java.learningwebapplication.models;

import java.util.List;

import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Form {
    private String questionTitle;

    private String questionText;

    private String explanation;

    private String choiceText1;

    private String choiceText2;

    private String choiceText3;

    private String choiceText4;

    private boolean correct1;

    private boolean correct2;

    private boolean correct3;

    private boolean correct4;


    @OneToMany(mappedBy = "question")
    private List<Choice> choices;

}
