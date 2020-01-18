package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Choice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice, Integer> {

    @Override
    Optional<Choice> findById(Integer integer);

    @Override
    <S extends Choice> S save(S entity);

    @Override
    <S extends Choice> List<S> saveAll(Iterable<S> entities);
}
