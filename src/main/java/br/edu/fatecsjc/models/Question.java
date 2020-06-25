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

    @JsonView({QuestionView.QuestionSimple.class, ExamView.ExamComplete.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @JsonView({QuestionView.QuestionSimple.class, ExamView.ExamComplete.class})
    @Getter
    @Setter
    @NotNull
    private String questionTitle;

    @JsonView(QuestionView.QuestionComplete.class)
    @Getter
    private String assertion;

    @JsonView({QuestionView.QuestionComplete.class, ExamView.ExamComplete.class})
    //@JsonIgnore
    @OneToMany(mappedBy = "question")
    @Getter
    @Setter
    private List<Answer> answers = new LinkedList<>();

    @JsonView({QuestionView.QuestionComplete.class, ExamView.ExamComplete.class})
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exam_id")
    @Getter
    @Setter
    @NotNull
    private Exam exam;

    public Question(Integer id, String questionTitle, Exam exam) {
        this.id = id;
        this.questionTitle = questionTitle;
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
        return this.getQuestionTitle().equals(questionTitle);
    }


}