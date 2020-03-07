package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Activity;
import br.edu.fatecsjc.repositories.ActivityRepository;
import br.edu.fatecsjc.services.exceptions.DataIntegrityException;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity findById(Integer id) {

        Activity activity = activityRepository.findById(id).orElse(null);

        if (activity == null)
            throw new ObjectNotFoundException("Activity not found. Id: " + id + ", Type: " + Activity.class.getName());

        return activity;
    }

    public boolean isActivityAvailable(String username, String examTitle) {

        for (Activity activity : findActivities()) {
            if ((activity.getUsername().equals(username)) && (activity.getExamTitle().equals(examTitle)))
                return false;
        }

        return true;
    }

    public Activity saveActivity(Activity activity) {

        boolean isValid = isActivityAvailable(activity.getUsername(), activity.getExamTitle());

        if (isValid)
            return activityRepository.save(activity);
        throw new DataIntegrityException("User already saved an Activitie with this title");
    }

    public Iterable<Activity> findActivities() {

        return activityRepository.findAll();
    }
}
