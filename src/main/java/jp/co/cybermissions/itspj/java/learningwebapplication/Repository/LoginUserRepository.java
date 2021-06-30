package jp.co.cybermissions.itspj.java.learningwebapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.cybermissions.itspj.java.learningwebapplication.models.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser,Integer>{

    LoginUser findByUsername(String username);
}
