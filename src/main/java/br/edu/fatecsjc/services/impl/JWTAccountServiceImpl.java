package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.security.JWTAccount;
import br.edu.fatecsjc.services.JWTAAccountService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JWTAccountServiceImpl implements JWTAAccountService {

    @Override
    public JWTAccount getAccountAuthenticated() {

        try {
            return (JWTAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
