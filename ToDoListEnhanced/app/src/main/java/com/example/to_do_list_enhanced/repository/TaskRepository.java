package com.example.to_do_list_enhanced.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.to_do_list_enhanced.dao.TaskDao;
import com.example.to_do_list_enhanced.database.TaskRoomDatabase;
import com.example.to_do_list_enhanced.entities.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>>  allTasks;

    public TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAlphabetizedTasks();
    }

    //Retorna el LiveData de la lista de las tareas con Room.
    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    } //El método observado de LiveData notificará al observador en el hilo principal cuando la data se haya cambiado

    //Este método es un hilo no-UI y se debe llamar porque sino, la app tirará una excepción. Room asegura
    //que no se podrá hacer ninguna operación que dure mucho en el hilo principal, sino, bloqueará el UI.
    public void insert(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    //Eliminar una tarea
    public void delete(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.delete(task);
        });
    }

    //Actualizar una tarea (Marcarla como completado)
    public void update(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.update(task);
        });
    }
}
