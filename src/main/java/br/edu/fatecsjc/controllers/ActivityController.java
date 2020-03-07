package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Activity;
import br.edu.fatecsjc.models.views.ActivityView;
import br.edu.fatecsjc.services.ActivityService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    @JsonView(ActivityView.ActivitySimple.class)
    public ResponseEntity<Iterable<Activity>> findAllActivities() {

        Iterable<Activity> activities = activityService.findActivities();

        return ResponseEntity.ok().body(activities);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertActivity(@RequestBody Activity activity){

        Activity obj = activityService.saveActivity(activity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
