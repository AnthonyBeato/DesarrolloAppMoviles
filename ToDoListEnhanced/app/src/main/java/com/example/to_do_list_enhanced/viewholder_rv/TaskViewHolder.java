package com.example.to_do_list_enhanced.viewholder_rv;

import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.to_do_list_enhanced.R;
import com.example.to_do_list_enhanced.entities.Task;
import com.example.to_do_list_enhanced.viewmodel.TaskViewModel;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    private final ImageButton buttonDelete;
    private final CheckBox checkBoxTask;
    private final LinearLayout layoutCard;
    private final ImageButton importanceButton;

    private TaskViewModel viewModel;



    public TaskViewHolder(TaskViewModel viewModel, @NonNull @NotNull View itemView) {
        super(itemView);
        //taskTitleItemView = itemView.findViewById(R.id.checkBox_Title_RV);
        checkBoxTask = itemView.findViewById(R.id.checkBox_Title_RV);
        buttonDelete = itemView.findViewById(R.id.buttonDelete);
        layoutCard = itemView.findViewById(R.id.layoutCard);
        importanceButton = itemView.findViewById(R.id.importanceButtonRV);
        this.viewModel = viewModel;
    }

    private static final List<String> IMPORTANCE_LEVELS = Arrays.asList("Alta", "Media", "Baja", "Nula");

    public void bind(Task task){
        //checkBoxTask.setText(task.getTitle());
        checkBoxTask.setText(getStyledText(task.getTitle(), task.isChecked()));
        checkBoxTask.setChecked(task.isChecked());

        updateTaskTransparency(task.isChecked());

        updateImportanceButton(task.getImportance());


        checkBoxTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Manejar cambio de estado (Ya sea si se completó o no)
                boolean isChecked = checkBoxTask.isChecked();
                viewModel.updateTaskStatus(task, isChecked);

                updateTaskTransparency(isChecked);
            }
        });

        importanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtener el nivel de importancia actual de la tarea
                String currentImportance = task.getImportance();

                //Encontrar el indice actual en la lista de niveles de importancia
                int currentIndex = IMPORTANCE_LEVELS.indexOf(currentImportance);

                //Calcular el nuevo indice (ciclando los valores)
                int nextIndex = (currentIndex + 1) % IMPORTANCE_LEVELS.size();

                //Obtener el nuevo nivel de importancia
                String newImportance = IMPORTANCE_LEVELS.get(nextIndex);

                //Actualizar el nivel de importancia de la tarea
                task.setImportance(newImportance);

                //Actualizar la vista del botón
                updateImportanceButton(newImportance);

                //Actualizar la tarea en la bd con el viewmodel
                viewModel.updateTaskImportance(task, newImportance);
            }
        });
    }

    private void updateImportanceButton(String importance) {
        switch (importance){
            case "Alta":
                importanceButton.setImageResource(R.drawable.baseline_importance_high);
                break;
            case "Media":
                importanceButton.setImageResource(R.drawable.baseline_importance_medium);
                break;
            case "Baja":
                importanceButton.setImageResource(R.drawable.baseline_importance_easy);
                break;
            default:
                importanceButton.setImageResource(R.drawable.baseline_brightness_1_24);
                break;
        }
    }

    private SpannableString getStyledText(String text, boolean isChecked) {
        SpannableString spannableString = new SpannableString(text);

        if (isChecked) {
            spannableString.setSpan(new StrikethroughSpan(), 0, text.length(), 0);
        }

        return spannableString;
    }

    private void updateTaskTransparency(boolean isChecked){
        float alpha = isChecked ? 0.5f : 1.0f;
        layoutCard.setAlpha(alpha);
    }

    public ImageButton getButtonDelete() {
        return buttonDelete;
    }

    public static TaskViewHolder create(ViewGroup parent, TaskViewModel viewModel){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TaskViewHolder(viewModel ,view);
    }
}
