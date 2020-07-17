package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Question;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Question findById(Integer id);

    Question findByTitle(String questionTitle);

    boolean isQuestionAvailable(String questionTitle, String examTitle);

    @Transactional
    Question saveQuestion(Question question);

    List<Question> findQuestions();

  List<Question> findQuestionByExamId(Integer examId);

}
