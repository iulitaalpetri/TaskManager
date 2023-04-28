package com.example.project_pao;

import java.time.LocalTime;

public class MyList extends TaskList{
    private String mood;

    //constructor
    public MyList() {
        super();
        this.mood = null;
        super.name = "My List";
    }

    //set mood
    public void setMood(String mood){
        this.mood = mood;
    }

    //after 00:00, the mood is set to null
    public void resetMood(){
        if (LocalTime.now().isAfter(LocalTime.MIDNIGHT)){
            System.out.println("Set your mood for the day!");
            this.mood = null;
        }
    }


    //set inCharge for all tasks to "me"
    public void setInCharge(){
        for (Task task : this.tasks) {
            task.setInCharge("me");
        }
    }



}

