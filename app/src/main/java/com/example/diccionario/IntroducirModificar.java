package com.example.diccionario;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diccionario.Adaptador.AdaptadorRecyclerView;
import com.example.diccionario.Controlador.ControladorEntrada;
import com.example.diccionario.Modelo.Entrada;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IntroducirModificar extends AppCompatActivity implements RecyclerViewInterface{

    private AdaptadorRecyclerView adaptadorRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_modificar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fabAgregarEntrada = findViewById(R.id.fabAgregarEntrada);

        adaptadorRecyclerView = new AdaptadorRecyclerView(this, ControladorEntrada.getEntradas(), this);
        recyclerView.setAdapter(adaptadorRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Añadir
        fabAgregarEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_editar_entrada, null);

                EditText editTextEspanol = popupView.findViewById(R.id.editTextEspanol);
                EditText editTextIngles = popupView.findViewById(R.id.editTextIngles);
                CheckBox checkBoxEsPalabra = popupView.findViewById(R.id.checkBoxEsPalabra);

                PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);

                Button btnGuardar = popupView.findViewById(R.id.btnGuardar);
                Button btnEliminar = popupView.findViewById(R.id.btnEliminar);
                btnEliminar.setVisibility(View.GONE);
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ControladorEntrada.agregarEntrada(new Entrada(editTextEspanol.getText().toString(), editTextIngles.getText().toString(), checkBoxEsPalabra.isChecked()));

                        // Notificar al adaptador que los datos han cambiado
                        adaptadorRecyclerView.notifyDataSetChanged();

                        // Cerrar el PopupWindow
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(int posicion) {

        Entrada entradaSeleccionada = ControladorEntrada.getEntradas().get(posicion);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_editar_entrada, null);

        EditText editTextEspanol = popupView.findViewById(R.id.editTextEspanol);
        EditText editTextIngles = popupView.findViewById(R.id.editTextIngles);
        CheckBox checkBoxEsPalabra = popupView.findViewById(R.id.checkBoxEsPalabra);

        editTextEspanol.setText(entradaSeleccionada.getEspanol());
        editTextIngles.setText(entradaSeleccionada.getIngles());
        checkBoxEsPalabra.setChecked(entradaSeleccionada.isEsPalabra());

        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);

        Button btnGuardar = popupView.findViewById(R.id.btnGuardar);
        Button btnEliminar = popupView.findViewById(R.id.btnEliminar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevaPalabra = editTextEspanol.getText().toString();
                String nuevaTraduccion = editTextIngles.getText().toString();

                // Actualizar los valores de la entrada
                entradaSeleccionada.setEspanol(nuevaPalabra);
                entradaSeleccionada.setIngles(nuevaTraduccion);
                entradaSeleccionada.setEsPalabra(checkBoxEsPalabra.isChecked());

                // Notificar al adaptador que los datos han cambiado
                adaptadorRecyclerView.notifyDataSetChanged();

                // Cerrar el PopupWindow
                popupWindow.dismiss();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Delete", "Valor de entrada: " + entradaSeleccionada.toString());
                ControladorEntrada.eliminarEntrada(entradaSeleccionada);
                adaptadorRecyclerView.notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });
    }
}