package com.example.diccionario.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Clase para manejar la base de datos SQLite
public class DBHandler extends SQLiteOpenHelper {

    // Nombre de la base de datos
    public static final String DB_NAME = "diccionario.db";
    // Versión de la base de datos
    public static final int DB_VERSION = 1;
    // Nombre de la tabla
    public static final String TABLE_NAME = "entradas";
    // Columnas de la tabla
    public static final String ID_COL = "id";
    public static final String ESPANOL_COL = "espanol";
    public static final String INGLES_COL = "ingles";
    public static final String ESPALABRA_COL = "esPalabra";
    public static final String SONIDO_COL = "sonido";
    public static final String FECHADEINTRODUCCION_COL = "fechaDeIntroduccion";
    public static final String ULTIMOTESTREALIZADO_COL = "ultimoTestRealizado";
    public static final String NUMERODEACIERTOS_COL = "numeroDeAciertos";

    // Constructor para crear el manejador de base de datos
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Método llamado cuando la base de datos es creada por primera vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Si se quiere crear una categoria extra añadir de esta forma, dentro del oncreate pero con otro query, ejecutar estos con db.execSQL
        String createCategoriasTableQuery = "CREATE TABLE " + CATEGORIAS_TABLE_NAME + " ("
                + CATEGORIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CATEGORIA_NOMBRE + " TEXT NOT NULL UNIQUE);";*/
        // Query de creación de la tabla
        // CHECK (esPalabra IN(0,1)) asegura que solo se pueden insertar valores 0 o 1 en esPalabra
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ESPANOL_COL + " TEXT NOT NULL, "
                + INGLES_COL + " TEXT NOT NULL, "
                + ESPALABRA_COL + " INTEGER NOT NULL CHECK (esPalabra IN(0,1)), "
                + SONIDO_COL + " TEXT, "
                + FECHADEINTRODUCCION_COL + " TEXT NOT NULL, "
                + ULTIMOTESTREALIZADO_COL + " TEXT, "
                + NUMERODEACIERTOS_COL + " INTEGER NOT NULL);";

        // Ejecuta el query de creación de la tabla
        db.execSQL(query);
    }

    // Método llamado cuando la base de datos necesita ser actualizada
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Elimina la tabla antigua si existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + CATEGORIAS_TABLE_NAME);
        // Crea la nueva tabla
        onCreate(db);
    }
}
