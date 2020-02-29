package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.models.views.AccountView;
import br.edu.fatecsjc.services.AccountService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController("AccountController")
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(AccountView.AccountComplete.class)
    public ResponseEntity<Account> findAccountById(@PathVariable Long id) {

        Account account = accountService.findById(id);

        return ResponseEntity.ok().body(account);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    @JsonView(AccountView.AccountComplete.class)
    public ResponseEntity<Account> findAccountByEmail(@RequestParam(value = "value") String email) {

        Account account = accountService.findByEmail(email);

        return ResponseEntity.ok().body(account);
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(AccountView.AccountLogin.class)
    public ResponseEntity<Iterable<Account>> findAllAccounts() {

        Iterable<Account> accounts = accountService.findAccounts();

        return ResponseEntity.ok().body(accounts);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertAccount(@Valid @RequestBody Account account) {

        Account obj = accountService.saveAccount(account);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
