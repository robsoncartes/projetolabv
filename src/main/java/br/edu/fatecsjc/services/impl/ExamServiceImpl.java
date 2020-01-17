package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Exam;
import br.edu.fatecsjc.repositories.ExamRepository;
import br.edu.fatecsjc.services.ExamService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam findExamById(Integer id) {

        Exam exam = examRepository.findById(id).orElse(null);

        if (exam == null)
            throw new ObjectNotFoundException("Exam not found. Id: " + id + ", Type: " + Exam.class.getName());

        return exam;
    }

    @Override
    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void saveExams(List<Exam> exams) {
        examRepository.saveAll(exams);
    }
}
