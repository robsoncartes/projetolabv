package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.security.JWTAccount;
import br.edu.fatecsjc.services.JWTAccountService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JWTAccountServiceImpl implements JWTAccountService {

    @Override
    public JWTAccount getAccountAuthenticated() {

        try {
            return (JWTAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
