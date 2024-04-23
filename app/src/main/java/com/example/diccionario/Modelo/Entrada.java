package com.example.diccionario.Modelo;

import android.os.Build;

import java.time.LocalDateTime;

public class Entrada {
    private String espanol;
    private String ingles;
    private boolean esPalabra;
    private String sonido;
    private LocalDateTime fechaDeIntroduccion;
    private LocalDateTime ultimoTestRealizado;
    private int numeroDeAciertos;

    public  Entrada(String espanol, String ingles, boolean esPalabra){
        this.espanol = espanol;
        this.ingles = ingles;
        this.esPalabra = esPalabra;
        this.sonido = espanol + ".wav";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.fechaDeIntroduccion = LocalDateTime.now();
        }
        this.ultimoTestRealizado = null;
        this.numeroDeAciertos = 0;
    }

    public Entrada(String espanol, String ingles, boolean esPalabra, String sonido, int numeroDeAciertos) {
        this.espanol = espanol;
        this.ingles = ingles;
        this.esPalabra = esPalabra;
        this.sonido = sonido;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.fechaDeIntroduccion = LocalDateTime.now();
        }
        this.ultimoTestRealizado = null;
        this.numeroDeAciertos = numeroDeAciertos;
    }

    public Entrada(String espanol, String ingles, boolean esPalabra, String sonido, LocalDateTime fechaDeIntroduccion, LocalDateTime ultimoTestRealizado, int numeroDeAciertos) {
        this.espanol = espanol;
        this.ingles = ingles;
        this.esPalabra = esPalabra;
        this.sonido = sonido;
        this.fechaDeIntroduccion = fechaDeIntroduccion;
        this.ultimoTestRealizado = ultimoTestRealizado;
        this.numeroDeAciertos = numeroDeAciertos;
    }

    public String getEspanol() {
        return espanol;
    }

    public void setEspanol(String espanol) {
        this.espanol = espanol;
    }

    public String getIngles() {
        return ingles;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }

    public boolean isEsPalabra() {
        return esPalabra;
    }

    public void setEsPalabra(boolean esPalabra) {
        this.esPalabra = esPalabra;
    }

    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }

    public LocalDateTime getFechaDeIntroduccion() {
        return fechaDeIntroduccion;
    }

    public void setFechaDeIntroduccion(LocalDateTime fechaDeIntroduccion) {
        this.fechaDeIntroduccion = fechaDeIntroduccion;
    }

    public LocalDateTime getUltimoTestRealizado() {
        return ultimoTestRealizado;
    }

    public void setUltimoTestRealizado(LocalDateTime ultimoTestRealizado) {
        this.ultimoTestRealizado = ultimoTestRealizado;
    }

    public int getNumeroDeAciertos() {
        return numeroDeAciertos;
    }

    public void setNumeroDeAciertos(int numeroDeAciertos) {
        this.numeroDeAciertos = numeroDeAciertos;
    }
}
