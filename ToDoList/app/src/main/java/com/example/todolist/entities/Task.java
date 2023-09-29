package com.example.todolist.entities;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String title;
    private boolean checked;
    private int id = 0;


    public Task(String title) {
        this.title = title;
        this.checked = false;
        this.id++;
    }

    public String getTitle(){return title;}

    public boolean getChecked(){return checked;}

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public int getId(){
        return id;
    }


    public static List<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Tarea 1"));
//        tasks.add(new Task("Tarea 2"));
//        tasks.add(new Task("Tarea 3"));
//        tasks.add(new Task("Tarea 4"));

        return tasks;
    }
}
