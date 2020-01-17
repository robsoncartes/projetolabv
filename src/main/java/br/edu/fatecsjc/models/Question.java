package br.edu.fatecsjc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "questions")
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String question;

    @Getter
    private String assertion;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    @Getter
    @Setter
    private List<Answer> answers = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @Getter
    @Setter
    private Exam exam;

    public Question(Integer id, String question, Exam exam) {
        this.id = id;
        this.question = question;
        this.exam = exam;
    }

    public void setAssertion(String assertion) {

        for (Answer answer : getAnswers()) {
            if (answer.getAnswer().equals(assertion)) {
                this.assertion = assertion;
            }
        }
    }

    public void addAnswer(Answer answer) {
        this.getAnswers().add(answer);
    }

    public void addAnswers(List<Answer> answers) {
        this.getAnswers().addAll(answers);
    }

    public boolean isValid(String questionTitle) {
        return this.getQuestion().equals(questionTitle);
    }
}