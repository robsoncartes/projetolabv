package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(Long id);

    Account findByEmail(String email);

    @Override
    void deleteById(Long id);

    @Override
    <S extends Account> S save(S entity);

    @Override
    List<Account> findAll();

    @Override
    List<Account> findAll(Sort sort);
}
