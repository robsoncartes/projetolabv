package br.edu.fatecsjc.models;

import br.edu.fatecsjc.models.views.ExamView;
import br.edu.fatecsjc.models.views.QuestionView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @JsonView({QuestionView.QuestionSimple.class, ExamView.ExamComplete.class})
    private Integer id;

    @Getter
    @Setter
    @JsonView({QuestionView.QuestionSimple.class, ExamView.ExamComplete.class})
    @NotNull
    private String question;

    @Getter
    @JsonView(QuestionView.QuestionComplete.class)
    private String assertion;

    //@JsonIgnore
    @OneToMany(mappedBy = "question")
    @Getter
    @Setter
    @JsonView({QuestionView.QuestionComplete.class, ExamView.ExamComplete.class})
    private List<Answer> answers = new LinkedList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exam_id")
    @Getter
    @Setter
    @JsonView({QuestionView.QuestionComplete.class, ExamView.ExamComplete.class})
    @NotNull
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
                break;
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