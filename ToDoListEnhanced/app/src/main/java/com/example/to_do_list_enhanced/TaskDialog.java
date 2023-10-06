package com.example.to_do_list_enhanced;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;

public class TaskDialog extends Dialog {
    //public static final String EXTRA_REPLY = "com.example.to_do_list_enhanced.REPLY";

    private EditText taskTextView;
    private Spinner importanceSpinner;
    private Button okButton;

    private TaskDialogListener listener;

    public TaskDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_task);

        taskTextView = findViewById(R.id.taskTextView);
        importanceSpinner = findViewById(R.id.importanceSpinner);
        okButton = findViewById(R.id.okButton);

        //Configuración del adaptador para el Spinner y mostrar opciones
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.importance_options,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource( androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        importanceSpinner.setAdapter(adapter);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtener tarea y poner su importancia
                String taskTitle = taskTextView.getText().toString();
                String importance = importanceSpinner.getSelectedItem().toString();

                //Llamar al método de TaskDialogListener para enviar los datos
                if(listener != null){
                    listener.onTaskAdded(taskTitle, importance);
                }

                dismiss();
            }
        });

    }

    public void setTaskDialogListener(TaskDialogListener listener){
        this.listener = listener;
    }

    public interface TaskDialogListener{
        void onTaskAdded(String taskTitle, String importance);
    }
}
