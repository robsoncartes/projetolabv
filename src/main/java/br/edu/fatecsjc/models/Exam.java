package br.edu.fatecsjc.models;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String type;
    private String description;
    private String author;

    @OneToMany(mappedBy = "exam")
    private List<Question> questions = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Exam() {
    }

    public Exam(Integer id, String title, String type, String description, String author) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.author = author;
    }

    public Question getQuestion(String questionTitle) {

        for (Question question : getQuestions())
            if (question.isValid(questionTitle))
                return question;

        return null;
    }

    public void addQuestion(Question question) {
        this.getQuestions().add(question);
    }

    public void addQuestions(List<Question> questions) {
        this.getQuestions().addAll(questions);
    }

    public boolean isValid(Exam exam) {
        return this.getTitle().equals(exam.getTitle());
    }
}
