package jp.co.cybermissions.itspj.java.learningwebapplication.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    private String username;

    @Size(min = 3,max = 255)
    private String password;

    //権限
    private String role;

    //teacherフラグ
    private boolean teacher;

    @Email
    @Size(max = 255)
    @NotBlank
    private String email;

    // @ManyToOne
    // @JoinColumn(name = "question_id")
    // private Question question;


}
