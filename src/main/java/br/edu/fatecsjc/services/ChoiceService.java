package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Choice;

import javax.transaction.Transactional;
import java.util.List;

public interface ChoiceService {

    Choice findById(Integer id);

    @Transactional
    void saveChoice(Choice choice);

    List<Choice> findChoices();
}
