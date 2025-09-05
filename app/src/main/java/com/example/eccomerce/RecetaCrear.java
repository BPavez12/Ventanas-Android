package com.example.eccomerce;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecetaCrear extends AppCompatActivity {

    private ImageView imageView;
    private Uri photoURI;

    // Lanzador para tomar foto
    private ActivityResultLauncher<Uri> takePictureLauncher;

    // Lanzador para solicitar permiso de cámara
    private ActivityResultLauncher<String> requestCameraPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receta_crear);

        imageView = findViewById(R.id.imageView);
        Button btnCamera = findViewById(R.id.btnCamera);

        // Inicializar lanzador de la cámara
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                result -> {
                    if (result && photoURI != null) {
                        imageView.setImageURI(photoURI);
                    } else {
                        Toast.makeText(this, "No se tomó la foto", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Inicializar lanzador de permiso
        requestCameraPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        openCamera();
                    } else {
                        Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Botón para abrir la cámara
        btnCamera.setOnClickListener(v -> checkCameraPermission());

        // Ajuste de barras de sistema (opcional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Verifica y solicita permiso de cámara en tiempo de ejecución
     */
    private void checkCameraPermission() {
        if (androidx.core.content.ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    /**
     * Abre la cámara usando FileProvider
     */
    private void openCamera() {
        try {
            File photoFile = createImageFile();
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(
                        this,
                        "com.example.eccomerce.provider", // debe coincidir con tu manifest
                        photoFile
                );
                takePictureLauncher.launch(photoURI);
            } else {
                Toast.makeText(this, "No se pudo crear el archivo de imagen", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al crear archivo de imagen", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Crea un archivo temporal único para la foto
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (storageDir == null) {
            Toast.makeText(this, "No se pudo acceder al almacenamiento interno", Toast.LENGTH_SHORT).show();
            return null;
        }

        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }
}
