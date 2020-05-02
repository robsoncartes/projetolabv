package br.edu.fatecsjc.models;

import br.edu.fatecsjc.models.enums.AuthorityName;
import br.edu.fatecsjc.models.views.AccountView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "accounts")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({AccountView.AccountLogin.class})
    private Long id;

    @JsonView({AccountView.AccountLogin.class})
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @JsonView({AccountView.AccountLogin.class})
    private String username;

    @JsonView({AccountView.AccountComplete.class})
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authority_names")
    private Set<Integer> authorities = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "accounts")
    //@JsonView({AccountView.AccountComplete.class})
    private List<Activity> activities = new ArrayList<>();

    public Account() {
        addAuthorityName(AuthorityName.USER);
    }

    public Account(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        addAuthorityName(AuthorityName.USER);
    }

    public void addAuthorityName(AuthorityName authorityName) {
        authorities.add(authorityName.getCode());
    }

    @JsonView(AccountView.AccountComplete.class)
    public Set<AuthorityName> getAuthorityNames() {
        return authorities.stream().map(AuthorityName::toEnum).collect(Collectors.toSet());
    }

    public void addAtivity(Activity activity) {
        getActivities().add(activity);
    }
}
