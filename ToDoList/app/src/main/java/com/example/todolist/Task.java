package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private final String title;
    private final boolean checked;
    private int id = 0;


    public Task(String title, boolean checked) {
        this.title = title;
        this.checked = checked;
        this.id++;
    }

    public String getTitle(){return title;}

    public boolean getChecked(){return checked;}


    public static List<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Tarea 1", true));
        tasks.add(new Task("Tarea 2", false));
        tasks.add(new Task("Tarea 3", false));
        tasks.add(new Task("Tarea 4", false));

        return tasks;
    }
}
