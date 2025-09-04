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

public class Parrillas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Vinculamos este Activity con su layout XML
        setContentView(R.layout.activity_parrillas);

        // Botón de Atras
        ImageButton btnRetroceso = findViewById(R.id.btnRetrocederAnterior);

        // Botones de platos en el apartado Parrilla
        ImageButton btnAnticucho = findViewById(R.id.btnAnticuchoPolloVerduras);
        ImageButton btnCostillar = findViewById(R.id.btnCostillarCerdo);
        ImageButton btnPollo = findViewById(R.id.btnPolloAsadoLimon);

        // Abrir receta de Anticucho
        btnAnticucho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Parrillas.this, AnticuchoDePolloYVerduras.class);
                startActivity(intent);
            }
        });

        // Abrir receta de Costillar
        btnCostillar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Parrillas.this, CostillarDeCerdoParrilla.class);
                startActivity(intent);
            }
        });

        // Abrir receta de Pollo Asado al Limón
        btnPollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Parrillas.this, PolloAsadoAlLimon.class);
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
