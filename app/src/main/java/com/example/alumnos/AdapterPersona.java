package com.example.alumnos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPersona extends RecyclerView.Adapter<AdapterPersona.HolderAlumno> {
    //Variables
    private Context contexto;
    private ArrayList<Persona> personas;

    //Declaramos un objeto de tipo BD que hemos creado
    ClaseBD clasebd;

    //Constructor
    public AdapterPersona(Context contexto, ArrayList<Persona> personas) {
        this.contexto = contexto;
        this.personas = personas;
        clasebd = new ClaseBD(contexto);
    }

    @NonNull
    @Override
    public AdapterPersona.HolderAlumno onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflamos el layout con el de lista_alumno
        View view = LayoutInflater.from(contexto).inflate(R.layout.lista_persona, parent, false);

        //Devolvemos el HolderAlumno con todas las vistas de lista_alumno inicializadas, es donde pondremos los datos de los alumnos
        return new HolderAlumno(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAlumno holder, final int position) {
        //Con este método obtenemos datos, los establecemos y vemos los clicks

        //Primero obtenemos los datos de cada alumno por la posición
        Persona persona = personas.get(position);
         final String id = persona.getId();

        final String nombre = persona.getNombre();
        Toast.makeText(contexto, nombre + " " + persona.getEdad(), Toast.LENGTH_SHORT).show();
        final String apellido = persona.getApellido();
        final String imagen = persona.getImagen();
        //Toast.makeText(contexto, nombre + " " + imagen, Toast.LENGTH_SHORT).show();
        final String telefono = persona.getTelefono();
        final String email = persona.getEmail();
        final String edad = persona.getEdad();


        //Estos datos los mostramos en las vistas correspondientes de lista_alumno que están recogidas en el holder
        holder.nombre.setText(nombre);
        holder.telefono.setText(telefono);
        holder.edad.setText(edad);
        holder.email.setText(email);

        //para la imagen, si el usuario no quiere asignar imagen, la uri será nula por lo que configuramos una imagen predeterminada para este caso
        if(imagen == null){
            Toast.makeText(contexto, nombre + " " + imagen, Toast.LENGTH_SHORT).show();
            holder.imagen.setImageResource(R.drawable.ic_baseline_person_24);
        }else{
            holder.imagen.setImageURI(Uri.parse(imagen));
        }

        //Si clickamos en un holder (en un item de la lista) nos llevará a la pantalla con los detalles del alumno
        //Además, tendremos que pasarle el id de dicho alumno
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, MostrarPersonaActivity.class);
                Toast.makeText(contexto, nombre + " " + id, Toast.LENGTH_SHORT).show();
                intent.putExtra("id", id);
                contexto.startActivity(intent);
            }
        });

        //Si clickamos en el botón de opciones
        holder.btn_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesDialogo(""+position, imagen, id, nombre, apellido, telefono, email, edad);
            }
        });

    }

    //Hacemos un método para mostrar el diálogo del botón de editar y borrar
    public void mostrarOpcionesDialogo(final String posicion, final String imagen, final String id, final String nombre, final String apellido, final String telefono, final String email,
                                       final String edad){
        //Creamos un array de Strings con las opciones que van a aparecer en el diálogo

        String[] opciones = {"Editar", "Eliminar"};

        //Creamos el AlertDialog con las opciones
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setTitle("Selecciona una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //El which a 0 indica la primera opción que es Editar
                if(which == 0){
                    //Si clickamos en Editar vamos a la Actividad de AddAlumnoActivity para poder editar los datos
                    //le tenemos que mandar todos los datos que tiene ese alumno para que los muestre
                    Intent intent = new Intent(contexto, Add_Persona.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("apellido", apellido);
                    intent.putExtra("edad", edad);
                    intent.putExtra("telefono", telefono);
                    intent.putExtra("email", email);

                    intent.putExtra("imagen", imagen);
                    //Añadimos otro dato para saber si viene de editar
                    intent.putExtra("REQUEST_EDICION_ALUMNO", true);
                    contexto.startActivity(intent);
                }
                //Si which es 1 ha clickado en eliminar
                else if(which == 1){
                    //Creamos otro diálogo para ver si estamos seguros de borrarlo
                    AlertDialog.Builder eliminarDialogo = new AlertDialog.Builder(contexto);
                    eliminarDialogo.setTitle("Eliminarás un alumno");
                    eliminarDialogo.setMessage("¿Estás seguro de eliminarlo?");
                    eliminarDialogo.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //en este caso borramos al alumno que queremos
                            clasebd.eliminarPersonaById(id);
                            //y actualizamos la lista de alumnos otra vez llamando al método onResume del MainActivity
                            ((MainActivity)contexto).onResume();
                        }
                    });
                    eliminarDialogo.setNegativeButton("No", null);
                    eliminarDialogo.create().show();

                }
            }
        });

        //Creamos y mostramos el diálogo
        builder.create().show();

    }

    @Override
    public int getItemCount() {
        //Devuelve el número de alumnos almacenados
        return personas.size();
    }

    public class HolderAlumno extends RecyclerView.ViewHolder{
        //En esta clase cogemos todos los elementos de lista_alumnos para poder utilizarlos en AdapterAlumno y rellenar el recyclerview posteriormente
        ImageView imagen;
        TextView nombre, telefono, email, edad;
        ImageButton btn_mas;

        public HolderAlumno(@NonNull View itemView) {
            super(itemView);

            //Inicializamos los elementos de la vista
            imagen = itemView.findViewById(R.id.foto);
            nombre = itemView.findViewById(R.id.tv_nombre);
            telefono = itemView.findViewById(R.id.tv_tel);
            email = itemView.findViewById(R.id.tv_email);
            edad = itemView.findViewById(R.id.tv_fecha);
            btn_mas = itemView.findViewById(R.id.btn_opciones);
        }
    }
}
