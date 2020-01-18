package br.edu.fatecsjc.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "choices")
@Data
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String answer;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Choice() {
    }

    public Choice(Integer id, String questionTitle, String answer, boolean correct, Activity activity) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.answer = answer;
        this.correct = correct;
        this.activity = activity;
    }

    public boolean isValid(Choice choice) {

        return this.getQuestionTitle().equals(choice.getQuestionTitle()) && this.getAnswer().equals(choice.getAnswer());
    }
}
