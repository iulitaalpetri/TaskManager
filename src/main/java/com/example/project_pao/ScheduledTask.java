package com.example.project_pao;

import java.time.LocalDate;

public class ScheduledTask extends Task{
    private LocalDate deadline;
    private boolean isOverdue;


    public ScheduledTask(String name, String description, String deadline) {
        super(name, description);
        this.deadline = LocalDate.parse(deadline);
        this.isOverdue = false;
    }

    // when the current day is the deadline, the task is overdue
    public boolean isOverdue(){
        if (deadline.compareTo(LocalDate.now()) == 0) {
            isOverdue = true;
        }
        return isOverdue;
    }

    // receive a notification on the day of the deadline
    public void notification(){
        if (deadline.compareTo(LocalDate.now()) == 0) {
            System.out.println("You have a task due today!");
        }
    }


    // receive notification every day after the task is overdue
    public void notificationoverdue(){
        if (isOverdue == true && deadline.compareTo(LocalDate.now()) < 0){
            System.out.println("You have a task that is overdue!");
        }

    }






}
