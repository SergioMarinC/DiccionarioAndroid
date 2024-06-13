package com.example.diccionario.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.example.diccionario.Modelo.Entrada;
import com.example.diccionario.SQLite.DBHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Clase para manejar las operaciones CRUD de la tabla 'entradas'
//Para probar usar Log.d()
public class DaoEntrada {

    DBHandler dbHandler;

    // Constructor que inicializa el manejador de la base de datos
    public DaoEntrada(Context context) {
        dbHandler = new DBHandler(context);
    }

    // Método para añadir una entrada a la base de datos
    public void addEntrada(Entrada entrada) {
        // Abre una conexión de escritura con la base de datos
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        // ContentValues para almacenar los valores a insertar
        ContentValues values = new ContentValues();

        // Se añaden los valores correspondientes a la tabla
        values.put(DBHandler.ESPANOL_COL, entrada.getEspanol());
        values.put(DBHandler.INGLES_COL, entrada.getIngles());
        values.put(DBHandler.ESPALABRA_COL, entrada.isEsPalabra());
        values.put(DBHandler.SONIDO_COL, entrada.getSonido());

        // Manejo de fechaDeIntroduccion
        if (entrada.getFechaDeIntroduccion() != null) {
            values.put(DBHandler.FECHADEINTRODUCCION_COL, entrada.getFechaDeIntroduccion().toString());
        } else {
            // Si fechaDeIntroduccion es nulo, asignarle la fecha y hora actual
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                entrada.setFechaDeIntroduccion(LocalDateTime.now());
            }
            values.put(DBHandler.FECHADEINTRODUCCION_COL, entrada.getFechaDeIntroduccion().toString());
        }

        // Manejo de ultimoTestRealizado
        if (entrada.getUltimoTestRealizado() != null) {
            values.put(DBHandler.ULTIMOTESTREALIZADO_COL, entrada.getUltimoTestRealizado().toString());
        } else {
            values.putNull(DBHandler.ULTIMOTESTREALIZADO_COL);
        }

        values.put(DBHandler.NUMERODEACIERTOS_COL, entrada.getNumeroDeAciertos());

        // Realiza un insert de los valores
        db.insert(DBHandler.TABLE_NAME, null, values);
        // Cierra la conexión
        db.close();
    }

    // Método para borrar una entrada de la base de datos
    public void deleteEntrada(int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        // Utiliza un array de String para evitar problemas de inyección SQL
        db.delete(DBHandler.TABLE_NAME, DBHandler.ID_COL + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Método para actualizar una entrada en la base de datos
    public void updateEntrada(Entrada entrada) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHandler.ESPANOL_COL, entrada.getEspanol());
        values.put(DBHandler.INGLES_COL, entrada.getIngles());
        values.put(DBHandler.ESPALABRA_COL, entrada.isEsPalabra());
        values.put(DBHandler.SONIDO_COL, entrada.getSonido());

        // Manejo de fechaDeIntroduccion
        if (entrada.getFechaDeIntroduccion() != null) {
            values.put(DBHandler.FECHADEINTRODUCCION_COL, entrada.getFechaDeIntroduccion().toString());
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                entrada.setFechaDeIntroduccion(LocalDateTime.now());
            }
            values.put(DBHandler.FECHADEINTRODUCCION_COL, entrada.getFechaDeIntroduccion().toString());
        }

        // Manejo de ultimoTestRealizado
        if (entrada.getUltimoTestRealizado() != null) {
            values.put(DBHandler.ULTIMOTESTREALIZADO_COL, entrada.getUltimoTestRealizado().toString());
        } else {
            values.putNull(DBHandler.ULTIMOTESTREALIZADO_COL);
        }

        values.put(DBHandler.NUMERODEACIERTOS_COL, entrada.getNumeroDeAciertos());

        // Actualiza la entrada en la base de datos
        db.update(DBHandler.TABLE_NAME, values, DBHandler.ID_COL + " = ?", new String[]{String.valueOf(entrada.getId())});
        db.close();
    }

    // Método para obtener todas las entradas de la base de datos
    public ArrayList<Entrada> getAllEntradas() {
        ArrayList<Entrada> entradas = new ArrayList<>();
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        // Realiza una consulta a la base de datos para obtener todas las entradas
        Cursor cursor = db.query(DBHandler.TABLE_NAME, null, null, null, null, null, null);

        // Comprueba si hay resultados
        if (cursor.moveToFirst()) {
            DateTimeFormatter formatter = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            }
            // Itera sobre los resultados
            do {
                String espanol = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.ESPANOL_COL));
                String ingles = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.INGLES_COL));
                boolean esPalabra = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.ESPALABRA_COL)) == 1;
                String sonido = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.SONIDO_COL));
                String fechaDeIntroduccionStr = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.FECHADEINTRODUCCION_COL));
                String ultimoTestRealizadoStr = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.ULTIMOTESTREALIZADO_COL));
                int numeroDeAciertos = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.NUMERODEACIERTOS_COL));
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.ID_COL));

                // Manejo de LocalDateTime
                LocalDateTime fechaDeIntroduccion = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    fechaDeIntroduccion = LocalDateTime.parse(fechaDeIntroduccionStr, formatter);
                }
                LocalDateTime ultimoTestRealizado = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ultimoTestRealizado = ultimoTestRealizadoStr != null ? LocalDateTime.parse(ultimoTestRealizadoStr, formatter) : null;
                }

                // Crea una nueva instancia de Entrada y la añade a la lista
                Entrada entrada = new Entrada(espanol, ingles, esPalabra, sonido, fechaDeIntroduccion, ultimoTestRealizado, numeroDeAciertos, id);
                entradas.add(entrada);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return entradas;
    }
}
