package com.example.tareaunomyfirstapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.example.tareaunomyfirstapp.databinding.ActivityMainBinding;

import java.util.Calendar;




public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DatePicker datePicker;
    private String fechaNacimiento = ""; // Variable para almacenar la fecha seleccionada
    private CheckBox checkboxJava;
    private CheckBox checkboxJS;
    private CheckBox checkboxC;
    private CheckBox checkboxPython;
    private CheckBox checkboxGolang;
    private CheckBox checkboxCsharp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner spinner = (Spinner) findViewById(R.id.spinner_genre);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genres_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        datePicker = findViewById(R.id.datePicker);
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Actualiza la variable fechaNacimiento cuando cambia la fecha
                        fechaNacimiento = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    }
                });


        checkboxJava = findViewById(R.id.checkbox_java);
        checkboxJS = findViewById(R.id.checkbox_js);
        checkboxC = findViewById(R.id.checkbox_c);
        checkboxPython = findViewById(R.id.checkbox_python);
        checkboxGolang = findViewById(R.id.checkbox_golang);
        checkboxCsharp = findViewById(R.id.checkbox_csharp);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        int radioButtonId = Integer.parseInt(view.getTag().toString()); //Obtiene el valor entero asignado en el XML

        // Check which radio button was clicked
        switch(radioButtonId) {
            case 1:
                if (checked){
                    checkboxJava.setEnabled(true);
                    checkboxJS.setEnabled(true);
                    checkboxC.setEnabled(true);
                    checkboxPython.setEnabled(true);
                    checkboxGolang.setEnabled(true);
                    checkboxCsharp.setEnabled(true);
                }
                break;
            case 2:
                if (checked){
                    checkboxJava.setEnabled(false);
                    checkboxJS.setEnabled(false);
                    checkboxC.setEnabled(false);
                    checkboxPython.setEnabled(false);
                    checkboxGolang.setEnabled(false);
                    checkboxCsharp.setEnabled(false);


                    checkboxJava.setChecked(false);
                    checkboxJS.setChecked(false);
                    checkboxC.setChecked(false);
                    checkboxPython.setChecked(false);
                    checkboxGolang.setChecked(false);
                    checkboxCsharp.setChecked(false);
                }
                break;
        }
    }



    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        // Obtén el valor de los EditText
        String nombre = binding.editNameField.getText().toString();
        String apellido = binding.editLastnameField.getText().toString();

        // Obtén el valor seleccionado en el Spinner
        String genero = binding.spinnerGenre.getSelectedItem().toString();

        // Obtén el valor seleccionado en el RadioGroup
        int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedRadioButtonId);
        String meGustaProgramar = radioButton.getText().toString().equals(getString(R.string.radius_yes)) ? "Me gusta programar." : "No me gusta la programación";

        StringBuilder lenguajes = new StringBuilder();
        if (checkboxJava.isChecked()) {
            lenguajes.append(getString(R.string.java)).append(", ");
        }
        if (checkboxJS.isChecked()) {
            lenguajes.append(getString(R.string.js)).append(", ");
        }
        if (checkboxC.isChecked()) {
            lenguajes.append(getString(R.string.c_c)).append(", ");
        }
        if (checkboxPython.isChecked()) {
            lenguajes.append(getString(R.string.python)).append(", ");
        }
        if (checkboxGolang.isChecked()) {
            lenguajes.append(getString(R.string.golang)).append(", ");
        }
        if (checkboxCsharp.isChecked()) {
            lenguajes.append(getString(R.string.c)).append(", ");
        }

        // Construye el mensaje final
        String message = "Hola! Mi nombre es: " + nombre + " " + apellido + ".\n\nSoy " + genero + ", y nací en " + fechaNacimiento + ".\n\n" + meGustaProgramar;

        // Si se seleccionaron lenguajes, agrégalos al mensaje
        if (lenguajes.length() > 0) {
            message += " Mis lenguajes favoritos son: " + lenguajes.substring(0, lenguajes.length() - 2) + ".";
        }

        intent.putExtra("value", message);
        startActivity(intent);
    }


    public void cleanInputs(View view) {
        //Limpiar los input texto
        binding.editNameField.setText("");
        binding.editLastnameField.setText("");

        //Poniendo en 0 la opcion de género
        binding.spinnerGenre.setSelection(0);

        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Establecer la fecha actual en el DatePicker
        binding.datePicker.updateDate(year, month, day);

        //Seleccionar el radiobutton por defecto
        binding.radioYes.setChecked(true);

        //Deseleccionar todas las opciones de checkbox
        binding.checkboxJava.setChecked(false);
        binding.checkboxJs.setChecked(false);
        binding.checkboxC.setChecked(false);
        binding.checkboxPython.setChecked(false);
        binding.checkboxGolang.setChecked(false);
        binding.checkboxCsharp.setChecked(false);
    }
}