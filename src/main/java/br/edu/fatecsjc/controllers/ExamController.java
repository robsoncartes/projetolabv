package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Exam;
import br.edu.fatecsjc.models.views.ExamView;
import br.edu.fatecsjc.services.ExamService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController("ExamController")
@RequestMapping(value = "/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @JsonView(ExamView.ExamComplete.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Exam> findExamById(@PathVariable Integer id) {

        Exam exam = examService.findById(id);

        return ResponseEntity.ok().body(exam);
    }

    @JsonView(ExamView.ExamComplete.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Exam>> findAllExams() {

        List<Exam> exams = examService.findExams();

        return ResponseEntity.ok().body(exams);
    }

    @JsonView(ExamView.ExamSave.class)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertExam(@Valid @RequestBody Exam exam) {

        Exam obj = examService.saveExam(exam);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteExam(@PathVariable Integer id){

        examService.deleteExamById(id);

        return ResponseEntity.noContent().build();
    }

}
