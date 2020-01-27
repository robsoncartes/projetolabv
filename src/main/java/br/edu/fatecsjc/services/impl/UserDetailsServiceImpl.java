package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Account;
import br.edu.fatecsjc.repositories.AccountRepository;
import br.edu.fatecsjc.security.JWTAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(email);

        if (account == null)
            throw new UsernameNotFoundException(email);

        return new JWTAccount(account.getId(), account.getEmail(), account.getPassword(), account.getAuthorityNames());
    }
}
