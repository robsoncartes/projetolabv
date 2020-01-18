package br.edu.fatecsjc.services;

import br.edu.fatecsjc.models.Activity;

public interface ActivityService {

    Activity findActivityById(Integer id);

    void saveActivity(Activity activity);

    void saveActivities(Iterable<Activity> activities);

    Iterable<Activity> findActivities();
}
