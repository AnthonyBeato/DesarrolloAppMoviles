package com.example.to_do_list_enhanced.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.to_do_list_enhanced.R;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "completed")
    private boolean checked;

    @ColumnInfo(name = "importance")
    private String importance;
    @ColumnInfo(name = "priority")
    private int priority; //Puede ser 0, 1, 2 o 3 dependiendo de la importancia. 4 es nula, 3 baja, 2 media y 1 es alta

    public Task(@NonNull String title, String importance){
        this.title = title;
        this.importance = importance;
        this.checked = false;

        this.priority = setPriorityByImportance(importance);
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
        this.priority = setPriorityByImportance(importance);;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = setPriorityByImportance(importance);;
    }

    private int setPriorityByImportance(String importance){
        switch (importance){
            case "Alta":
                priority = 1;
                break;
            case "Media":
                priority = 2;
                break;
            case "Baja":
                priority = 3;
                break;
            default:
                priority = 4;
        }
        return priority;
    }
}
