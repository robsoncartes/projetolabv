package br.edu.fatecsjc.factories;

import br.edu.fatecsjc.models.Activity;

public class ActivityFactory {

    public static Activity validActivity(Activity activity){

        activity.setId(1);
        activity.setUsername("user3");
        activity.setExamTitle("ExamTitle1");

        return activity;
    }
}
