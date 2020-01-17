package br.edu.fatecsjc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String answer;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public boolean isValid(Answer answer){
        return this.getAnswer().equals(answer.getAnswer());
    }

}
