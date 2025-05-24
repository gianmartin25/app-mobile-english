package com.example.applicationtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Usuarios.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_CORREO = "correo";
    public static final String COLUMN_CONTRASENA = "contrasena";
    public static final String COLUMN_NIVEL = "nivel";
    public static final String COLUMN_AREA = "area";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Aqui estoy creando la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREAR_TABLA = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_CORREO + " TEXT, " +
                COLUMN_CONTRASENA + " TEXT, " +
                COLUMN_NIVEL + " TEXT, " +
                COLUMN_AREA + " TEXT)";
        db.execSQL(SQL_CREAR_TABLA);
    }

    //Aqui se va a actualizar la base de datos en caso de que cambie la version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    //Este metodo sirve para insertar los datos
    public boolean insertarUsuario(String nombre, String correo, String contrasena, String nivel, String area){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NOMBRE, nombre);
        valores.put(COLUMN_CORREO, correo);
        valores.put(COLUMN_CONTRASENA, contrasena);
        valores.put(COLUMN_NIVEL, nivel);
        valores.put(COLUMN_AREA, area);

        long resultado = db.insert(TABLE_USUARIOS, null, valores);
        db.close();
        return resultado != -1;
    }
}
