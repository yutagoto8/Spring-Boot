package jp.co.cybermissions.itspj.java.learningwebapplication.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.cybermissions.itspj.java.learningwebapplication.models.Question;

@Repository
public interface QusetionRepository extends JpaRepository<Question, Integer>{
    
    Collection<Question> findByChoices(int choices);

    //idをランダムに取得
    @Query(value = "SELECT * FROM QUESTION ORDER BY RAND() LIMIT 1 ",nativeQuery = true)
    Integer ran ();

    @Query(value = "SELECT MIN(ID) FROM QUESTION WHERE ID > ?",nativeQuery = true)
    Question findByIdOrderById(int id);

}
