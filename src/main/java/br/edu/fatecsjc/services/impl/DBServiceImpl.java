package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Administrator;
import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.models.User;
import br.edu.fatecsjc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AdministratorServiceImpl administratorService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private AnswerServiceImpl answerService;

    @Override
    public void instantiateTestDatabase() throws Exception {

        Administrator account1 = new Administrator(null, "admin1", "admin1");
        User account2 = new User(null, "user1", "user1");
        Administrator account3 = new Administrator(null, "admin2", "admin2");
        User account4 = new User(null, "user2", "user2");
        // accountService.saveAccounts(Arrays.asList(account1, account2, account3));
        // accountService.saveAccount(account4);

        administratorService.saveAdministrator(account1);
        userService.saveUser(account2);
        administratorService.saveAdministrator(account3);
        userService.saveUser(account4);

        Question q1 = new Question(null, "Q1", "QA1");
        Answer a11 = new Answer(null, "QA1", true, q1);
        Answer a12 = new Answer(null, "QA1", false, q1);
        Answer a13 = new Answer(null, "QA1", false, q1);
        Answer a14 = new Answer(null, "QA1", false, q1);

        q1.getAnswers().add(a11);

        questionService.saveQuestion(q1);
        answerService.saveAnswer(a11);
        answerService.saveAnswers(Arrays.asList(a12, a13, a14));

        Question q2 = new Question(null, "Q2", "QA1");
        Answer a21 = new Answer(null, "QA2", true, q2);
        Answer a22 = new Answer(null, "QA2", false, q2);
        Answer a23 = new Answer(null, "QA2", false, q2);
        Answer a24 = new Answer(null, "QA2", false, q2);

        questionService.saveQuestion(q2);
        answerService.saveAnswers(Arrays.asList(a21, a22, a23, a24));
    }
}
