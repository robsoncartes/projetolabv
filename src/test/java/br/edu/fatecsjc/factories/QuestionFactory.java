package br.edu.fatecsjc.factories;

import br.edu.fatecsjc.models.Exam;
import br.edu.fatecsjc.models.Question;

public class QuestionFactory {

    public static Question validQuestion(Question question) {

        // Question q = new Question(1, "questi", new Exam());
        question.setId(1);
        question.setQuestionTitle("Question1");
        question.setExam(ExamFactory.validExam(new Exam()));

        return question;
    }
}
