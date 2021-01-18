package com.example.alumnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

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

    public void insertarDatos(String nombre, String apellido, String edad, String telefono, String email){

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

    }
}
