package br.edu.fatecsjc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer id;

    private String username;
    private String examTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "activity")
    private List<Choice> choices = new LinkedList<>();

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
