package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.repositories.AnswerRepository;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer findById(Integer id) {

        Answer answer = answerRepository.findById(id).orElse(null);

        if (answer == null)
            throw new ObjectNotFoundException("Answer not found. Id: " + id + ", Type: " + Answer.class.getName());

        return answer;
    }

    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Iterable<Answer> findAnswers() {

        return answerRepository.findAll();
    }
}
