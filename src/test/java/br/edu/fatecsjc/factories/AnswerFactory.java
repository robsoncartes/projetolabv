package br.edu.fatecsjc.factories;

import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.models.Question;

public class AnswerFactory {

    public static Answer validAnswer(Answer answer){

        // Answer a = new Answer(1, "alla", true, new Question());
        answer.setId(1);
        answer.setAnswer("Answer1");
        answer.setCorrect(true);
        answer.setQuestion(new Question());

        return answer;
    }
}
