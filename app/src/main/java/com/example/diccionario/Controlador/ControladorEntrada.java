package com.example.diccionario.Controlador;

import android.content.Context;

import com.example.diccionario.DAO.DaoEntrada;
import com.example.diccionario.Modelo.Entrada;

import java.util.ArrayList;

public class ControladorEntrada {
    private static DaoEntrada daoEntrada;

    public static void init(Context context){
        if (daoEntrada == null){
            daoEntrada = new DaoEntrada(context);
        }
    }

    public static ArrayList<Entrada> getEntradas() {
        // Método para obtener las entradas
        return daoEntrada.getAllEntradas();
    }

    public static void agregarEntrada(Entrada nuevaEntrada){
        daoEntrada.addEntrada(nuevaEntrada);
    }

    public static void eliminarEntrada(Entrada entradaEliminar){
        daoEntrada.deleteEntrada(entradaEliminar.getId());
    }
}
