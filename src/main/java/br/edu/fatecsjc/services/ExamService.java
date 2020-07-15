package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Exam;

import javax.transaction.Transactional;
import java.util.List;

public interface ExamService {

    Exam findById(Integer id);

    @Transactional
    Exam saveExam(Exam exam);

    List<Exam> findExams();
}
