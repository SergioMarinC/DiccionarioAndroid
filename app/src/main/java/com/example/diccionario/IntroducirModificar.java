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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diccionario.Adaptador.AdaptadorRecyclerView;
import com.example.diccionario.Controlador.ControladorEntrada;
import com.example.diccionario.Modelo.Entrada;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IntroducirModificar extends AppCompatActivity implements RecyclerViewInterface {

    // Adaptador para el RecyclerView
    private AdaptadorRecyclerView adaptadorRecyclerView;
    // Lista de entradas que se mostrarán en el RecyclerView
    private ArrayList<Entrada> listadoEntradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_modificar);

        // Obtener referencia al RecyclerView y al FloatingActionButton
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fabAgregarEntrada = findViewById(R.id.fabAgregarEntrada);

        // Obtener las entradas desde el controlador
        listadoEntradas = ControladorEntrada.getEntradas();
        // Inicializar el adaptador con la lista de entradas
        adaptadorRecyclerView = new AdaptadorRecyclerView(this, listadoEntradas, this);
        // Asignar el adaptador y el layout manager al RecyclerView
        recyclerView.setAdapter(adaptadorRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar el listener para el FloatingActionButton
        fabAgregarEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar el layout para el PopupWindow
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_editar_entrada, null);

                // Obtener referencias a los elementos del layout del PopupWindow
                EditText editTextEspanol = popupView.findViewById(R.id.editTextEspanol);
                EditText editTextIngles = popupView.findViewById(R.id.editTextIngles);
                CheckBox checkBoxEsPalabra = popupView.findViewById(R.id.checkBoxEsPalabra);

                // Crear y mostrar el PopupWindow
                PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);

                // Configurar el listener para el botón de guardar en el PopupWindow
                Button btnGuardar = popupView.findViewById(R.id.btnGuardar);
                Button btnEliminar = popupView.findViewById(R.id.btnEliminar);
                btnEliminar.setVisibility(View.GONE);  // Ocultar el botón de eliminar ya que no es relevante en este contexto
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear una nueva entrada y agregarla a la base de datos a través del controlador
                        ControladorEntrada.agregarEntrada(new Entrada(editTextEspanol.getText().toString(), editTextIngles.getText().toString(), checkBoxEsPalabra.isChecked()));
                        // Actualizar la lista de entradas
                        listadoEntradas.clear();
                        listadoEntradas.addAll(ControladorEntrada.getEntradas());
                        adaptadorRecyclerView.notifyItemInserted(listadoEntradas.size() - 1);
                        // Cerrar el PopupWindow
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(int posicion) {
        // Obtener la entrada seleccionada desde el controlador
        Entrada entradaSeleccionada = ControladorEntrada.getEntradas().get(posicion);

        // Inflar el layout para el PopupWindow
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_editar_entrada, null);

        // Obtener referencias a los elementos del layout del PopupWindow
        EditText editTextEspanol = popupView.findViewById(R.id.editTextEspanol);
        EditText editTextIngles = popupView.findViewById(R.id.editTextIngles);
        CheckBox checkBoxEsPalabra = popupView.findViewById(R.id.checkBoxEsPalabra);

        // Prellenar los campos del PopupWindow con los datos de la entrada seleccionada
        editTextEspanol.setText(entradaSeleccionada.getEspanol());
        editTextIngles.setText(entradaSeleccionada.getIngles());
        checkBoxEsPalabra.setChecked(entradaSeleccionada.isEsPalabra());

        // Crear y mostrar el PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);

        // Configurar el listener para el botón de guardar en el PopupWindow
        Button btnGuardar = popupView.findViewById(R.id.btnGuardar);
        Button btnEliminar = popupView.findViewById(R.id.btnEliminar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los nuevos valores de los campos del PopupWindow
                String nuevaPalabra = editTextEspanol.getText().toString();
                String nuevaTraduccion = editTextIngles.getText().toString();

                // Actualizar los valores de la entrada seleccionada
                entradaSeleccionada.setEspanol(nuevaPalabra);
                entradaSeleccionada.setIngles(nuevaTraduccion);
                entradaSeleccionada.setEsPalabra(checkBoxEsPalabra.isChecked());
                entradaSeleccionada.setSonido(nuevaPalabra + ".wav");

                // Actualizar la entrada en la base de datos a través del controlador
                ControladorEntrada.actualizarEntrada(entradaSeleccionada);
                // Actualizar la lista de entradas
                listadoEntradas.clear();
                listadoEntradas.addAll(ControladorEntrada.getEntradas());
                adaptadorRecyclerView.notifyItemChanged(posicion);

                // Cerrar el PopupWindow
                popupWindow.dismiss();
            }
        });

        // Configurar el listener para el botón de eliminar en el PopupWindow
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Delete", "Valor de entrada: " + entradaSeleccionada.toString());
                // Eliminar la entrada seleccionada de la base de datos a través del controlador
                ControladorEntrada.eliminarEntrada(entradaSeleccionada);
                // Actualizar la lista de entradas
                listadoEntradas.clear();
                listadoEntradas.addAll(ControladorEntrada.getEntradas());
                adaptadorRecyclerView.notifyItemRemoved(listadoEntradas.size());
                // Cerrar el PopupWindow
                popupWindow.dismiss();
            }
        });
    }
}
