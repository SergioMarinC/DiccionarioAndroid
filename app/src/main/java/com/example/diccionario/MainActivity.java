package com.example.diccionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.diccionario.Controlador.ControladorEntrada;
import com.example.diccionario.DAO.DaoEntrada;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ControladorEntrada.init();

        Button btnIntroducirModificar = findViewById(R.id.btnIntroducirModificar);
        btnIntroducirModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IntroducirModificar.class);
                startActivity(intent);
            }
        });

        Button btnConsultas = findViewById(R.id.btnConsultas);
        btnConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consultas.class);
                startActivity(intent);
            }
        });

        Button btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Test.class);
                startActivity(intent);
            }
        });
    }
}