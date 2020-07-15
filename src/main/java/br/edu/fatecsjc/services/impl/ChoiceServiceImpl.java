package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Choice;
import br.edu.fatecsjc.repositories.ChoiceRepository;
import br.edu.fatecsjc.services.ChoiceService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    @Autowired
    private ChoiceRepository choiceRepository;

    @Override
    public Choice findById(Integer id) {

        Choice choice = choiceRepository.findById(id).orElse(null);

        if (choice == null)
            throw new ObjectNotFoundException("Escolha não encontrada. Id: " + id + ", Tipo: " + Choice.class.getName());

        return choice;
    }

    @Override
    public void saveChoice(Choice choice) {

        choiceRepository.save(choice);
    }

    @Override
    public List<Choice> findChoices() {

        return choiceRepository.findAll();
    }
}
