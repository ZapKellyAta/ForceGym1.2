package com.example.kellyjohanazapataestrada.forcegym;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Usuarios extends SQLiteOpenHelper
{
    private String DATA_BASE_NAME ="Forcegym";
    private int DATA_BASE_VERSION = 1;

    //Creamos la tabla a trabajar
    String sqlCreate = "CREATE TABLE USERS("+
            "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"usuario TEXT,"
            +"contrasena TEXT,"
            +"recontrasena TEXT,"
            +"email TEXT,"
            +"estatura TEXT,"
            +"IMC TEXT,"
            +"categoria TEXT,"
            +"peso TEXT)";
    public Usuarios(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(sqlCreate);//Ejecutamos la sentencia anterior
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USERS");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
