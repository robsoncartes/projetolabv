package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.models.views.QuestionView;
import br.edu.fatecsjc.services.QuestionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController("QuestionController")
@RequestMapping(value = "/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @JsonView(QuestionView.QuestionSimple.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Question> findQuestionById(@PathVariable Integer id) {

        Question question = questionService.findById(id);

        return ResponseEntity.ok().body(question);
    }

    @JsonView(QuestionView.QuestionComplete.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Question>> findQuestions() {

        List<Question> questions = questionService.findQuestions();

        return ResponseEntity.ok().body(questions);
    }

    @JsonView(QuestionView.QuestionSave.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertQuestion(@Valid @RequestBody Question question) {

        Question obj = questionService.saveQuestion(question);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
