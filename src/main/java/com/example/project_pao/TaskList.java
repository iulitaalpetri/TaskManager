package com.example.project_pao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class TaskList {
    protected List<Task> tasks = new ArrayList<>();
    protected String name;


    public TaskList( ) {

    }



    public void addTask(){
        //menu to choose what kind of task to add
        System.out.println("Choose the type of task you want to add: ");
        System.out.println("1. Priority task");
        System.out.println("2. Deadline task");
        System.out.println("3. Recurring task");

        Scanner myObj = new Scanner(System.in);
        int option = myObj.nextInt();
        switch (option) {
            case 1:

                myObj= new Scanner(System.in);
                System.out.println("Enter the priority of the task: ");
                int priority = myObj.nextInt();
                System.out.println("Enter the name of the task: ");
                String na = myObj.nextLine();
                String name = myObj.nextLine();
                System.out.println("Enter the description of the task: ");
                String description = myObj.nextLine();
                PriorityTask priorityTask = new PriorityTask(name, description, priority);
                this.tasks.add(priorityTask);
                System.out.println("Task added successfully!");
                break;
            case 2:
                myObj= new Scanner(System.in);
                System.out.println("Enter the name of the task: ");
                name = myObj.nextLine();
                System.out.println("Enter the description of the task: ");
                description = myObj.nextLine();
                System.out.println("Enter the deadline of the task: ");
                String deadline = myObj.nextLine();
                ScheduledTask deadlineTask = new ScheduledTask(name, description, deadline);

                this.tasks.add(deadlineTask);
                System.out.println("Task added successfully!");
                break;
            case 3:
                myObj= new Scanner(System.in);
                System.out.println("Enter the name of the task: ");
                name = myObj.nextLine();
                System.out.println("Enter the description of the task: ");
                description = myObj.nextLine();
                System.out.println("Enter the frequncy of the task: ");
                int frequency = myObj.nextInt();
                System.out.println("Enter the start date of the task(yyyy-mm-dd): ");
                String st = myObj.nextLine();
                String startDate = myObj.nextLine();
                System.out.println("Enter the end date of the task(yyyy-mm-dd): ");
                String endDate = myObj.nextLine();



                RecurringTask recurringTask = new RecurringTask(name, description, frequency, startDate, endDate);
                this.tasks.add(recurringTask);
                System.out.println("Task added successfully!");
                break;
            default:
                System.out.println("Invalid option");
        }







    }

    //send notifications regardig all tasks

    public void sendNotifications(){
        for(Task task: tasks){
            if(task instanceof PriorityTask){
                ((PriorityTask) task).notificationPriority();

            }
            if(task instanceof ScheduledTask){
                ((ScheduledTask) task).notification();
                ((ScheduledTask) task).notificationoverdue();
            }
            if(task instanceof RecurringTask){
                ((RecurringTask) task).notification();
                ((RecurringTask) task).notificationWeek();
            }
        }

    }
    public void removeTask(Task task){

        if (task.isChecked() == true) {
            tasks.remove(task);
            task= null; //nu stiu daca e ok sa fac asta
        }
        //when a priority task is removed, we need to update the priority of the other priority tasks -1
        if(task instanceof PriorityTask){
            for(Task t: tasks){
                if(t instanceof PriorityTask){
                    PriorityTask priorityTask = (PriorityTask) t;
                    priorityTask.setPriority(priorityTask.getPriority()-1);
                }
            }
        }


    }

    //print all tasks with a numeric index
    public void printTasks(){
        int i=1;
        for(Task task: tasks){
            System.out.println(i + ". " + task.getName());
            i++;
        }
    }


    public Task getTask(String name) {
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
