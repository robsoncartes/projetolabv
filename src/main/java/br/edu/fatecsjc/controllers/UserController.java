package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.User;
import br.edu.fatecsjc.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("UserAcontroller")
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserById(@PathVariable Long id) {

        User user = userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> findAllUsers() {

        Iterable<User> users = userService.findUsers();

        return ResponseEntity.ok().body(users);
    }
}
