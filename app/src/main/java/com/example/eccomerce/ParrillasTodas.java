package com.example.eccomerce;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ParrillasTodas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita Edge-to-Edge para que el layout ocupe toda la pantalla
        EdgeToEdge.enable(this);

        // Asociar el layout
        setContentView(R.layout.activity_parrillas_todas);

        // Ajusta el padding de la view raíz para que no se solape con las barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainParrillas), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
