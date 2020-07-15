package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.*;
import br.edu.fatecsjc.models.enums.AuthorityName;
import br.edu.fatecsjc.services.AccountService;
import br.edu.fatecsjc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private ActivityServiceImpl activityService;

    @Autowired
    private ChoiceServiceImpl choiceService;

    @Override
    public void instantiateTestDatabase() throws Exception {

        Administrator account1 = new Administrator(null, "email1@gmail.com", "admin1", "pass");
        Administrator account2 = new Administrator(null, "email2@gmail.com", "admin2", "pass");
        User account3 = new User(null, "email3@gmail.com", "user3", "pass");
        User account4 = new User(null, "email4@gmail.com", "user4", "pass");
        User account5 = new User(null, "email5@gmail.com", "user5", "pass");
        User account6 = new User(null, "email6@gmail.com", "user6", "pass");
        User account7 = new User(null, "email7@gmail.com", "user7", "pass");
        User account8 = new User(null, "email8@gmail.com", "user8", "pass");
        User account9 = new User(null, "email9@gmail.com", "user9", "pass");
        User account10 = new User(null, "email10@gmail.com", "user10", "pass");
        User account11 = new User(null, "email11@gmail.com", "user11", "pass");
        User account12 = new User(null, "email12@gmail.com", "user12", "pass");
        User account13 = new User(null, "email13@gmail.com", "user13", "pass");
        User account14 = new User(null, "email14@gmail.com", "user14", "pass");
        User account15 = new User(null, "email15@gmail.com", "user15", "pass");
        User account16 = new User(null, "email16@gmail.com", "user16", "pass");
        User account17 = new User(null, "email17@gmail.com", "user17", "pass");
        User account18 = new User(null, "email18@gmail.com", "user18", "pass");
        User account19 = new User(null, "email19@gmail.com", "user19", "pass");
        User account20 = new User(null, "email20@gmail.com", "user20", "pass");
        User account21 = new User(null, "email21@gmail.com", "user21", "pass");
        User account22 = new User(null, "email22@gmail.com", "user22", "pass");
        User account23 = new User(null, "email23@gmail.com", "user23", "pass");
        User account24 = new User(null, "email24@gmail.com", "user24", "pass");

        account1.addAuthorityName(AuthorityName.ADMINISTRATOR);
        account2.addAuthorityName(AuthorityName.ADMINISTRATOR);

        Exam exam1 = new Exam(null, "Exame A", "Tipo A", "Descrição A", account1.getUsername());
        Exam exam2 = new Exam(null, "Exame B", "Tipo B", "Descrição B", account1.getUsername());

        Question question1 = new Question(null, "Pergunta 1", exam1);
        Question question2 = new Question(null, "Pergunta 2", exam1);
        Question question3 = new Question(null, "Pergunta 3", exam2);

        Activity activity1 = new Activity(null, account3.getUsername(), exam1.getExamTitle());
        Activity activity2 = new Activity(null, account4.getUsername(), exam2.getExamTitle());

        Answer answer11 = new Answer(null, "True", true, question1);
        Answer answer12 = new Answer(null, "False", false, question1);
        Answer answer21 = new Answer(null, "Apple", true, question2);
        Answer answer22 = new Answer(null, "Wipro", false, question2);
        Answer answer31 = new Answer(null, "ADD", false, question3);
        Answer answer32 = new Answer(null, "INSERT", true, question3);

        Choice choice1 = new Choice(null, question1.getQuestionTitle(), answer11.getAnswer(), true, activity1);
        Choice choice2 = new Choice(null, question2.getQuestionTitle(), answer21.getAnswer(), true, activity1);
        Choice choice3 = new Choice(null, question3.getQuestionTitle(), answer32.getAnswer(), true, activity2);

        account3.addAtivity(activity1);
        account4.addAtivity(activity2);

        activity1.addAccount(account1);
        activity2.addAccount(account2);

        question1.addAnswer(answer11);
        question1.addAnswer(answer12);
        question2.addAnswer(answer21);
        question2.addAnswer(answer22);
        question3.addAnswer(answer31);
        question3.addAnswer(answer32);

        question1.setAssertion("True");
        question2.setAssertion("Apple");
        question3.setAssertion("INSERT");

        exam1.addQuestion(question1);
        exam1.addQuestion(question2);
        exam2.addQuestion(question3);

        exam1.setActivity(activity1);
        exam2.setActivity(activity2);

        List<Exam> exams = new ArrayList<>(Arrays.asList(exam1, exam2));
        // List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));
        // List<Answer> answers = new ArrayList<>(Arrays.asList(answer11, answer12, answer21, answer22, answer31, answer32));
        List<Choice> choices = new ArrayList<>(Arrays.asList(choice1, choice2, choice3));
        List<Activity> activities = new ArrayList<>(Arrays.asList(activity1, activity2));
        List<Account> accounts = new ArrayList<>(Arrays.asList(account1, account2, account3, account4, account5, account6, account7,
                account8, account9, account10, account11, account12, account13, account14, account15, account16, account17,
                account18, account19, account20, account21, account22, account23, account24));


        for (Account account : accounts) accountService.saveAccount(account);
        for (Activity activity : activities) activityService.saveActivity(activity);
        for (Exam exam : exams) examService.saveExam(exam);
        // for (Question question : questions) questionService.saveQuestion(question);

        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);
        questionService.saveQuestion(question3);

        // for (Answer answer : answers) answerService.saveAnswer(answer, question1);
        answerService.saveAnswer(answer11, question1);
        answerService.saveAnswer(answer12, question1);
        answerService.saveAnswer(answer21, question2);
        answerService.saveAnswer(answer22, question2);
        answerService.saveAnswer(answer31, question3);
        answerService.saveAnswer(answer32, question3);

        for (Choice choice : choices) choiceService.saveChoice(choice);
    }
}
