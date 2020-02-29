package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.models.enums.AuthorityName;
import br.edu.fatecsjc.repositories.AccountRepository;
import br.edu.fatecsjc.security.JWTAccount;
import br.edu.fatecsjc.services.exceptions.AuthorizationException;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTAccountService jwtAccountService;

    public Account findById(Long id) {

        JWTAccount jwtAccount = jwtAccountService.getAccountAuthenticated();

        if (jwtAccount == null || !jwtAccount.hasHole(AuthorityName.ADMINISTRATOR) && !id.equals(jwtAccount.getId()))
            throw new AuthorizationException("Access denied.");

        Account account = accountRepository.findById(id).orElse(null);

        if (account == null)
            throw new ObjectNotFoundException("Account not found. Id: " + id + ", Type: " + Account.class.getName());

        return account;
    }

    public Account findByEmail(String email) {

        JWTAccount jwtAccount = jwtAccountService.getAccountAuthenticated();

        if (jwtAccount == null || !jwtAccount.hasHole(AuthorityName.ADMINISTRATOR) && !email.equals(jwtAccount.getUsername()))
            throw new AuthorizationException("Access denied.");

        Account account = accountRepository.findByEmail(email);

        if (account == null)
            throw new ObjectNotFoundException("Account not found. Email: " + email + ", Type: " + Account.class.getName());

        return account;
    }

    @Transactional
    public Account saveAccount(Account account) {

        Account obj = accountRepository.findByEmail(account.getEmail());

        if (obj == null) {
            account.setId(null);
            return accountRepository.save(account);
        } else
            throw new DataIntegrityException("Email already exist");
    }

    public Iterable<Account> findAccounts() {

        return accountRepository.findAll();
    }
}
