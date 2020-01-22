package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.repositories.AnswerRepository;
import br.edu.fatecsjc.services.AnswerService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer findById(Integer id) {

        Answer answer = answerRepository.findById(id).orElse(null);

        if (answer == null)
            throw new ObjectNotFoundException("Answer not found. Id: " + id + ", Type: " + Answer.class.getName());

        return answer;
    }

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void saveAnswers(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    @Override
    public Iterable<Answer> findAnswers() {

        return answerRepository.findAll();
    }
}
