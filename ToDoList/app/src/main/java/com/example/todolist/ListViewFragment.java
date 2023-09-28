package com.example.todolist;

import android.os.Bundle;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewFragment extends Fragment {

    private ListView lvTasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        //Vincular listView
        lvTasks = view.findViewById(R.id.lvTasks);

        // Crear y establecer el adaptador aquí
        List<Task> arrayOfTasks = Task.getTasks();
        lvTasks.setAdapter(new TasksAdapter(requireContext(), arrayOfTasks));


        // Inflate the layout for this fragment
        return view;
    }
}