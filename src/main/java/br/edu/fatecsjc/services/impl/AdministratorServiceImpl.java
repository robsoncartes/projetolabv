package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Administrator;
import br.edu.fatecsjc.repositories.AdministratorRepository;
import br.edu.fatecsjc.services.AdministratorService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Administrator findById(Long id) {

        Administrator administrator = administratorRepository.findById(id).orElse(null);

        if (administrator == null)
            throw new ObjectNotFoundException("Administrator not found. Id: " + id + ", Type: " + Administrator.class.getName());

        return administrator;
    }

    @Override
    public Administrator saveAdministrator(Administrator administrator) {
        administrator.setId(null);
        return administratorRepository.save(administrator);
    }

    @Override
    public void saveAdministrators(List<Administrator> administrators) {
        administratorRepository.saveAll(administrators);
    }

    @Override
    public Iterable<Administrator> findAdministrators() {

        return administratorRepository.findAll();
    }
}
