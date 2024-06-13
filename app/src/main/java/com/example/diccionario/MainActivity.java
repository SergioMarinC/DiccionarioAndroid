package com.example.diccionario;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diccionario.Controlador.ControladorEntrada;

public class MainActivity extends AppCompatActivity {

    // Método onCreate se ejecuta cuando la actividad se crea
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Establece el layout de la actividad

        // Inicializa el controlador que manejará las operaciones de base de datos
        ControladorEntrada.init(this);

        // Encuentra el botón para introducir o modificar entradas en el layout
        Button btnIntroducirModificar = findViewById(R.id.btnIntroducirModificar);
        // Configura un listener para el botón
        btnIntroducirModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea una intención para iniciar la actividad IntroducirModificar
                Intent intent = new Intent(MainActivity.this, IntroducirModificar.class);
                // Inicia la actividad IntroducirModificar
                startActivity(intent);
            }
        });

        // Encuentra el botón para consultas en el layout
        Button btnConsultas = findViewById(R.id.btnConsultas);
        // Configura un listener para el botón
        btnConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea una intención para iniciar la actividad Consultas
                Intent intent = new Intent(MainActivity.this, Consultas.class);
                // Inicia la actividad Consultas
                startActivity(intent);
            }
        });

        // Encuentra el botón para realizar tests en el layout
        Button btnTest = findViewById(R.id.btnTest);
        // Configura un listener para el botón
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea una intención para iniciar la actividad Test
                Intent intent = new Intent(MainActivity.this, Test.class);
                // Inicia la actividad Test
                startActivity(intent);
            }
        });
    }
}
