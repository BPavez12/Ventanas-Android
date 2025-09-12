package com.example.eccomerce;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ParrillasTodas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Asocia el layout
        setContentView(R.layout.activity_parrillas_todas);

        // Botón de retroceso
        ImageButton btnRetroceso = findViewById(R.id.btnMenuRetroceder);
        btnRetroceso.setOnClickListener(v -> finish());

        // Mostrar instrucciones al entrar
        Toast.makeText(this, "Deslizar hacia los lados para cambiar recetas", Toast.LENGTH_LONG).show();

        // Ajustar padding para evitar choque con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainParrillas), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
