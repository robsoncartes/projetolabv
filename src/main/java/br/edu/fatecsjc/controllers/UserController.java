package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.User;
import br.edu.fatecsjc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController("UserAcontroller")
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertUser(@Valid @RequestBody User user) {

        User obj = userService.saveUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
