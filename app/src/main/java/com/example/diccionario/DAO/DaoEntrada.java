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

public class DaoEntrada {

    DBHandler dbHandler;

    public DaoEntrada(Context context){

        dbHandler = new DBHandler(context);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            entradas.add(new Entrada("Hola", "Hello", true, "hola.wav", LocalDateTime.of(2023, 4, 10, 10, 30), null, 0));
            entradas.add(new Entrada("Adiós", "Goodbye", true, "adios.wav", LocalDateTime.of(2023, 4, 10, 10, 35), null, 0));
            entradas.add(new Entrada("Gato", "Cat", true, "gato.wav", LocalDateTime.of(2023, 4, 10, 10, 40), null, 0));
            entradas.add(new Entrada("Perro", "Dog", true, "perro.wav", LocalDateTime.of(2023, 4, 10, 10, 45), null, 0));
            entradas.add(new Entrada("Casa", "House", true, "casa.wav", LocalDateTime.of(2023, 4, 10, 10, 50), null, 0));
            entradas.add(new Entrada("Coche", "Car", true, "coche.wav", LocalDateTime.of(2023, 4, 10, 10, 55), null, 0));
            entradas.add(new Entrada("Rojo", "Red", true, "rojo.wav", LocalDateTime.of(2023, 4, 10, 11, 0), null, 0));
            entradas.add(new Entrada("Azul", "Blue", true, "azul.wav", LocalDateTime.of(2023, 4, 10, 11, 5), null, 0));
            entradas.add(new Entrada("Verde", "Green", true, "verde.wav", LocalDateTime.of(2023, 4, 10, 11, 10), null, 0));
            entradas.add(new Entrada("Feliz", "Happy", true, "feliz.wav", LocalDateTime.of(2023, 4, 10, 11, 15), null, 0));
            entradas.add(new Entrada("Libro", "Book", true, "libro.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Bicicleta", "Bicycle", true, "bicicleta.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Computadora", "Computer", true, "computadora.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Teléfono", "Phone", true, "telefono.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Maleta", "Suitcase", true, "maleta.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Sombrero", "Hat", true, "sombrero.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Pluma", "Pen", true, "pluma.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Lápiz", "Pencil", true, "lapiz.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Gafas", "Glasses", true, "gafas.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Agua", "Water", true, "agua.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Pan", "Bread", true, "pan.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Leche", "Milk", true, "leche.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Silla", "Chair", true, "silla.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Mesa", "Table", true, "mesa.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Cuchillo", "Knife", true, "cuchillo.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Tenedor", "Fork", true, "tenedor.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Cuchara", "Spoon", true, "cuchara.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Servilleta", "Napkin", true, "servilleta.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Plato", "Plate", true, "plato.wav", LocalDateTime.now(), null, 0));
            entradas.add(new Entrada("Vaso", "Glass", true, "vaso.wav", LocalDateTime.now(), null, 0));*/
        }

    //Añadir entrada a la base de datos
    public void addEntrada(Entrada entrada){
        //Abre conexión de escritura con la base de datos
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        //Se añaden los valores correspondientes a las tabla
        String espanolValue = entrada.getEspanol();
        Log.d("DB_INSERT", "Valor de espanol: " + espanolValue);
        values.put(DBHandler.ESPANOL_COL, espanolValue);

        String inglesValue = entrada.getIngles();
        Log.d("DB_INSERT", "Valor de ingles: " + inglesValue);
        values.put(DBHandler.INGLES_COL, inglesValue);

        boolean esPalabraValue = entrada.isEsPalabra();
        Log.d("DB_INSERT", "Valor de esPalabra: " + esPalabraValue);
        values.put(DBHandler.ESPALABRA_COL, esPalabraValue);

        String sonidoValue = entrada.getSonido();
        Log.d("DB_INSERT", "Valor de sonido: " + sonidoValue);
        values.put(DBHandler.SONIDO_COL, sonidoValue);

        String fechaDeIntroduccionValue = entrada.getFechaDeIntroduccion().toString();
        Log.d("DB_INSERT", "Valor de fechaDeIntroduccion: " + fechaDeIntroduccionValue);
        if (entrada.getFechaDeIntroduccion() != null) {
            values.put(DBHandler.FECHADEINTRODUCCION_COL, entrada.getFechaDeIntroduccion().toString());
        } else {
            // Si fechaDeIntroduccion es nulo, asignarle la fecha y hora actual
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                entrada.setFechaDeIntroduccion(LocalDateTime.now());
            }
            values.put(DBHandler.FECHADEINTRODUCCION_COL, entrada.getFechaDeIntroduccion().toString());
        }

        if (entrada.getUltimoTestRealizado() != null) {
            values.put(DBHandler.ULTIMOTESTREALIZADO_COL, entrada.getUltimoTestRealizado().toString());
        } else {
            values.putNull(DBHandler.ULTIMOTESTREALIZADO_COL);
        }

        int numeroDeAciertosValue = entrada.getNumeroDeAciertos();
        Log.d("DB_INSERT", "Valor de numeroDeAciertos: " + numeroDeAciertosValue);
        values.put(DBHandler.NUMERODEACIERTOS_COL, numeroDeAciertosValue);

        //Realiza un insert de los valores
        db.insert(DBHandler.TABLE_NAME, null, values);

        //Cierra conexión
        db.close();
    }

    //Borrado de Entrada
    public void deleteEntrada(int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        //Colección de string para evitar problemas con la inyección. Además, se convierte el int a String
        db.delete(DBHandler.TABLE_NAME, DBHandler.ID_COL + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<Entrada> getAllEntradas(){
        ArrayList<Entrada> entradas = new ArrayList<>();
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        Cursor cursor = db.query(DBHandler.TABLE_NAME, null, null, null, null, null, null);

        //Comprueba la primera fila
        //Luego hace un do while que obtiene datos hasta que se no haya más
        //tod esto lo añade a un array
        if (cursor.moveToFirst()) {
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            }
            do {
                String espanol = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.ESPANOL_COL));
                String ingles = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.INGLES_COL));
                boolean esPalabra = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.ESPALABRA_COL)) == 1;
                String sonido = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.SONIDO_COL));
                String fechaDeIntroduccionStr = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.FECHADEINTRODUCCION_COL));
                String ultimoTestRealizadoStr = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.ULTIMOTESTREALIZADO_COL));
                int numeroDeAciertos = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.NUMERODEACIERTOS_COL));
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHandler.ID_COL));

                LocalDateTime fechaDeIntroduccion = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    fechaDeIntroduccion = LocalDateTime.parse(fechaDeIntroduccionStr, formatter);
                }
                LocalDateTime ultimoTestRealizado = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    ultimoTestRealizado = ultimoTestRealizadoStr != null ? LocalDateTime.parse(ultimoTestRealizadoStr, formatter) : null;
                }

                Entrada entrada = new Entrada(espanol, ingles, esPalabra, sonido, fechaDeIntroduccion, ultimoTestRealizado, numeroDeAciertos, id);
                entradas.add(entrada);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return  entradas;
    }
}
