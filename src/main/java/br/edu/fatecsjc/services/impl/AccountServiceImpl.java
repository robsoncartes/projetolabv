package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.models.enums.AuthorityName;
import br.edu.fatecsjc.repositories.AccountRepository;
import br.edu.fatecsjc.security.JWTAccount;
import br.edu.fatecsjc.services.AccountService;
import br.edu.fatecsjc.services.JWTAccountService;
import br.edu.fatecsjc.services.exceptions.AuthorizationException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTAccountService jwtAccountService;

    @Override
    public Account findById(Long id) {

        JWTAccount jwtAccount = jwtAccountService.getAccountAuthenticated();

        if (jwtAccount == null || !jwtAccount.hasHole(AuthorityName.ADMINISTRATOR) && !id.equals(jwtAccount.getId()))
            throw new AuthorizationException("Acesso negado.");

        Account account = accountRepository.findById(id).orElse(null);

        if (account == null)
            throw new ObjectNotFoundException("Accouunt not found. Id: " + id + ", Type: " + Account.class.getName());

        return account;
    }

    @Override
    public Account findByEmail(String email) {

        JWTAccount jwtAccount = jwtAccountService.getAccountAuthenticated();

        if (jwtAccount == null || !jwtAccount.hasHole(AuthorityName.ADMINISTRATOR) && !email.equals(jwtAccount.getUsername()))
            throw new AuthorizationException("Acesso negado.");

        Account account = accountRepository.findByEmail(email);

        if (account == null)
            throw new ObjectNotFoundException("Account not found. Email: " + email + ", Type: " + Account.class.getName());

        return account;
    }

    @Override
    public Account saveAccount(Account account) {
        account.setId(null);
        return accountRepository.save(account);
    }

    @Override
    public void saveAccounts(List<Account> accounts) {
        accountRepository.saveAll(accounts);
    }

    @Override
    public Iterable<Account> findAccounts() {

        return accountRepository.findAll();
    }
}
