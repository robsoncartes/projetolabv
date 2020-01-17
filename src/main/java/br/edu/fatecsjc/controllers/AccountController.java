package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.services.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("AccountController")
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccountById(@PathVariable Long id) {

        Account account = accountService.findById(id);

        return ResponseEntity.ok().body(account);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Account>> findAllAccounts() {

        Iterable<Account> accounts = accountService.findAccounts();

        return ResponseEntity.ok().body(accounts);
    }
}
