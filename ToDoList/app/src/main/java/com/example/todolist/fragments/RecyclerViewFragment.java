package com.example.todolist.fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolist.R;
import com.example.todolist.adapters.TaskAdapterRV;
import com.example.todolist.databinding.FragmentRecyclerViewBinding;
import com.example.todolist.entities.Task;
import com.example.todolist.entities.TaskRV;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {

    private FragmentRecyclerViewBinding binding;
    private TaskAdapterRV taskAdapter;
    private List<TaskRV> taskList = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewFragment newInstance(String param1, String param2) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
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

        // Inicializar taskList con las tareas de getTasks()
        taskList = new ArrayList<>(TaskRV.getTasks());
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        RecyclerView recyclerView = binding.recycler;
        taskAdapter = new TaskAdapterRV(taskList);

        recyclerView.setHasFixedSize(true);

        int spanCan = 1;

        if(getActivity() != null){
            if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                spanCan = 2;
            }
        }

        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), spanCan));

        recyclerView.setAdapter(taskAdapter);

        EditText textTask = view.findViewById(R.id.editTaskRV);

        ImageButton addButton = view.findViewById(R.id.addButtonRV);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTask(textTask);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Liberar la referencia de binding cuando se destruye la vista
        binding = null;
    }

    public void createTask(EditText textTask) {
        String title = textTask.getText().toString();

        // Validar si el título está vacío antes de crear una tarea
        if (!TextUtils.isEmpty(title)) {
            // Creación de objeto tarea con imagen por defecto
            TaskRV newTask = new TaskRV(title);

            // Agregar task a la lista de tareas actual
            taskList.add(newTask);

            // Notificar al adaptador que se ha insertado un nuevo elemento
            if (taskAdapter != null) {
                taskAdapter.notifyItemInserted(taskList.size() - 1);
            }


            // Limpiar el EditText después de agregar la tarea
            textTask.setText("");

            // Agrega una impresión para verificar si el elemento se agregó correctamente
            Log.d("RecyclerViewFragment", "Elemento agregado: " + newTask.getTitle());
        } else {
            // Mostrar un mensaje de error si el título está vacío
            Toast.makeText(requireContext(), "El título de la tarea no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }
}