package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Exam;
import br.edu.fatecsjc.repositories.ExamRepository;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public Exam findById(Integer id) {

        Exam exam = examRepository.findById(id).orElse(null);

        if (exam == null)
            throw new ObjectNotFoundException("Exame não encontrado. Id: " + id + ", Tipo: " + Exam.class.getName());

        return exam;
    }

    @Transactional
    public Exam saveExam(Exam exam) {

        Exam obj = examRepository.findByTitle(exam.getTitle());

        if (obj == null) {
            exam.setId(null);
            exam.setTitle(exam.getTitle());
            exam.setType(exam.getType());
            exam.setDescription(exam.getDescription());
            exam.setAuthor(exam.getAuthor());

            return examRepository.save(exam);
        } else
            throw new DataIntegrityException("Já existe um exame com este título.");
    }

    public Iterable<Exam> findExams() {

        return examRepository.findAll();
    }
}
