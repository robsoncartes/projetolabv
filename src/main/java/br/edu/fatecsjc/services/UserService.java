package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    void saveUser(User user);

    void saveUsers(List<User> users);
}
