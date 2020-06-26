package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Integer> {

    @Override
    Optional<Exam> findById(Integer integer);

    Exam findByExamTitle(String examTitle);

    @Override
    <S extends Exam> S save(S entity);

    @Override
    List<Exam> findAll();
}
