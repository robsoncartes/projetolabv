package br.edu.fatecsjc.models;

import br.edu.fatecsjc.models.views.ActivityView;
import br.edu.fatecsjc.models.views.ChoiceView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "choices")
@Data
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ChoiceView.ChoiceSimple.class, ActivityView.ActivityComplete.class})
    private Integer id;

    @JsonView({ChoiceView.ChoiceSimple.class, ActivityView.ActivityComplete.class})
    private String questionTitle;

    @JsonView(ChoiceView.ChoiceComplete.class)
    private String answer;

    @JsonView(ChoiceView.ChoiceComplete.class)
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    // @JsonView(ChoiceView.ChoiceComplete.class)
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
