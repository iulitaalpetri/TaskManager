package com.example.project_pao;

public class PriorityTask extends Task {
    private int priority;

    public PriorityTask(String name, String description, int priority) {
        super(name, description);
        this.priority = priority;
    }

    //send notification if the priority is 1
    public void notificationPriority(){
        if (priority == 1){
            System.out.println("You have an unchecked task with high priority!");
        }
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @java.lang.Override
    public String toString() {
        return "PriorityTask{" +
                "priority=" + priority +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }



}
