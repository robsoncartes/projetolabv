package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Exam;

import java.util.List;

public interface ExamService {

    Exam findById(Integer id);

    void saveExam(Exam exam);

    void saveExams(List<Exam> exams);

    Iterable<Exam> findExams();
}
