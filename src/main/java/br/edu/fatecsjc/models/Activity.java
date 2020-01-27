package br.edu.fatecsjc.models;

import br.edu.fatecsjc.models.views.ActivityView;
import br.edu.fatecsjc.models.views.ExamView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "activities")
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ActivityView.ActivitySimple.class)
    private Integer id;

    @JsonView(ActivityView.ActivitySimple.class)
    private String username;

    @JsonView({ActivityView.ActivitySimple.class, ExamView.ExamComplete.class})
    private String examTitle;

    // @JsonIgnore
    @OneToMany(mappedBy = "activity")
    @JsonView(ActivityView.ActivityComplete.class)
    private List<Choice> choices = new LinkedList<>();

    @JsonView(ActivityView.ActivityComplete.class)
    private boolean complete;

    public Activity() {
    }

    public Activity(Integer id, String username, String examTitle) {
        this.id = id;
        this.username = username;
        this.examTitle = examTitle;
        this.setComplete(false);
    }

    public void addChoice(Choice choice) {

        if (this.getChoices() == null)
            this.setChoices(new LinkedList<>());
        this.getChoices().add(choice);
    }
}
