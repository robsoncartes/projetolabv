package br.edu.fatecsjc.services.impl;

import br.edu.fatecsjc.models.Activity;
import br.edu.fatecsjc.repositories.ActivityRepository;
import br.edu.fatecsjc.services.ActivityService;
import br.edu.fatecsjc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
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

    @Override
    public void saveActivity(Activity activity) {

        if (isActivityAvailable(activity.getUsername(), activity.getExamTitle()))
            activityRepository.save(activity);
    }

    @Override
    public void saveActivities(Iterable<Activity> activities) {
        activityRepository.saveAll(activities);
    }

    @Override
    public Iterable<Activity> findActivities() {

        return activityRepository.findAll();
    }


}
