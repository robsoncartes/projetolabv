package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Choice;

public interface ChoiceService {

    Choice findChoiceById(Integer id);

    void saveChoice(Choice choice);

    void saveChoices(Iterable<Choice> choices);
}
