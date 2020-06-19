package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Choice;
import br.edu.fatecsjc.repositories.ChoiceRepository;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {

    @Autowired
    private ChoiceRepository choiceRepository;

    public Choice findById(Integer id) {

        Choice choice = choiceRepository.findById(id).orElse(null);

        if (choice == null)
            throw new ObjectNotFoundException("Escolha não encontrada. Id: " + id + ", Tipo: " + Choice.class.getName());

        return choice;
    }

    public void saveChoice(Choice choice) {
        choiceRepository.save(choice);
    }

    public List<Choice> findChoices() {

        return choiceRepository.findAll();
    }
}
