package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.repositories.AnswerRepository;
import br.edu.fatecsjc.repositories.QuestionRepository;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Answer findById(Integer id) {

        Answer answer = answerRepository.findById(id).orElse(null);

        if (answer == null)
            throw new ObjectNotFoundException("Resposta não encontrada. Id: " + id + ", Tipo: " + Answer.class.getName());

        return answer;
    }

    public boolean isAnswerValid(String answerTitle, String questionTitle) {

        for (Answer answer : findAnswers()) {
            if ((answer.getAnswer().equals(answerTitle)) && (answer.getQuestion().getQuestionTitle().equals(questionTitle)))
                return false;
        }

        return true;
    }

    @Transactional
    public Answer saveAnswer(Answer answer, Question question) {

        boolean isValid = isAnswerValid(answer.getAnswer(), question.getQuestionTitle());

        if (isValid){
            questionRepository.save(question);
            return answerRepository.save(answer);
        }

        throw new DataIntegrityException("Resposta já existe para essa pergunta.");
    }

    public List<Answer> findAnswers() {

        return answerRepository.findAll();
    }
}
