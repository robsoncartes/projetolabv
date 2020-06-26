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

    @JsonView(ExamView.ExamSimple.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ExamView.ExamSimple.class)
    @NotNull
    @Column(unique = true)
    private String examTitle;

    @JsonView(ExamView.ExamComplete.class)
    @NotNull
    private String type;

    @JsonView(ExamView.ExamComplete.class)
    @NotNull
    private String description;

    @JsonView(ExamView.ExamComplete.class)
    @NotNull
    private String author;

    @JsonView(ExamView.ExamComplete.class)
    // @JsonIgnore
    @OneToMany(mappedBy = "exam")
    private List<Question> questions = new LinkedList<>();

    @JsonView(ExamView.ExamComplete.class)
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Exam() {
    }

    public Exam(Integer id, String examTitle, String type, String description, String author) {
        this.id = id;
        this.examTitle = examTitle;
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
        return this.getExamTitle().equals(exam.getExamTitle());
    }
}
