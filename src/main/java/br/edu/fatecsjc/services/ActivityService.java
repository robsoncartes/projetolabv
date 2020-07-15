package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Activity;

import javax.transaction.Transactional;
import java.util.List;

public interface ActivityService {

    Activity findById(Integer id);

    boolean isActivityAvailable(String username, String examTitle);

    @Transactional
    Activity saveActivity(Activity activity);

    List<Activity> findActivities();
}
