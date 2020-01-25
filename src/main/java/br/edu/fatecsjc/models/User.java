package br.edu.fatecsjc.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@PrimaryKeyJoinColumn(name = "account_id")
public class User extends Account {

    public User(Long id, String email, String username, String password) {
        super(id, email, username, password);
    }
}
