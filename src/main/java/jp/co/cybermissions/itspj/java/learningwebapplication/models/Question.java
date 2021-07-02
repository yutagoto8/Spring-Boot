package jp.co.cybermissions.itspj.java.learningwebapplication.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 100)
    @NotBlank
    private String questionTitle;

    @NotBlank
    @Size(max = 500)
    private String questionText;

    @NotBlank
    @Size(max = 1000)
    private String explanation;

    @OneToMany(mappedBy = "question")
    private List<Choice> choices;

    // @OneToMany(mappedBy = "question")
    // private List<LoginUser> loginUsers;

}
