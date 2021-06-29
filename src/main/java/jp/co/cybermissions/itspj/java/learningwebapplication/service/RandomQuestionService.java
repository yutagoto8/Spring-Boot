package jp.co.cybermissions.itspj.java.learningwebapplication.service;

import org.springframework.stereotype.Service;

import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.QusetionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RandomQuestionService {

    private final QusetionRepository qusetionRepository;

    public int RandomQuestion() {
        return (int) (Math.random() * qusetionRepository.count());
    }
    
}
