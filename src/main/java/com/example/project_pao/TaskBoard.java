package com.example.project_pao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TaskBoard {
    private MyList toDo;
    private List<Project> project= new ArrayList<>();//asta merge sortata dupa deadline

    public TaskBoard() {


    }

    //copy constructor
    public TaskBoard(TaskBoard taskBoard) {
        this.toDo = taskBoard.toDo;
        this.project = taskBoard.project;
    }

    //add a project
    public Project addProject(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the name of the project: ");
        String name =myObj.nextLine();
        System.out.println("Enter the description of the project: ");
        String description =myObj.nextLine();
        System.out.println("Enter the deadline of the project (yyyy-mm-dd): ");
        String deadline =myObj.nextLine();
        Project proj = new Project(name, description, deadline);
        this.project.add(proj);

        System.out.println("Project added!");
        return proj;
    }

    public void removeProject(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the name of the project: ");
        String name =myObj.nextLine();
        for (Project proj : this.project) {
            if (proj.getName().equals(name)){
                this.project.remove(proj);
                System.out.println("Project removed!");
                break;
            }
        }
    }


    //remove a project when all the tasks are done
    public void removeProject(Project project){
        if(project.tasks.size()==0){
            this.project.remove(project);
            project=null; //nu stiu daca e ok sa fac asta
        }
    }

    //sort the projects by deadline
    public void sortProjects(){
        Collections.sort(project);
    }

    //print projects by deadline
    public void printProjects(){
        this.sortProjects();
        for(Project proj: project){
            System.out.println(proj.getName());
        }
    }



    public Project getProject(String name) {
        for (Project proj : this.project) {
            if (proj.getName().equals(name)){
                return proj;
            }
        }
        return null;
    }

    public MyList getMyList() {
        return toDo;
    }
}


