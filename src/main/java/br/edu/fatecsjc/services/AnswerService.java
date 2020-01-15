package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Answer;

import java.util.List;

public interface AnswerService {

    Answer findAnswerById(Integer id);

    void saveAnswer(Answer answer);

    void saveAnswers(List<Answer> answers);
}
