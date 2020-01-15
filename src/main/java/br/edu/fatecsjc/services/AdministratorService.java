package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Administrator;

import java.util.List;

public interface AdministratorService {

    Administrator findById(Long id);

    void saveAdministrator(Administrator administrator);

    void saveAdministrators(List<Administrator> administrators);
}
