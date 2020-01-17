package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Administrator;
import br.edu.fatecsjc.services.impl.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdministratorController")
@RequestMapping(value = "/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorServiceImpl administratorService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Administrator> findAdministratorById(@PathVariable Long id) {

        Administrator administrator = administratorService.findById(id);

        return ResponseEntity.ok().body(administrator);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Administrator>> findAllAdministrators() {

        Iterable<Administrator> administrators = administratorService.findAdministrators();

        return ResponseEntity.ok().body(administrators);
    }
}
