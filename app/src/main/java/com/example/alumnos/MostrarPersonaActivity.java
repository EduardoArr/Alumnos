package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MostrarPersonaActivity extends AppCompatActivity {

    TextView textView;
    ClaseBD claseBD;
    String nombres = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_persona);

        textView = (TextView) findViewById(R.id.textView);
        claseBD = new ClaseBD(this);

        ArrayList<Persona> personas = claseBD.mostrarPersonas(ConstantesBD.C_ADDEDTIME);
        for(int i = 0; i<personas.size(); i++){
            Persona p = personas.get(i);
            nombres = nombres + " " + p.getNombre();
        }

        textView.setText(nombres);

    }
}