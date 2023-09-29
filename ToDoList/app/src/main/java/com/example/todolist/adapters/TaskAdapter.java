package com.example.todolist.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.example.todolist.R;
import com.example.todolist.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private List<Task> tasksUnchecked; // Lista de tareas no marcadas
    private List<Task> tasksChecked;   // Lista de tareas marcadas
    private List<Task> allTasks; // Lista de todas las tareas

    public TaskAdapter(Context context, @NonNull List<Task> objects){
        super(context, R.layout.item_task, objects);
        // Inicializa la lista única
        allTasks = new ArrayList<>(objects);

        // Divide las tareas en listas de completadas y no completadas
        tasksChecked = new ArrayList<>();
        tasksUnchecked = new ArrayList<>();
        for (Task task : objects) {
            if (task.getChecked()) {
                tasksChecked.add(task);
            } else {
                tasksUnchecked.add(task);
            }
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        Task item = getItem(position);

        CheckBox checkBoxTitle = convertView.findViewById(R.id.checkBox_title);
        checkBoxTitle.setText(item.getTitle());

        //Configuración el estado del Checkbox según el estado de la tarea
        checkBoxTitle.setChecked(item.getChecked());

        //Configuración el OnClickListener para el CheckBox
        checkBoxTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambiar el estado de la tarea
                item.setChecked(!item.getChecked());

                //Moviendo la tarea a la lista correspondiente
                if (item.getChecked()) {
                    tasksChecked.add(item);
                    tasksUnchecked.remove(item);
                } else {
                    tasksUnchecked.add(item);
                    tasksChecked.remove(item);
                }

                notifyDataSetChanged(); // Notificar al adaptador que los datos cambiaron
            }
        });


        ImageButton deleteButton = convertView.findViewById(R.id.imgBtn_delete);
        //Configuración de OnClickListener para eliminar
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog(item);
            }
        });



        return convertView;
    }

    // Método para mostrar el cuadro de diálogo de advertencia
    private void showDeleteConfirmationDialog(final Task taskToDelete) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmación de Eliminación");
        builder.setMessage("¿Seguro que quieres eliminar la tarea '" + taskToDelete.getTitle() + "'?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // En caso positivo, se elimina la tarea
                remove(taskToDelete);
                notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // En caso negativo, el usuario no quiere borrar la tarea
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
