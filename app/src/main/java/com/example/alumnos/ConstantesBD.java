package com.example.alumnos;

public class ConstantesBD {
    //Nombre de la BD
    public static final String BD_NAME = "CLASE";

    //Versión de la BD
    public static  final int BD_VERSION = 1;

    //Nombre de la tabla
    public static final String TABLE_NAME = "PERSONA";

    //Campos de la tabla
    public static final String C_ID = "ID";
    public static final String C_NAME = "NOMBRE";
    public static final String C_SUBNAME = "APELLIDO";
    public static final String C_AGE = "EDAD";
    public static final String C_PHONE = "TELEFONO";
    public static final String C_EMAIL = "CORREO";
    public static final String C_ADDEDTIME = "FECHA_ALTA";


    //Código de creación de la tabla
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_NAME + "  TEXT, "
            + C_SUBNAME + " TEXT, "
            + C_AGE + " TEXT, "
            + C_PHONE + " TEXT, "
            + C_EMAIL + " TEXT, "
            + C_ADDEDTIME + " TEXT"
            + ")";
}
