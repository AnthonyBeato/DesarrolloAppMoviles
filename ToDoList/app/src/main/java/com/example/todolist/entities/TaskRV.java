package com.example.todolist.entities;

import android.graphics.drawable.Drawable;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class TaskRV {

    private final String title;
    private boolean checked;
    private int id = 0;

    public int imageResourceId;


    public TaskRV(String title) {
        this.title = title;
        this.checked = false;
        this.imageResourceId = R.drawable.baseline_brightness_1_24;
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

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public static List<TaskRV> getTasks(){
        ArrayList<TaskRV> tasks = new ArrayList<>();
//        tasks.add(new TaskRV("Tarea 1"));
//        tasks.add(new TaskRV("Tarea 2"));

        return tasks;
    }

}
