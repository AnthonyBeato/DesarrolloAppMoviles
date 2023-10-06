package com.example.to_do_list_enhanced.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.to_do_list_enhanced.entities.Task;
import com.example.to_do_list_enhanced.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private final LiveData<List<Task>> allWords;

    public TaskViewModel(Application application) {
        super(application);
        repository = new TaskRepository(application);
        this.allWords = repository.getAllTasks();
    }

    public LiveData<List<Task>> getAllWords(){
        return allWords;
    };

    //Ejecutar orden de insertar al repositorio
    public void insert(Task task){
        repository.insert(task);
    }

    //Ejecutar orden de Delete al repositorio:
    public void delete(Task task){
        repository.delete(task);
    }


    //Ejecutar orden de Update al repositorio:
    public void updateTaskStatus(Task task, boolean isChecked) {
        task.setChecked(isChecked); //Marcar si la tarea fue completada
        repository.update(task);
    }

    //Ejecutar orden de Update al repo:
    public void updateTaskImportance(Task task, String importance) {
        task.setImportance(importance);
        repository.update(task);
    }
}
