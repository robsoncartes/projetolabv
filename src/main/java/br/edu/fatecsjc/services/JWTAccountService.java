package br.edu.fatecsjc.services;

import br.edu.fatecsjc.security.JWTAccount;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JWTAccountService {

    public JWTAccount getAccountAuthenticated() {

        try {
            return (JWTAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
