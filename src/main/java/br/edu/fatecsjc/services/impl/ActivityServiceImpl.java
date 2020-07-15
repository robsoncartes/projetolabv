package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Activity;
import br.edu.fatecsjc.repositories.ActivityRepository;
import br.edu.fatecsjc.services.ActivityService;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity findById(Integer id) {

        Activity activity = activityRepository.findById(id).orElse(null);

        if (activity == null)
            throw new ObjectNotFoundException("Atividade não encontrada. Id: " + id + ", Tipo: " + Activity.class.getName());

        return activity;
    }

    @Override
    public boolean isActivityAvailable(String username, String examTitle) {

        for (Activity activity : findActivities()) {
            if ((activity.getUsername().equals(username)) && (activity.getExamTitle().equals(examTitle)))
                return false;
        }

        return true;
    }

    @Override
    public Activity saveActivity(Activity activity) {

        boolean isValid = isActivityAvailable(activity.getUsername(), activity.getExamTitle());

        if (isValid)
            return activityRepository.save(activity);
        throw new DataIntegrityException("O usuário já salvou uma atividade com este título.");
    }

    @Override
    public List<Activity> findActivities() {

        return activityRepository.findAll();
    }
}
