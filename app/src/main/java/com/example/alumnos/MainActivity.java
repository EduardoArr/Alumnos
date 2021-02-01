package com.example.alumnos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addItem;
    RecyclerView persona;


    ActionBar actionBar;

    String ordenarPorNombreAsc = ConstantesBD.C_NAME + "ASC";
    String ordenarPorNombreDesc = ConstantesBD.C_NAME + "DESC";
    String ordenarPorNuevo = ConstantesBD.C_ADDEDTIME + "Desc";

    String condicionOrdenarActual = ordenarPorNuevo;
    ClaseBD claseBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItem = findViewById(R.id.btn_check);
        persona = findViewById(R.id.personas);

        claseBD = new ClaseBD(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Personas");

        mostrarPersonas(condicionOrdenarActual);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_Persona.class);
                intent.putExtra("REQUEST EDICION PERSONA", false);
                startActivity(intent);
            }
        });
    }

    //nos muestra los alumnos
    private void mostrarPersonas(String condicionOrdenar){
        //cambiamos la condicion actual de odenar por esta
        condicionOrdenarActual = condicionOrdenar;

        //Utilizamos la clase AdapterAlumno para cargar los alumnos del recyclerview
        AdapterPersona adapter = new AdapterPersona(this, claseBD.mostrarPersonas(condicionOrdenar));

        //utilizamos el adaptador en el recyclerview
        persona.setAdapter(adapter);

        //ponemos en el ActionBar el numero de alumnos
        //actionBar.setSubtitle("Total alumnos: " + claseBD.getItemsCount());
    }

    //Buscará alumnos que cumplan una condicion
    private void buscarPersona(String condicion){
        //Utilizamos la clase AdapterAlumno para cargar los alumnos en el recyclerview
        //AdapterPersona adapter = new AdapterPersona(MainActivity.this, claseBD.searchPersona(condicion));

        //utilizamos el adaptador en el recyclerview
       //persona.setAdapter(adapter);
    }

    protected void onResume(){
        super.onResume();
        mostrarPersonas(condicionOrdenarActual);
    }

    //Opciones del menu
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflamos el menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
/*
        //Cogemos la opcion de buscar
        MenuItem buscar = menu.findItem(R.id.bucar);

        //Creamos una variable SearchView
        SearchView buscarV = (SearchView)buscar.getActionView();
        buscarV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //buscar cuando se hace click en el botón de búsqueda del teclado
                buscarPersona(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //bussca mientras escribes
                buscarPersona(newText);
                return false;
            }
        });
*/
        return super.onCreateOptionsMenu(menu);
    }

}