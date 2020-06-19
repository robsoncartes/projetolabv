package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.repositories.QuestionRepository;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question findById(Integer id) {

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null)
            throw new ObjectNotFoundException("Questão não encontrada. Id: " + id + ", Tipo: " + Question.class.getName());

        return question;
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public Iterable<Question> findQuestions() {

        return questionRepository.findAll();
    }
}
