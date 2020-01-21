package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Choice;

public interface ChoiceService {

    Choice findById(Integer id);

    void saveChoice(Choice choice);

    void saveChoices(Iterable<Choice> choices);
}
