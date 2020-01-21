package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Question;

import java.util.List;

public interface QuestionService {

    Question findById(Integer id);

    void saveQuestion(Question question);

    void saveQuestions(List<Question> questions);

    Iterable<Question> findQuestions();
}
