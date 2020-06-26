package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Exam;
import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.repositories.ExamRepository;
import br.edu.fatecsjc.repositories.QuestionRepository;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    public Question findById(Integer id) {

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null)
            throw new ObjectNotFoundException("Questão não encontrada. Id: " + id + ", Tipo: " + Question.class.getName());

        return question;
    }

    public boolean isQuestionAvailable(String questionTitle, String examTitle) {

        for (Question question : findQuestions()) {
            if ((question.getQuestionTitle().equals(questionTitle)) && (question.getExam().getExamTitle().equals(examTitle)))
                return false;
        }

        return true;
    }

    @Transactional
    public Question saveQuestion(Question question, Exam exam) {

        boolean isValid = isQuestionAvailable(question.getQuestionTitle(), exam.getExamTitle());

        if (isValid) {
            examRepository.save(exam);
            return questionRepository.save(question);
        }

        throw new DataIntegrityException("Já existe uma questão com esse título.");
    }

    public List<Question> findQuestions() {

        return questionRepository.findAll();
    }
}
