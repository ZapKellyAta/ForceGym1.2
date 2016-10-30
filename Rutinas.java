package com.example.kellyjohanazapataestrada.forcegym;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Rutinas extends SQLiteOpenHelper
{
    private String DATA_BASE_NAME ="Forcegym";
    private int DATA_BASE_VERSION = 1;

    //Creamos la tabla a trabajar
    String sqlCreate = "CREATE TABLE RUTINAS("+
            "idRutina INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"nombreRutina TEXT,"
            +"tipoRutina TEXT,"
            +"descripcion TEXT)";

    public Rutinas(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS RUTINAS");
        sqLiteDatabase.execSQL(sqlCreate);
    }

}
