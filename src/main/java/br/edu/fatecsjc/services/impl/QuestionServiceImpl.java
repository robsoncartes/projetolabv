package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Exam;
import br.edu.fatecsjc.models.Question;
import br.edu.fatecsjc.repositories.ExamRepository;
import br.edu.fatecsjc.repositories.QuestionRepository;
import br.edu.fatecsjc.services.QuestionService;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Question findById(Integer id) {

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null)
            throw new ObjectNotFoundException("Questão não encontrada. Id: " + id + ", Tipo: " + Question.class.getName());

        return question;
    }

    @Override
    public Question findByTitle(String questionTitle) {

        Question question = questionRepository.findByQuestionTitle(questionTitle);

        if (question == null)
            throw new ObjectNotFoundException("Questão não encontrada. Título: " + questionTitle + "Tipo: " + Question.class.getName());

        return question;
    }

    @Override
    public boolean isQuestionAvailable(String questionTitle, String examTitle) {

        for (Question question : findQuestions()) {
            if ((question.getQuestionTitle().equals(questionTitle)) && (question.getExam().getExamTitle().equals(examTitle)))
                return false;
        }

        return true;
    }

    @Override
    public Question saveQuestion(Question question) {

        Exam eObj = examRepository.findByExamTitle(question.getExam().getExamTitle());

        boolean isValid = isQuestionAvailable(question.getQuestionTitle(), question.getExam().getExamTitle());

        if (isValid) {
            if (eObj == null) {
                Exam exam = new Exam();
                exam.setId(null);
                exam.setExamTitle(question.getExam().getExamTitle());
                exam.setType(question.getExam().getType());
                exam.setDescription(question.getExam().getDescription());
                exam.setAuthor(question.getExam().getAuthor());

                question.setId(null);
                question.setQuestionTitle(question.getQuestionTitle());
                question.setExam(exam);

                exam.addQuestion(question);
                examRepository.save(exam);

            } else {
                question.setId(null);
                question.setQuestionTitle(question.getQuestionTitle());
                question.getExam().setId(eObj.getId());
                question.getExam().setExamTitle(question.getExam().getExamTitle());
                question.getExam().setType(question.getExam().getType());
                question.getExam().setDescription(question.getExam().getDescription());
                question.getExam().setAuthor(question.getExam().getAuthor());
            }


            return questionRepository.save(question);
        }

        throw new DataIntegrityException("Já existe um Exame com uma questão com esse título.");
    }

    public List<Question> findQuestions() {

        return questionRepository.findAll();
    }
}
