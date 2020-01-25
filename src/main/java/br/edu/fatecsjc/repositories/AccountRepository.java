package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findById(Long id);

    Account findByEmail(String email);

    @Override
    <S extends Account> S save(S entity);

    @Override
    <S extends Account> List<S> saveAll(Iterable<S> entities);

    @Override
    Iterable<Account> findAll();
}
