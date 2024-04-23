package com.example.diccionario.Controlador;

import com.example.diccionario.DAO.DaoEntrada;
import com.example.diccionario.Modelo.Entrada;

import java.util.ArrayList;

public class ControladorEntrada {
    private static DaoEntrada daoEntrada;

    public static void init(){
        daoEntrada = new DaoEntrada();
    }

    public static ArrayList<Entrada> getEntradas() {
        // Método para obtener las entradas
        return daoEntrada.getEntradas();
    }

    public static void agregarEntrada(Entrada nuevaEntrada){
        ArrayList<Entrada> entradas = daoEntrada.getEntradas();
        entradas.add(nuevaEntrada);
        daoEntrada.setEntradas(entradas);
    }

    public static void eliminarEntrada(Entrada entrada){
        ArrayList<Entrada> entradas = daoEntrada.getEntradas();
        entradas.remove(entrada);
        daoEntrada.setEntradas(entradas);
    }
}
