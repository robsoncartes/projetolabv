package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.models.Administrator;
import br.edu.fatecsjc.models.User;
import br.edu.fatecsjc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private AccountServiceImpl accountService;

    @Override
    public void instantiateTestDatabase() throws Exception {

        Account account1 = new Administrator(null, "admin1", "admin1");
        Account account2 = new User(null, "user1", "user1");
        Account account3 = new Administrator(null, "admin2", "admin2");
        Account account4 = new User(null, "user2", "user2");
        accountService.saveAccounts(Arrays.asList(account1, account2, account3));
        accountService.saveAccount(account4);
    }
}
