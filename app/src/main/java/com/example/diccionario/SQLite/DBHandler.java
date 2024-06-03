package com.example.diccionario.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "diccionario.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "entradas";
    public static final String ID_COL = "id";
    public static final String ESPANOL_COL = "espanol";
    public static final String INGLES_COL = "ingles";
    public static final String ESPALABRA_COL = "esPalabra";
    public static final String SONIDO_COL = "sonido";
    public static final String FECHADEINTRODUCCION_COL = "fechaDeIntroduccion";
    public static final String ULTIMOTESTREALIZADO_COL = "ultimoTestRealizado";
    public static final String NUMERODEACIERTOS_COL = "numeroDeAciertos";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //Query de creación de base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Comprueba si el número introducido en espalabra es 0 o 1 para simular el uso de Boolean
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ESPANOL_COL + " TEXT NOT NULL, "
                + INGLES_COL + " TEXT NOT NULL, "
                + ESPALABRA_COL + " INTEGER NOT NULL CHECK (esPalabra IN(0,1)), "
                + SONIDO_COL + " TEXT, "
                + FECHADEINTRODUCCION_COL + " TEXT NOT NULL, "
                + ULTIMOTESTREALIZADO_COL + " TEXT, "
                + NUMERODEACIERTOS_COL + " INTEGER NOT NULL);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
