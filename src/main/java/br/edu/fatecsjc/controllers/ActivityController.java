package br.edu.fatecsjc.controllers;

import br.edu.fatecsjc.models.Activity;
import br.edu.fatecsjc.models.views.ActivityView;
import br.edu.fatecsjc.services.ActivityService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("ActivityController")
@RequestMapping(value = "/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @JsonView(ActivityView.ActivitySimple.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Activity> findActivityById(@PathVariable Integer id) {

        Activity activity = activityService.findById(id);

        return ResponseEntity.ok().body(activity);
    }

    @JsonView(ActivityView.ActivitySimple.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Activity>> findAllActivities() {

        List<Activity> activities = activityService.findActivities();

        return ResponseEntity.ok().body(activities);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertActivity(@RequestBody Activity activity){

        Activity obj = activityService.saveActivity(activity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
