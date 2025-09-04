package com.example.eccomerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Empanadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Vinculamos este Activity con su layout XML
        setContentView(R.layout.activity_empanadas);

        // Botón de retroceso
        ImageButton btnRetroceso = findViewById(R.id.btnRetrocederAnterior);

        // Botones de cada receta de empanada
        ImageButton btnEmpanadaQueso = findViewById(R.id.btnEmpanadaQueso);
        ImageButton btnEmpanadaPino = findViewById(R.id.btnEmpanadaPino);
        ImageButton btnEmpanadaMarisco = findViewById(R.id.btnEmpanadaMarisco);

        // Abrir receta de Empanada de Queso
        btnEmpanadaQueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Empanadas.this, QuesoEmpanada.class);
                startActivity(intent);
            }
        });

        // Abrir receta de Empanada de Pino
        btnEmpanadaPino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Empanadas.this, PinoEmpanada.class);
                startActivity(intent);
            }
        });

        // Abrir receta de Empanada de Marisco
        btnEmpanadaMarisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Empanadas.this, MariscoEmpanada.class);
                startActivity(intent);
            }
        });

        // Botón para volver atrás al menú anterior
        btnRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra este Activity y regresa al anterior
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
