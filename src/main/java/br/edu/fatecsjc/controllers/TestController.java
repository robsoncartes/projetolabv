package br.edu.fatecsjc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTeste(){

        return "Testando REST.";
    }
}
