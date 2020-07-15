package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.models.Question;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerService {

    Answer findById(Integer id);

    boolean isAnswerValid(String answerTitle, String questionTitle);

    @Transactional
    Answer saveAnswer(Answer answer, Question question);

    List<Answer> findAnswers();
}
