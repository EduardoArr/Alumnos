package com.example.alumnos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MostrarPersonaActivity extends AppCompatActivity {

    ImageView imagenAlumno;
    EditText nombre;
    EditText apellido;
    EditText telefono;
    EditText email;
    EditText edad;

    ActionBar actionBar;
    ClaseBD claseBD;

    Persona persona;

    String ema;
    String tele;
    String age;
    String ape;
    String nom;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_persona);
        claseBD = new ClaseBD(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Detalle del Alumno");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");

        claseBD = new ClaseBD(this);

        imagenAlumno = findViewById(R.id.foto);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        telefono = findViewById(R.id.telefono);
        email = findViewById(R.id.email);
        edad = findViewById(R.id.edad);

        nom = nombre.getText().toString();
        ape = apellido.getText().toString();
        age = edad.getText().toString();
        tele = telefono.getText().toString();
        ema = email.getText().toString();

        persona = new Persona(id, nom, ape, age, tele, ema);

        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());
        edad.setText(persona.getEdad());
        telefono.setText(persona.getTelefono());
        email.setText(persona.getEmail());

        if(persona.getImagen().equals("null")){
            imagenAlumno.setImageResource(R.drawable.ic_launcher_foreground);
        }
        else{
            imagenAlumno.setImageURI(Uri.parse(persona.getImagen()));
        }
    }



    public boolean onSupportNavigateUp(){
        return super.onSupportNavigateUp();
    }
}