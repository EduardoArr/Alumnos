package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.alumnos.R;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    EditText apellidos;
    EditText edad;
    EditText telefono;
    EditText email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}