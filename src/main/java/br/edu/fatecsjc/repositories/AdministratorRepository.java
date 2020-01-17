package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {

    @Override
    Optional<Administrator> findById(Long aLong);

    @Override
    <S extends Administrator> S save(S entity);

    @Override
    <S extends Administrator> List<S> saveAll(Iterable<S> entities);

    @Override
    Iterable<Administrator> findAll();
}
