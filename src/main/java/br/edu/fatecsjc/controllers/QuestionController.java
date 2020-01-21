package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.services.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("QuestionController")
@RequestMapping(value = "/questions")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Question> findQuestionById(@PathVariable Integer id) {

        Question question = questionService.findById(id);

        return ResponseEntity.ok().body(question);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Question>> findQuestions() {

        Iterable<Question> questions = questionService.findQuestions();

        return ResponseEntity.ok().body(questions);
    }
}
