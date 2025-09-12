package com.example.eccomerce;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class recetaMenu extends AppCompatActivity {

    // Referencias a las vistas
    private TextView nombreReceta;
    private ImageView imgReceta;
    private TextView ingredientesReceta;
    private TextView preparacionReceta;

    // Launcher para abrir RecetaCrear y esperar resultado
    private final ActivityResultLauncher<Intent> recetaCrearLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();

                    String nombre = data.getStringExtra("nombre");
                    String ingredientes = data.getStringExtra("ingredientes");
                    String preparacion = data.getStringExtra("preparacion");
                    String imgRecetaUser = data.getStringExtra("imagenUri");

                    nombreReceta.setText(nombre);
                    ingredientesReceta.setText(ingredientes);
                    preparacionReceta.setText(preparacion);

                    if (imgRecetaUser != null) {
                        Uri imagenUri = Uri.parse(imgRecetaUser);
                        imgReceta.setImageURI(imagenUri);
                    } else {
                        imgReceta.setImageResource(0); // Limpia la imagen si no hay
                    }
                }
            });

    private void loadRecetaGuardada() {
        var prefs = getSharedPreferences("Recetas", MODE_PRIVATE);

        String nombre = prefs.getString("nombre", null);
        String ingredientes = prefs.getString("ingredientes", null);
        String preparacion = prefs.getString("preparacion", null);
        String imagenUri = prefs.getString("imagenUri", null);

        if (nombre != null) {
            TextView nombreReceta = findViewById(R.id.usuarioRecetaNombre);
            TextView ingredientesReceta = findViewById(R.id.ingredientesRecetaUser);
            TextView preparacionReceta = findViewById(R.id.preparacionRecetaUser);
            ImageView imgReceta = findViewById(R.id.imgRecetaUsuario);

            nombreReceta.setText(nombre);
            ingredientesReceta.setText(ingredientes);
            preparacionReceta.setText(preparacion);

            if (imagenUri != null) {
                imgReceta.setImageURI(Uri.parse(imagenUri));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_recetas);

        Button btnCrearReceta = findViewById(R.id.CrearRecetaUser);
        nombreReceta = findViewById(R.id.usuarioRecetaNombre);
        imgReceta = findViewById(R.id.imgRecetaUsuario);
        ingredientesReceta = findViewById(R.id.ingredientesRecetaUser);
        preparacionReceta = findViewById(R.id.preparacionRecetaUser);
        ImageButton btnAtras = findViewById(R.id.btnRetrocederOpciones);

        // Abrir RecetaCrear esperando resultado
        btnCrearReceta.setOnClickListener(v -> {
            Intent intent = new Intent(recetaMenu.this, RecetaCrear.class);
            recetaCrearLauncher.launch(intent);
        });

        // Botón atrás
        btnAtras.setOnClickListener(v -> finish());

        loadRecetaGuardada();

        // Ajuste de barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
