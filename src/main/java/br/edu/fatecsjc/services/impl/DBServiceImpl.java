package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.*;
import br.edu.fatecsjc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private AccountServiceImpl accountService;

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

        Administrator account1 = new Administrator(null, "admin1", "admin1");
        User account2 = new User(null, "user1", "user1");
        Administrator account3 = new Administrator(null, "admin2", "admin2");
        User account4 = new User(null, "user2", "user2");

        accountService.saveAccounts(Arrays.asList(account1, account2, account3));
        accountService.saveAccount(account4);

        Exam exam1 = new Exam(null, "Exam 1", "Type 1", "Description 1", account1.getUsername());
        Activity activity1 = new Activity(null, account1.getUsername(), exam1.getTitle());

        Question q1 = new Question(null, "Question 1", exam1);
        q1.setAssertion("QA11");

        Answer a11 = new Answer(null, "QA11", true, q1);
        Answer a12 = new Answer(null, "QA12", false, q1);
        Answer a13 = new Answer(null, "QA13", false, q1);
        Answer a14 = new Answer(null, "QA14", false, q1);


        Question q2 = new Question(null, "Question 2", exam1);
        q2.setAssertion("QA22");

        Answer a21 = new Answer(null, "QA21", false, q2);
        Answer a22 = new Answer(null, "QA22", true, q2);
        Answer a23 = new Answer(null, "QA23", false, q2);
        Answer a24 = new Answer(null, "QA24", false, q2);


        Exam exam2 = new Exam(null, "Exam 2", "Type 2", "Description 2", account2.getUsername());

        Question q3 = new Question(null, "Question 1", exam2);
        q3.setAssertion("QA33");

        Answer a31 = new Answer(null, "QA31", false, q3);
        Answer a32 = new Answer(null, "QA32", false, q3);
        Answer a33 = new Answer(null, "QA33", true, q3);
        Answer a34 = new Answer(null, "QA34", false, q3);


        Question q4 = new Question(null, "Question 2", exam2);
        q4.setAssertion("QA44");

        Answer a41 = new Answer(null, "QA41", false, q4);
        Answer a42 = new Answer(null, "QA42", false, q4);
        Answer a43 = new Answer(null, "QA43", false, q4);
        Answer a44 = new Answer(null, "QA44", true, q4);

        Choice choice1 = new Choice(null, "Question 1", "QA11", true, activity1);
        Choice choice2 = new Choice(null, "Question 2", "QA22", true, activity1);
        Choice choice3 = new Choice(null, "Question 3", "QA33", true, activity1);
        Choice choice4 = new Choice(null, "Question 4", "QA44", true, activity1);
        activity1.addChoice(choice1);

        activityService.saveActivity(activity1);
        choiceService.saveChoices(Arrays.asList(choice1, choice2, choice3, choice4));

        exam1.addQuestions(Arrays.asList(q1, q2));
        exam2.addQuestions(Arrays.asList(q3, q4));
        q1.addAnswers(Arrays.asList(a11, a12, a13, a14));
        q2.addAnswers(Arrays.asList(a21, a22, a23, a24));
        q3.addAnswers(Arrays.asList(a31, a32, a33, a34));
        q4.addAnswers(Arrays.asList(a41, a42, a43, a44));

        examService.saveExams(Arrays.asList(exam1, exam2));
        questionService.saveQuestions(Arrays.asList(q1, q2, q3, q4));
        answerService.saveAnswers(Arrays.asList(a11, a12, a13, a14, a21, a22, a23, a24, a31, a32, a33, a34, a41, a42, a43, a44));
    }
}
