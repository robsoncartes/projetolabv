package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.models.views.AccountView;
import br.edu.fatecsjc.services.AccountService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(AccountView.AccountComplete.class)
    public ResponseEntity<Void> insertAccount(@Valid @RequestBody Account account) {

        Account newAccount = accountService.newAccount(account);
        Account obj = accountService.saveAccount(newAccount);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAccount(@Valid @RequestBody Account account, @PathVariable Long id) {

        account.setId(id);
        accountService.updateAccount(account);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccountById(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(AccountView.AccountLogin.class)
    public ResponseEntity<Iterable<Account>> findAllAccounts() {

        Iterable<Account> accounts = accountService.findAccounts();

        return ResponseEntity.ok().body(accounts);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Account>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "username") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Account> accounts = accountService.searchPage(page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(accounts);
    }
}
