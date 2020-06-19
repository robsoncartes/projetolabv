package br.edu.fatecsjc.models;

import br.edu.fatecsjc.models.views.ActivityView;
import br.edu.fatecsjc.models.views.ExamView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "activities")
@Data
public class Activity {

    @JsonView(ActivityView.ActivitySimple.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ActivityView.ActivitySimple.class)
    @NotNull
    private String username;

    @ManyToMany()
    @JoinTable(name = "activity_account", joinColumns = @JoinColumn(name = "activity_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts = new ArrayList<>();

    @JsonView({ActivityView.ActivitySimple.class, ExamView.ExamComplete.class})
    @NotNull
    private String examTitle;

    @JsonView(ActivityView.ActivityComplete.class)
    @OneToMany(mappedBy = "activity")
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

    public boolean contains(String questionTitle) {

        if (!(getChoices() == null)) {
            for (Choice choice : getChoices())
                if (choice.getQuestionTitle().equals(questionTitle))
                    return true;
        }

        return false;
    }

    public void addAccount(Account account) {
        this.getAccounts().add(account);
    }
}
