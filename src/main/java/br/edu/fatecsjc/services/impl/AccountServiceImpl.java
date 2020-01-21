package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.repositories.AccountRepository;
import br.edu.fatecsjc.services.AccountService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(Long id) {

        Account account = accountRepository.findById(id).orElse(null);

        if (account == null)
            throw new ObjectNotFoundException("Accouunt not found. Id: " + id + ", Type: " + Account.class.getName());

        return account;
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
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
