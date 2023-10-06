package com.example.to_do_list_enhanced.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.to_do_list_enhanced.entities.Task;

import java.util.List;

@Dao
public interface TaskDao {

    //Permitir que el insert pueda agregar multiples veces la misma task utilizando una estrategia de resolución de
    //conflictos

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task); //Declara el método para insertar una palabra

    //Update
    @Update
    void update(Task task);

    //Delete
    @Delete
    void delete(Task task); //Declaración de método para borrar una tarea


    //Query para borrar todas las tasks
    @Query("DELETE FROM task_table")
    void deleteAll();


    //Queries para obtener listas ordenadas de las tareas
    @Query("SELECT * FROM task_table ORDER BY priority, completed, title ASC")
    LiveData<List<Task>> getAlphabetizedTasks(); //La lista se envuelve en LiveData

    @Query("SELECT * FROM task_table ORDER BY importance DESC")
    LiveData<List<Task>> getTaskByImportance();


}
