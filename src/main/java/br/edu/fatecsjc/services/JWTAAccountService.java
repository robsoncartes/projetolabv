package br.edu.fatecsjc.services;

import br.edu.fatecsjc.security.JWTAccount;

public interface JWTAAccountService {

    JWTAccount getAccountAuthenticated();
}
