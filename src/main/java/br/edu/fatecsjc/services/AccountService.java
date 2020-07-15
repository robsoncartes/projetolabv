package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Account;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountService {

    Account findById(Long id);

    Account findByEmail(String email);

    @Transactional
    Account saveAccount(Account account);

    Account updateAccount(Account account);

    void updateData(Account newAccount, Account account);

    void deleteAccountById(Long id);

    List<Account> findAccounts();

    Page<Account> searchPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
