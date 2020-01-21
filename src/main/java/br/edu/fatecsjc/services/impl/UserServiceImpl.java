package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.User;
import br.edu.fatecsjc.repositories.UserRepository;
import br.edu.fatecsjc.services.UserService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null)
            throw new ObjectNotFoundException("User not found. Id: " + id + ", Type: " + User.class.getName());

        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public Iterable<User> findUsers() {

        return userRepository.findAll();
    }
}
