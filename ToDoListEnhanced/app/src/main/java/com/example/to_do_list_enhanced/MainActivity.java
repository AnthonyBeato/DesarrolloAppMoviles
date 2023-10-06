package com.example.to_do_list_enhanced;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.to_do_list_enhanced.adapters.TaskListAdapter;
import com.example.to_do_list_enhanced.database.TaskRoomDatabase;
import com.example.to_do_list_enhanced.entities.Task;
import com.example.to_do_list_enhanced.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskDialog.TaskDialogListener {

    private TaskViewModel taskViewModel;
    private TextView empyTextView;
    private static final int TASK_DIALOG_REQUEST_CODE = 1;
    private TaskListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TaskRoomDatabase.deleteInstance(getApplicationContext()); // Elimina la base de datos

        //Inicializar las vistas
        empyTextView = findViewById(R.id.emptyTextView);

        //Usando ViewModelProvider para asociar el ViewModel con la actividad
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        adapter = new TaskListAdapter(taskViewModel, new TaskListAdapter.WordDiff());

        //Verificar orientación del device
        int orientation = getResources().getConfiguration().orientation;
        int spanCount = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? 2 : 1;

        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        //Añadir un observador para analizar en LiveData cuando la data cambie
        //Actualizar la copia en cache de las tareas en el adaptador
        //taskViewModel.getAllWords().observe(this, adapter::submitList);
        taskViewModel.getAllWords().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(tasks.isEmpty()){
                    //En el caso de que no hayan tareas, se muestra el TextView y se oculta Recycler
                    empyTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }else{
                    //En caso contrario, si existen tareas
                    empyTextView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

                //Actualizar el RecyclerView con la lista de tareas
                adapter.submitList(tasks);
            }

        });

        //Manejo para botón de agregar nueva tarea
        FloatingActionButton fab = findViewById(R.id.floatingAddTaskButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDialog taskDialog = new TaskDialog(MainActivity.this);
                taskDialog.setTaskDialogListener(MainActivity.this);
                taskDialog.show();
            }
        });
    }


    @Override
    public void onTaskAdded(String taskTitle, String importance) {
        Task newTask = new Task(taskTitle, importance);
        taskViewModel.insert(newTask);
    }
}