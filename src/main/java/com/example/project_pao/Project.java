package com.example.project_pao;

import java.time.LocalDate;

public class Project extends TaskList implements Comparable<Project>{//participanti
    private LocalDate deadline;
    private String description;
    private boolean isOverdue;

    public Project(String name, String description, String deadline) {
        super();
        this.name = name;
        this.description = description;
        this.deadline = LocalDate.parse(deadline);
        this.isOverdue = false;

    }




    //compare deadline between two projects, return the one with the earliest deadline
    @java.lang.Override
    public int compareTo(Project o) {
        return this.deadline.compareTo(o.deadline);
    }




    //when the current day is the deadline, the project is overdue and we receive a notification
    public void isOverdue(){
        if (deadline.compareTo(LocalDate.now()) == 0) {
            isOverdue = true;
            System.out.println("The deadline is today!");
        }
    }

    //receive notification after the project is overdue
    public void notificationoverdue(){
        if (isOverdue == true && deadline.compareTo(LocalDate.now()) < 0){
            System.out.println("You have a project that is overdue!");
        }
    }


    //get name
    public String getName() {
        return name;
    }
}
