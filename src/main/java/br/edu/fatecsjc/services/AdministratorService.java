package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Administrator;

import javax.transaction.Transactional;
import java.util.List;

public interface AdministratorService {

    Administrator findById(Long id);

    @Transactional
    Administrator saveAdministrator(Administrator administrator);

    List<Administrator> findAdministrators();
}
