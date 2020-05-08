package br.edu.fatecsjc.integration.repositories;

import br.edu.fatecsjc.factories.AccountFactory;
import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.repositories.AccountRepository;
import br.edu.fatecsjc.utils.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

// import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
public class AccountRepositoryTest extends TestUtil {

    @Autowired
    private AccountRepository accountRepository;

    // FIXME

    @Test
    public void shouldBeSaved() {

        Account account = accountRepository.save(AccountFactory.validAccount(new Account()));

        Account saved = accountRepository.findByEmail(account.getEmail());
        assertEquals(saved.getEmail(), account.getEmail());
        assertEquals("is a valid object", getErrorMessage(saved));
    }
}
