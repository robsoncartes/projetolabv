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

        Exam exam1 = new Exam(null, "Database Test", "Technology", "Database questions to improve your knowledge", account1.getUsername());

        Question question1 = new Question(null, "You can add a row using SQL in a database with which of the following?", exam1);
        Answer answer11 = new Answer(null, "ADD", false, question1);
        Answer answer12 = new Answer(null, "CREATE", false, question1);
        Answer answer13 = new Answer(null, "INSERT", true, question1);
        Answer answer14 = new Answer(null, "MAKE", false, question1);

        Question question2 = new Question(null, "The SQL WHERE clause:", exam1);
        Answer answer15 = new Answer(null, "limits the column data that are returned", false, question2);
        Answer answer16 = new Answer(null, "limits the row data are returned", true, question2);
        Answer answer17 = new Answer(null, "Both A and B are correct", false, question2);
        Answer answer18 = new Answer(null, "Neither A nor B are correct", false, question2);

        Question question3 = new Question(null, "Which of the following is the original purpose of SQL?", exam1);
        Answer answer19 = new Answer(null, "To specify the syntax and semantics of SQL data definition language", false, question3);
        Answer answer20 = new Answer(null, "To specify the syntax and semantics of SQL manipulation language", false, question3);
        Answer answer21 = new Answer(null, "To define the data structures", false, question3);
        Answer answer22 = new Answer(null, "All of the above", true, question3);

        Exam exam2 = new Exam(null, "General Knowledge", "General", "Some wikipedia questions for you to enjoy!", account1.getUsername());

        Question question4 = new Question(null, "If a=1 and b=2, what is a+b??", exam2);
        Answer answer23 = new Answer(null, "12", false, question4);
        Answer answer24 = new Answer(null, "3", true, question4);
        Answer answer25 = new Answer(null, "4", false, question4);
        Answer answer26 = new Answer(null, "10", false, question4);

        Question question5 = new Question(null, "The IT capital of India is:", exam2);
        Answer answer27 = new Answer(null, "Bangalore", true, question5);
        Answer answer28 = new Answer(null, "Mumbai", false, question5);
        Answer answer29 = new Answer(null, "Mexico", false, question5);
        Answer answer30 = new Answer(null, "Hyderabad", false, question5);

        Exam exam3 = new Exam(null, "Android Quis", "Android Basic Test", "Online Android Quis", account1.getUsername());

        Question question6 = new Question(null, "Every Android application must have the file AndroidManifest.xml file in its root directory. Select one:", exam3);
        Answer answer31 = new Answer(null, "True", true, question6);
        Answer answer32 = new Answer(null, "False", false, question6);

        Question question7 = new Question(null, "Which among the following is not a member of Open Handset Alliance. Select one", exam3);
        Answer answer33 = new Answer(null, "Apple", true, question7);
        Answer answer34 = new Answer(null, "Wipro", false, question7);
        Answer answer35 = new Answer(null, "None of those", false, question7);
        Answer answer36 = new Answer(null, "Samsung", false, question7);

        Question question8 = new Question(null, "android:maxSdkVersion in AndroidManifest.xml is not recommended.", exam3);
        Answer answer37 = new Answer(null, "True", true, question8);
        Answer answer38 = new Answer(null, "False", false, question8);

        Question question9 = new Question(null, "Android is a linux based operating system. Select one:", exam3);
        Answer answer39 = new Answer(null, "True", true, question9);
        Answer answer40 = new Answer(null, "False", false, question9);

        Question question10 = new Question(null, "What is the use of AndroidManifest.xml file?", exam3);
        Answer answer41 = new Answer(null, "It describes the components of the application.", false, question10);
        Answer answer42 = new Answer(null, "It declares the minimum level of the Android API that the application requires.", false, question10);
        Answer answer43 = new Answer(null, "All", true, question10);
        Answer answer44 = new Answer(null, "It facilitates to provide a unique name for the application by specifying package name.", false, question10);

        Question question11 = new Question(null, "The root element of AndroidManifest.xml is. Select one:", exam3);
        Answer answer45 = new Answer(null, "application", false, question11);
        Answer answer46 = new Answer(null, "manifest", true, question11);
        Answer answer47 = new Answer(null, "activity", false, question11);
        Answer answer48 = new Answer(null, "action", false, question11);

        Question question12 = new Question(null, "It is not possible to create a user interface with out XML layout file. Select one:", exam3);
        Answer answer49 = new Answer(null, "True", true, question12);
        Answer answer50 = new Answer(null, "False", false, question12);

        Question question13 = new Question(null, "In Android, an XML layout file is used to design the contents of the user interface screen. Select one:", exam3);
        Answer answer51 = new Answer(null, "True", true, question13);
        Answer answer52 = new Answer(null, "False", false, question13);

        Question question14 = new Question(null, "What is the name of the class which is inherited to create a user interface screen? Select one:", exam3);
        Answer answer53 = new Answer(null, "ViewGroup", false, question14);
        Answer answer54 = new Answer(null, "View", false, question14);
        Answer answer55 = new Answer(null, "Activity", true, question14);
        Answer answer56 = new Answer(null, "None of these", false, question14);

        Question question15 = new Question(null, "The basic building element of Android's user interface is called:", exam3);
        Answer answer57 = new Answer(null, "View", true, question15);
        Answer answer58 = new Answer(null, "ContentProvider", false, question15);
        Answer answer59 = new Answer(null, "ViewGroup", false, question15);
        Answer answer60 = new Answer(null, "Layout", false, question15);

        Question question16 = new Question(null, "Specify the directory name where the XML layout file are stored. Select one:", exam3);
        Answer answer61 = new Answer(null, "/assets", false, question16);
        Answer answer62 = new Answer(null, "/src", false, question16);
        Answer answer63 = new Answer(null, "/res/values", false, question16);
        Answer answer64 = new Answer(null, "/res/layout", true, question16);

        Question question17 = new Question(null, "Android is initially developed by. Select one:", exam3);
        Answer answer65 = new Answer(null, "Google Inc", true, question17);
        Answer answer66 = new Answer(null, "Apple Inc", false, question17);
        Answer answer67 = new Answer(null, "Open Handset Alliance", false, question17);
        Answer answer68 = new Answer(null, "Android Inc", false, question17);

        Question question18 = new Question(null, "________ represents a single user interface screen. Select one:", exam3);
        Answer answer69 = new Answer(null, "Broadcast Reveiver", false, question18);
        Answer answer70 = new Answer(null, "Activity", true, question18);
        Answer answer71 = new Answer(null, "Service", false, question18);
        Answer answer72 = new Answer(null, "Content Provider", false, question18);

        Question question19 = new Question(null, "Is used to uniquely identify the framework API revision offered by a version of the Android Plataform. Select one:", exam3);
        Answer answer73 = new Answer(null, "Version Number", true, question19);
        Answer answer74 = new Answer(null, "Code Name", false, question19);
        Answer answer75 = new Answer(null, "API Level", false, question19);

        Question question20 = new Question(null, "Which attribute of the element <uses-sdk> is used to specify the minimum API Level required for the application to run?", exam3);
        Answer answer76 = new Answer(null, "android:targetSdkVersion", false, question20);
        Answer answer77 = new Answer(null, "android:maxSdkVersion", false, question20);
        Answer answer78 = new Answer(null, "android:minSdkVersion", true, question20);

        Activity activity1 = new Activity(null, account3.getUsername(), exam1.getExamTitle());
        Activity activity2 = new Activity(null, account4.getUsername(), exam2.getExamTitle());

        Choice choice1 = new Choice(null, question1.getQuestionTitle(), answer11.getAnswer(), true, activity1);
        Choice choice2 = new Choice(null, question2.getQuestionTitle(), answer21.getAnswer(), true, activity1);
        Choice choice3 = new Choice(null, question3.getQuestionTitle(), answer32.getAnswer(), true, activity2);

        account3.addAtivity(activity1);
        account4.addAtivity(activity2);

        activity1.addAccount(account1);
        activity2.addAccount(account2);

        question1.addAnswers(Arrays.asList(answer11, answer12, answer13, answer14));
        question2.addAnswers(Arrays.asList(answer15, answer16, answer17, answer18));
        question3.addAnswers(Arrays.asList(answer19, answer20, answer21, answer22));
        question4.addAnswers(Arrays.asList(answer23, answer24, answer25, answer26));
        question5.addAnswers(Arrays.asList(answer27, answer28, answer29, answer30));
        question6.addAnswers(Arrays.asList(answer31, answer32));
        question7.addAnswers(Arrays.asList(answer33, answer34, answer35, answer36));
        question8.addAnswers(Arrays.asList(answer37, answer38));
        question9.addAnswers(Arrays.asList(answer39, answer40));
        question10.addAnswers(Arrays.asList(answer41, answer42, answer43, answer44));
        question11.addAnswers(Arrays.asList(answer45, answer46, answer47, answer48));
        question12.addAnswers(Arrays.asList(answer49, answer50));
        question13.addAnswers(Arrays.asList(answer51, answer52));
        question14.addAnswers(Arrays.asList(answer53, answer54, answer55, answer56));
        question15.addAnswers(Arrays.asList(answer57, answer58, answer59, answer60));
        question16.addAnswers(Arrays.asList(answer61, answer62, answer63, answer64));
        question17.addAnswers(Arrays.asList(answer65, answer66, answer67, answer68));
        question18.addAnswers(Arrays.asList(answer69, answer70, answer71, answer72));
        question19.addAnswers(Arrays.asList(answer73, answer74, answer75));
        question20.addAnswers(Arrays.asList(answer76, answer77, answer78));

        question1.setAssertion("INSERT");
        question2.setAssertion("limits the row data are returned");
        question3.setAssertion("All of the above");
        question4.setAssertion("3");
        question5.setAssertion("Bangalore");
        question6.setAssertion("True");
        question7.setAssertion("Apple");
        question8.setAssertion("True");
        question9.setAssertion("True");
        question10.setAssertion("All");
        question11.setAssertion("manifest");
        question12.setAssertion("Tru");
        question13.setAssertion("True");
        question14.setAssertion("Activity");
        question15.setAssertion("View");
        question16.setAssertion("/res/values");
        question17.setAssertion("Google Inc");
        question18.setAssertion("Activity");
        question19.setAssertion("Version Number");
        question20.setAssertion("android:minSdkVersion");

        exam1.addQuestions(Arrays.asList(question1, question2, question3));
        exam2.addQuestions(Arrays.asList(question4, question5));
        exam3.addQuestions(Arrays.asList(question6, question7, question8, question9, question10, question11, question12,
                question13, question14, question15, question16, question17, question18, question19, question20));

        exam1.setActivity(activity1);
        exam2.setActivity(activity2);

        List<Exam> exams = new ArrayList<>(Arrays.asList(exam1, exam2, exam3));

        List<Question> questions = new ArrayList<>(Arrays.asList(question6, question7, question8, question9, question10,
                question11, question12, question13, question14, question15, question16, question17, question18, question19, question20));

        List<Answer> answers = new ArrayList<>(Arrays.asList(answer11, answer12, answer13, answer14, answer15, answer16, answer17,
                answer18, answer19, answer20, answer21, answer22, answer23, answer24, answer25, answer26, answer27, answer28, answer29,
                answer30, answer31, answer32, answer33, answer34, answer35, answer36, answer37, answer38, answer39, answer40, answer41,
                answer42, answer43, answer44, answer45, answer46, answer47, answer48, answer49, answer50, answer51, answer52, answer53,
                answer54, answer55, answer56, answer57, answer58, answer59, answer60, answer61, answer62, answer63, answer64, answer65,
                answer66, answer67, answer68, answer69, answer70, answer71, answer72, answer73, answer74, answer75, answer76, answer77, answer78));

        List<Choice> choices = new ArrayList<>(Arrays.asList(choice1, choice2, choice3));
        List<Activity> activities = new ArrayList<>(Arrays.asList(activity1, activity2));
        List<Account> accounts = new ArrayList<>(Arrays.asList(account1, account2, account3, account4, account5, account6, account7,
                account8, account9, account10, account11, account12, account13, account14, account15, account16, account17,
                account18, account19, account20, account21, account22, account23, account24));


        for (Account account : accounts) accountService.saveAccount(account);
        for (Activity activity : activities) activityService.saveActivity(activity);
        for (Exam exam : exams) examService.saveExam(exam);
        for (Question question : questions) questionService.saveQuestion(question);
        for (Answer answer : answers) answerService.saveAnswer(answer);

        for (Choice choice : choices) choiceService.saveChoice(choice);
    }
}
