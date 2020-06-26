package br.edu.fatecsjc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@PrimaryKeyJoinColumn(name = "account_id")
@EqualsAndHashCode(callSuper = false)
public class User extends Account {

    public User(Long id, String email, String username, String password) {
        super(id, email, username, password);
    }

    //@JsonView({AccountView.AccountComplete.class})
    @JsonIgnore
    @ManyToMany(mappedBy = "accounts")
    private List<Activity> activities = new ArrayList<>();

    public void addAtivity(Activity activity) {
        getActivities().add(activity);
    }
}
