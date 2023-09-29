package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.todolist.databinding.ActivityMainBinding;
import com.example.todolist.fragments.ListViewFragment;
import com.example.todolist.fragments.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            replaceFragment(new RecyclerViewFragment());
        }

        if (binding != null){
            binding.bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();

                if(itemId == R.id.recyclerview){
                    //Cuando se clickea recyclerview:
                    replaceFragment(new RecyclerViewFragment());

                }else if(itemId == R.id.listview){
                    //Cuando se clickea listview:
                    replaceFragment(new ListViewFragment());
                }

                return true;
            });
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null); // Se agrega a la pila de retroceso
        fragmentTransaction.commit();
    }

}