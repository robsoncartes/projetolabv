package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Answer;
import br.edu.fatecsjc.models.views.AnswerView;
import br.edu.fatecsjc.services.AnswerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("AnswerController")
@RequestMapping(value = "/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(AnswerView.AnswerSimple.class)
    public ResponseEntity<Answer> findAnswerById(@PathVariable Integer id) {

        Answer answer = answerService.findById(id);

        return ResponseEntity.ok().body(answer);
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(AnswerView.AnswerComplete.class)
    public ResponseEntity<Iterable<Answer>> findAllAnswers() {

        Iterable<Answer> answers = answerService.findAnswers();

        return ResponseEntity.ok().body(answers);
    }
}
