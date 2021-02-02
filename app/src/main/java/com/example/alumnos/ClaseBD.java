package com.example.alumnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ClaseBD extends SQLiteOpenHelper {

    public ClaseBD(@Nullable Context context) {
        super(context, ConstantesBD.BD_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la tabla Persona
        db.execSQL(ConstantesBD.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Borrar la tabla Persona si existe
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBD.TABLE_NAME);

        //Volvera a crear
        onCreate(db);

    }

    public long insertarDatos(String nombre, String apellido, String edad, String telefono, String email){

        //Creamos BD para almacenar registros en la tabla
        SQLiteDatabase db = getWritableDatabase();

        //Creamos la fecha actual que va a ser la fecha de registro
        String time = "" + System.currentTimeMillis();

        //Creamos un ContentValues con los valores a almacenar
        ContentValues valores = new ContentValues();
        valores.put(ConstantesBD.C_NAME, nombre);
        valores.put(ConstantesBD.C_SUBNAME, apellido);
        valores.put(ConstantesBD.C_AGE, edad);
        valores.put(ConstantesBD.C_PHONE, telefono);
        valores.put(ConstantesBD.C_EMAIL, email);
        valores.put(ConstantesBD.C_ADDEDTIME, time);

       long id =  db.insert(ConstantesBD.TABLE_NAME, null, valores);

        Log.i("INSERCIÓN PERSONA: ", "El número de registro de la intersección de la persona es: " + id);

        return id;
    }

    //Devolver todas las personas registradas en la app
    public ArrayList<Persona> mostrarPersonas(String cAddedtime){
        ArrayList<Persona> personas;
        personas = new ArrayList<>();

           String query = " SELECT * FROM " + ConstantesBD.TABLE_NAME;

           SQLiteDatabase db = this.getWritableDatabase();
           Cursor cursor = db.rawQuery(query, null);

           if(cursor.moveToFirst()){
               do{

                   Persona persona = new Persona(
                       cursor.getInt(cursor.getColumnIndex(ConstantesBD.C_ID)) + "",
                           cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NAME)),
                           cursor.getString(cursor.getColumnIndex(ConstantesBD.C_SUBNAME)),
                           cursor.getString(cursor.getColumnIndex(ConstantesBD.C_AGE)),
                           cursor.getString(cursor.getColumnIndex(ConstantesBD.C_PHONE)),
                           cursor.getString(cursor.getColumnIndex(ConstantesBD.C_EMAIL))


                   );
                   personas.add(persona);
               }while(cursor.moveToNext());
           }
           cursor.close();
           db.close();

           return personas;

    }

    //Devolver una persona
    public Persona getPersonabyId(String id){
         Persona p = null;

        String query = " SELECT * FROM " + ConstantesBD.TABLE_NAME + " WHERE " + ConstantesBD.C_ID + "=\"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                p = new Persona(
                        cursor.getInt(cursor.getColumnIndex(ConstantesBD.C_ID)) + "",
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_NAME)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_SUBNAME)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_AGE)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_PHONE)),
                        cursor.getString(cursor.getColumnIndex(ConstantesBD.C_EMAIL))
                        //cursor.getString(cursor.getColumnIndex(ConstantesBD.C_ADDEDTIME))

                );

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return p;

    }

    public void eliminarPersonaById(String id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(ConstantesBD.TABLE_NAME, ConstantesBD.C_ID + " = ? ", new String[]{id});

        db.close();

    }

    public long actualizarDatos(String txt_nombre, String txt_apellido, String txt_edad, String txt_tel, String txt_email) {
        return 0;
    }
}
