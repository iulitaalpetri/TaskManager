package com.example.project_pao;

import java.util.Scanner;

public class Task {
    private String name;
    private String description;
    private boolean checked;
    private String inCharge;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.checked = false;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }



    public boolean isChecked() {
        return checked;
    }

    //if a task is checked, it cannot be unchecked
    public void setChecked(boolean checked) {
        if (this.checked == true) {
            throw new IllegalArgumentException("The task is already checked!");
        }
        this.checked = checked;
    }
    //set inCharge
    public void setInCharge(String inCharge){
        this.inCharge = inCharge;
    }

    //print the task
    @java.lang.Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", checked=" + checked +
                ", inCharge='" + inCharge + '\'' +
                '}';
    }

    //setters
    public void setDescription(String description) {
        this.description = description;
    }



    public void editTask() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Description");
        System.out.println("3. In charge");
        System.out.println("4. Exit");
        int choice = myScanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter the new name: ");
                String newName = myScanner.nextLine();
                this.setName(newName);
                break;
            case 2:
                System.out.println("Enter the new description: ");
                String newDescription = myScanner.nextLine();
                this.setDescription(newDescription);
                break;
            case 3:
                System.out.println("Enter the new in charge: ");
                String nwInCharge = myScanner.nextLine();
                String newInCharge = myScanner.nextLine();
                this.setInCharge(newInCharge);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }


    }

    public void seeTask() {
        System.out.println(this.toString());

    }

    public String getinCharge() {
        return inCharge;
    }

    public void setinCharge(AppUser user) {
        this.inCharge = user.getName();
    }
}
