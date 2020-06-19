package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.User;
import br.edu.fatecsjc.repositories.UserRepository;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null)
            throw new ObjectNotFoundException("Usuário não encontrado. Id: " + id + ", Tipo: " + User.class.getName());

        return user;
    }

    public User saveUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public List<User> findUsers() {

        return userRepository.findAll();
    }
}
