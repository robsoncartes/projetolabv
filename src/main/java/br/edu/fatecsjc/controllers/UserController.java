package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.User;
import br.edu.fatecsjc.models.views.AccountView;
import br.edu.fatecsjc.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController("UserAcontroller")
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @JsonView(AccountView.AccountLogin.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserById(@PathVariable Long id) {

        User user = userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @JsonView(AccountView.AccountComplete.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() {

        List<User> users = userService.findUsers();

        return ResponseEntity.ok().body(users);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertUser(@Valid @RequestBody User user) {

        User obj = userService.saveUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
