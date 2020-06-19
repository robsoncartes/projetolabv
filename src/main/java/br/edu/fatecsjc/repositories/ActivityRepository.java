package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {

    @Override
    Optional<Activity> findById(Integer integer);

    @Override
    <S extends Activity> S save(S entity);

    List<Activity> save(List<Activity> activities);

    @Override
    List<Activity> findAll();
}
