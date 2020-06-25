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

import java.util.List;

@RestController("AnswerController")
@RequestMapping(value = "/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @JsonView(AnswerView.AnswerSimple.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Answer> findAnswerById(@PathVariable Integer id) {

        Answer answer = answerService.findById(id);

        return ResponseEntity.ok().body(answer);
    }

    @JsonView(AnswerView.AnswerComplete.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Answer>> findAllAnswers() {

        List<Answer> answers = answerService.findAnswers();

        return ResponseEntity.ok().body(answers);
    }
}
