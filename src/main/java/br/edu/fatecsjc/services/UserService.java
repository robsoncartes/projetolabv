package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    User findById(Long id);

    @Transactional
    User saveUser(User user);

    List<User> findUsers();
}
