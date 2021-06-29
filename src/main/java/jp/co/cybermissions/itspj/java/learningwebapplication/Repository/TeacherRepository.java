package jp.co.cybermissions.itspj.java.learningwebapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.cybermissions.itspj.java.learningwebapplication.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
    
}
