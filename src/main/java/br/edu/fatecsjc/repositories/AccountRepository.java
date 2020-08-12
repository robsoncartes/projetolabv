package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(Long id);

    Account findByEmail(String email);

    Account findByUsername(String username);

    // Geralmente, o mecanismo de criação de consulta para JPA funciona conforme descrito em “Métodos de consulta"
    @Query("SELECT obj FROM Account  obj WHERE obj.email = ?1 OR obj.username = ?2")
    Account findByEmailOrUsername(String email, String username);

    @Override
    void deleteById(Long id);

    @Override
    <S extends Account> S save(S entity);

    @Override
    List<Account> findAll();

    @Override
    List<Account> findAll(Sort sort);
}
