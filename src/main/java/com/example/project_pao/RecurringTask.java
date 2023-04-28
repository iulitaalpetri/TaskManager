package com.example.project_pao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RecurringTask extends Task{
    private int frequency;// de ex pt data unei facturi, apare odata la o luna
    private LocalDate start_date;
    private LocalDate end_date;


    //make the constructor
    public RecurringTask(String name, String description, int frequency, String start_date, String end_date){
        super(name, description);
        this.frequency = frequency;
        this.start_date = LocalDate.parse(start_date);
        this.end_date = LocalDate.parse(end_date);

    }

    //make the getters and setters
    public int getFrequency(){
        return frequency;
    }

    public LocalDate getStart_date(){
        return start_date;
    }



    public void setFrequency(int frequency){
        this.frequency = frequency;
    }

    public void setStart_date(LocalDate start_date){
        this.start_date = start_date;
    }


    //make the toString method
    @java.lang.Override
    public String toString(){
        return "RecurringTask{" +
                "frequency=" + frequency +
                ", start_date=" + start_date +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }

    //make the method to check if the task is overdue
    public boolean isOverdue(){
        if (ChronoUnit.DAYS.between(LocalDate.now(), start_date) > 0) {
            return true;
        }
        return false;
    }


    public LocalDate getNextDueDate() {
        LocalDate currentDate;

        currentDate = LocalDate.now();

        if (currentDate.isBefore(start_date)) {
            return start_date;
        }

        LocalDate dueDate = start_date;

        while (dueDate.isBefore(currentDate)) {
            dueDate = dueDate.plusDays(frequency);
        }

        if (end_date != null && dueDate.isAfter(end_date)) {
            return null;
        }
        return dueDate;
    }

    //make the method to check if the task is due today and send notification
    public void notification(){
        if (ChronoUnit.DAYS.between(LocalDate.now(), getNextDueDate()) == 0) {
            System.out.println("You have a task due today!");
        }
    }

    //make the method to check if the task is due this week and sends notification
    public void notificationWeek(){
        if (ChronoUnit.DAYS.between(LocalDate.now(), getNextDueDate()) <= 7
                && ChronoUnit.DAYS.between(LocalDate.now(), getNextDueDate()) != 0) {
            System.out.println("You have a task due this week!");
        }
    }












}
