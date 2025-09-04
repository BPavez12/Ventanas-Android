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

public class Tragos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Vinculamos este Activity con su layout XML
        setContentView(R.layout.activity_tragos);

        // Botón de retroceso
        ImageButton btnRetroceso = findViewById(R.id.btnRetrocederAnterior);

        // Botones de cada trago
        ImageButton btnTerremoto = findViewById(R.id.btnTerremoto);
        ImageButton btnMangoSour = findViewById(R.id.btnMangoSour);
        ImageButton btnVinoFrutas = findViewById(R.id.btnVinoFrutas);

        // Abrir receta de Terremoto
        btnTerremoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, Terremoto.class);
                startActivity(intent);
            }
        });

        // Abrir receta de Mango Sour
        btnMangoSour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, MangoSour.class);
                startActivity(intent);
            }
        });

        // Abrir receta de Vino con Frutas
        btnVinoFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, VinoConFrutas.class);
                startActivity(intent);
            }
        });

        // Botón para volver atrás al menú anterior
        btnRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra este Activity
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
