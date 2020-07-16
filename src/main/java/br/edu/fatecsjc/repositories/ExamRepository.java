package br.edu.fatecsjc.repositories;

import br.edu.fatecsjc.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    @Override
    Optional<Exam> findById(Integer integer);

    Exam findByExamTitle(String examTitle);

    void deleteById(Integer id);

    @Override
    <S extends Exam> S save(S entity);

    @Override
    List<Exam> findAll();
}
