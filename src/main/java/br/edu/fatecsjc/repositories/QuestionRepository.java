package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {

    @Override
    Optional<Question> findById(Integer integer);

    Question findByQuestionTitle(String questionTitle);

    @Override
    <S extends Question> S save(S entity);

    @Override
    List<Question> findAll();
}
