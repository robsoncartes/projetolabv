package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {

    @Override
    Optional<Answer> findById(Integer integer);

    @Override
    <S extends Answer> S save(S entity);

    @Override
    <S extends Answer> List<S> saveAll(Iterable<S> entities);
}
