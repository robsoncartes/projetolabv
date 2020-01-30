package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Activity;
import br.edu.fatecsjc.models.views.ActivityView;
import br.edu.fatecsjc.services.ActivityService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("ActivityController")
@RequestMapping(value = "/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(ActivityView.ActivitySimple.class)
    public ResponseEntity<Activity> findActivityById(@PathVariable Integer id) {

        Activity activity = activityService.findById(id);

        return ResponseEntity.ok().body(activity);
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ActivityView.ActivityComplete.class)
    public ResponseEntity<Iterable<Activity>> findAllActivities() {

        Iterable<Activity> activities = activityService.findActivities();

        return ResponseEntity.ok().body(activities);
    }
}
