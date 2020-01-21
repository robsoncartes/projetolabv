package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.repositories.QuestionRepository;
import br.edu.fatecsjc.services.QuestionService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question findById(Integer id) {

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null)
            throw new ObjectNotFoundException("Question not found. Id: " + id + ", Type: " + Question.class.getName());

        return question;
    }

    @Override
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void saveQuestions(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    @Override
    public Iterable<Question> findQuestions() {

        return questionRepository.findAll();
    }
}
