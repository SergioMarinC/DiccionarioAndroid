package com.example.diccionario.DAO;

import android.os.Build;

import com.example.diccionario.Modelo.Entrada;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DaoEntrada {

    private ArrayList<Entrada> entradas = new ArrayList<Entrada>();

    public DaoEntrada(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
            entradas.add(new Entrada("Vaso", "Glass", true, "vaso.wav", LocalDateTime.now(), null, 0));
        }

    }

    public ArrayList<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(ArrayList entradas) {
        this.entradas = entradas;
    }
}
