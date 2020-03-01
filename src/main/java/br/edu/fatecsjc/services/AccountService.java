package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.models.enums.AuthorityName;
import br.edu.fatecsjc.repositories.AccountRepository;
import br.edu.fatecsjc.security.JWTAccount;
import br.edu.fatecsjc.services.exceptions.AuthorizationException;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTAccountService jwtAccountService;

    @Autowired
    private HttpServletRequest servletRequest;

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

    public Account updateAccount(Account account) {

        Map<String, String> map = (Map<String, String>) servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));

        Account newAccount = accountRepository.findByEmail(account.getEmail());

        if (newAccount != null && newAccount.getId().equals(uriId)) {
            updateData(newAccount, account);
            return accountRepository.save(newAccount);
        } else
            throw new DataIntegrityException("Email existente");
    }

    public void updateData(Account newAccount, Account account) {
        newAccount.setUsername(account.getUsername());
        newAccount.setEmail(account.getEmail());
    }

    public Iterable<Account> findAccounts() {

        return accountRepository.findAll();
    }

    public Page<Account> searchPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return accountRepository.findAll(pageRequest);
    }
}
