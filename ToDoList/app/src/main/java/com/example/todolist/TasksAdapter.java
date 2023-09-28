package com.example.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TasksAdapter extends ArrayAdapter<Task> {
    public TasksAdapter(Context context, @NonNull List<Task> objects){
        super(context, R.layout.item_task, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        Task item = getItem(position);

        CheckBox title = convertView.findViewById(R.id.checkBox_title);

        title.setText(item.getTitle());

        return convertView;
    }
}
