package com.example.to_do_list_enhanced.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.to_do_list_enhanced.entities.Task;
import com.example.to_do_list_enhanced.viewholder_rv.TaskViewHolder;
import com.example.to_do_list_enhanced.viewmodel.TaskViewModel;
import org.jetbrains.annotations.NotNull;

public class TaskListAdapter extends ListAdapter<Task, TaskViewHolder> {

    private TaskViewModel viewModel;

    public TaskListAdapter(TaskViewModel viewModel, @NonNull @NotNull DiffUtil.ItemCallback<Task> diffCallback) {
        super(diffCallback);
        this.viewModel = viewModel;
    }


    @NonNull
    @NotNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return TaskViewHolder.create(parent, viewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskViewHolder holder, int position) {
        Task current = getItem(holder.getAbsoluteAdapterPosition());
        holder.bind(current);

        // Configuración del onClickListener para eliminar el Item clickeado
        holder.getButtonDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Mostrar dialogo de confirmación de Delete
                showDeleteConfirmationDialog(view.getContext(), current, holder.getAbsoluteAdapterPosition());
            }
        });
    }

    private void showDeleteConfirmationDialog(Context context, Task currentTask, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmación de Eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar esta tarea?");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Eliminar la tarea después de la confirmación, se manda la orden a ViewModel para que se encargue
                viewModel.delete(currentTask);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Task>{
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem){
            return oldItem.getTitle().equals(newItem.getTitle());
        }

    }
}
