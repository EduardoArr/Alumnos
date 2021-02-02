package com.example.alumnos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarPersonaActivity extends AppCompatActivity {

    ImageView imagenAlumno;
    TextView nombre;
    TextView apellido;
    TextView telefono;
    TextView email;
    TextView edad;

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
        edad = findViewById(R.id.fnacimiento);

        mostrarDetallePersona(id);

    }

    public void mostrarDetallePersona(String id){
        persona = claseBD.getPersonabyId(id);

        nom = persona.getNombre();
        ape = persona.getApellido();
        age = persona.getEdad();
        tele = persona.getTelefono();
        ema = persona.getEmail();

        nombre.setText(nom);
        apellido.setText(ape);
        Toast.makeText(this, "" + persona.getEdad(), Toast.LENGTH_SHORT).show();
        edad.setText(age);
        telefono.setText(tele);
        email.setText(ema);

       /* if(persona.getImagen().equals("null")){
            imagenAlumno.setImageResource(R.drawable.ic_launcher_foreground);
        }
        else{
            imagenAlumno.setImageURI(Uri.parse(persona.getImagen()));
        }*/
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}