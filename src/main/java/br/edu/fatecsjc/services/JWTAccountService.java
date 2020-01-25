package br.edu.fatecsjc.services;

import br.edu.fatecsjc.security.JWTAccount;

public interface JWTAccountService {

    JWTAccount getAccountAuthenticated();
}
