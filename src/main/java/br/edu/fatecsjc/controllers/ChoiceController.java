package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Choice;
import br.edu.fatecsjc.models.views.ChoiceView;
import br.edu.fatecsjc.services.ChoiceService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("ChoiceController")
@RequestMapping(value = "/choices")
public class ChoiceController {

    @Autowired
    private ChoiceService choiceService;

    @JsonView(ChoiceView.ChoiceSimple.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Choice> findChoiceById(@PathVariable Integer id) {

        Choice choice = choiceService.findById(id);

        return ResponseEntity.ok().body(choice);
    }

    @JsonView(ChoiceView.ChoiceComplete.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Choice>> findAllChoices() {

        List<Choice> choices = choiceService.findChoices();

        return ResponseEntity.ok().body(choices);
    }
}
