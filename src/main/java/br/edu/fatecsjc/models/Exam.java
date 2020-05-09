package br.edu.fatecsjc.models;

import br.edu.fatecsjc.models.views.ExamView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ExamView.ExamSimple.class)
    private Integer id;

    @JsonView(ExamView.ExamSimple.class)
    @NotNull
    private String title;

    @JsonView(ExamView.ExamComplete.class)
    @NotNull
    private String type;

    @JsonView(ExamView.ExamComplete.class)
    @NotNull
    private String description;

    @JsonView(ExamView.ExamComplete.class)
    @NotNull
    private String author;

    // @JsonIgnore
    @OneToMany(mappedBy = "exam")
    @JsonView(ExamView.ExamComplete.class)
    private List<Question> questions = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @JsonView(ExamView.ExamComplete.class)
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
