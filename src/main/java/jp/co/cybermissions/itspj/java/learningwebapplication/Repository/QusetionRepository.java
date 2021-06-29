package jp.co.cybermissions.itspj.java.learningwebapplication.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.cybermissions.itspj.java.learningwebapplication.models.Question;

@Repository
public interface QusetionRepository extends JpaRepository<Question, Integer>{
    
    Collection<Question> findByChoices(int choices);

    @Query(value = "SELECT * FROM QUESTION ORDER BY RAND() LIMIT 1 ",nativeQuery = true)
    List<Question> findAllById(int id);
}
