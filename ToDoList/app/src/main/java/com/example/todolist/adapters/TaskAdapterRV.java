package com.example.todolist.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolist.R;
import com.example.todolist.entities.TaskRV;
import org.jetbrains.annotations.NotNull;
import androidx.appcompat.app.AlertDialog;


import java.util.List;

public class TaskAdapterRV extends RecyclerView.Adapter<TaskAdapterRV.TaskViewHolder> {

    private final List<TaskRV> tasks;

    public TaskAdapterRV(List<TaskRV> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @NotNull
    @Override
    public TaskAdapterRV.TaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_rv, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskAdapterRV.TaskViewHolder holder, @SuppressLint("RecyclerView") int position) {

        TaskRV task = tasks.get(position);

        holder.title.setText(task.getTitle());
        holder.checkBox.setChecked(task.getChecked());
        holder.imagen.setImageResource(task.getImageResourceId());

        // Agregar OnClickListener para el CheckBox
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Actualizar el estado de la tarea cuando el CheckBox cambie
                task.setChecked(isChecked);
                holder.title.setText(getStyledText(task.getTitle(), isChecked));
            }

            private SpannableString getStyledText(String text, boolean isChecked) {
                SpannableString spannableString = new SpannableString(text);

                if (isChecked) {
                    spannableString.setSpan(new StrikethroughSpan(), 0, text.length(), 0);
                }

                return spannableString;
            }
        });

        // Establecer el estado actual del CheckBox
        holder.checkBox.setChecked(task.getChecked());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog(view.getContext(), task, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CheckBox checkBox;
        ImageView imagen;
        ImageButton delete;

        @SuppressLint("CutPasteId")
        public TaskViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.checkBox_Title_RV);
            checkBox = itemView.findViewById(R.id.checkBox_Title_RV);
            imagen = itemView.findViewById(R.id.importanceIconRV);
            delete = itemView.findViewById(R.id.btn_delete_RV);
        }
    }

    private void showDeleteConfirmationDialog(Context context, TaskRV task, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmación de Eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar esta tarea?");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTask(task, position);
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

    private void deleteTask(TaskRV task, int position) {
        tasks.remove(position);
        notifyItemRemoved(position);
        //Toast.makeText(context, "Se eliminó " + task.getTitle(), Toast.LENGTH_LONG).show();
    }


}
