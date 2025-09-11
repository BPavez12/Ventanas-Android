package com.example.eccomerce;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class recetaMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_recetas);

        Button btnCrearReceta = findViewById(R.id.CrearRecetaUser);

        TextView nombreReceta = findViewById(R.id.usuarioRecetaNombre);
        ImageView imgReceta = findViewById(R.id.imgRecetaUsuario);
        TextView ingredientesReceta = findViewById(R.id.ingredientesRecetaUser);
        TextView preparacionReceta = findViewById(R.id.preparacionRecetaUser);
        ImageButton btnAtras = findViewById(R.id.btnRetrocederOpciones);

        Intent intent = getIntent();
        String RecetaName = intent.getStringExtra("nombre");
        String imgRecetaUser = intent.getStringExtra("imagenUri");
        String ingredientes = intent.getStringExtra("ingredientes");
        String preparacion = intent.getStringExtra("preparacion");

        nombreReceta.setText(RecetaName);
        ingredientesReceta.setText(ingredientes);
        preparacionReceta.setText(preparacion);

        if (imgRecetaUser != null) {
            Uri imagenUri = Uri.parse(imgRecetaUser);
            imgReceta.setImageURI(imagenUri);
        }

        btnCrearReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recetaMenu.this, RecetaCrear.class);
                startActivity(intent);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}