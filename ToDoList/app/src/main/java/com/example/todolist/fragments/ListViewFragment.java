package com.example.todolist.fragments;

import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.todolist.R;
import com.example.todolist.adapters.TaskAdapter;
import com.example.todolist.databinding.FragmentListViewBinding;
import com.example.todolist.entities.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewFragment extends Fragment {

    private ListView lvTasks;
    List<Task> arrayOfTasks;
    FragmentListViewBinding binding;
    private TaskAdapter taskAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Crear y establecer el adaptador aquí
        arrayOfTasks = Task.getTasks();
        if(arrayOfTasks.isEmpty()){
            arrayOfTasks = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        //Vincular listView
        lvTasks = view.findViewById(R.id.lvTasks);



        taskAdapter = new TaskAdapter(requireContext(), arrayOfTasks);

        lvTasks.setAdapter(taskAdapter);

        // Obtener referencia al EditText
        EditText textTask = view.findViewById(R.id.editTask);

        // Obtener referencia al botón de crear tarea
        ImageButton createButton = view.findViewById(R.id.addButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar a la función createTask pasando el EditText como argumento
                createTask(textTask);
            }
        });



        return view;
    }


    public void createTask(EditText textTask) {
        String title = textTask.getText().toString();

        // Validar si el título está vacío antes de crear una tarea
        if (!TextUtils.isEmpty(title)) {
            // Creación de objeto tarea
            Task newTask = new Task(title);

            // Agregar task a la lista de tareas actual
            taskAdapter.add(newTask);

            // Limpiar el EditText después de agregar la tarea
            textTask.setText("");

            // Notificar al adaptador que los datos cambiaron
            taskAdapter.notifyDataSetChanged();
        } else {
            // Mostrar un mensaje de error o realizar alguna otra acción cuando el título está vacío
            // Puedes usar Toast o Snackbar para mostrar mensajes al usuario
            Toast.makeText(requireContext(), "El título de la tarea no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }
}