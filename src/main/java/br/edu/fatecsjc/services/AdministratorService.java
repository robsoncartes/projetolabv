package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Administrator;
import br.edu.fatecsjc.repositories.AdministratorRepository;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator findById(Long id) {

        Administrator administrator = administratorRepository.findById(id).orElse(null);

        if (administrator == null)
            throw new ObjectNotFoundException("Administrador não encontrado. Id: " + id + ", Tipo: " + Administrator.class.getName());

        return administrator;
    }

    public Administrator saveAdministrator(Administrator administrator) {
        administrator.setId(null);
        return administratorRepository.save(administrator);
    }

    public List<Administrator> findAdministrators() {

        return administratorRepository.findAll();
    }
}
