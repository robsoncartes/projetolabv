package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Answer;

import java.util.List;

public interface AnswerService {

    Answer findById(Integer id);

    void saveAnswer(Answer answer);

    void saveAnswers(List<Answer> answers);

    Iterable<Answer> findAnswers();
}
