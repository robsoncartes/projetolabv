package br.edu.fatecsjc.factories;

import br.edu.fatecsjc.models.Exam;

public class ExamFactory {

    public static Exam validExam(Exam exam) {

        // Exam lol = new Exam(1, "teste", "teste", "s", "teste");
        exam.setId(1);
        exam.setTitle("ExameTitle1");
        exam.setType("Type1");
        exam.setDescription("Description1");
        exam.setAuthor("Author1");

        return exam;
    }
}

