package com.example.trabajo01_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabajo01_multimedia.R;
import com.example.trabajo01_multimedia.MainActivity;

public class crearChinpokomon extends AppCompatActivity {

    private Spinner spTipo;
    private ImageView imgDiseño;
    private int imagenActual = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_chinpokomon);


        spTipo = (Spinner) findViewById(R.id.spTipo);   // Conecto objeto de Spinner al spinner del diseño
        // añado los tipos
        String[] tipos = {"Tipo", "Fuego","Planta","Agua", "Lucha", "Pistola", "Bicho", "Zapato", "Acero", "Coche", "Dragón", "Camello", "Mexicano", "Dinosaurio"};

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        spTipo.setAdapter(adapter);

        // Imagen clickable
        imgDiseño = findViewById(R.id.imgDiseño);

        // Boton enviarRestultados
        Button botonResultados = findViewById(R.id.btEnviarResultados);


    }


    // Método para cambiar la imagen
    public void cambiarImagen(View view) {
        // Incrementa el índice de la imagen actual
        imagenActual++;

        // Si alcanzamos la última imagen, volvemos a la primera
        if (imagenActual > 9) {
            imagenActual = 1;
        }

        // Obtener ID de la imagen correspondiente y establecer la nueva imagen
        int idNuevaImagen = getResources().getIdentifier("imagen" + imagenActual, "drawable", getPackageName());
        imgDiseño.setImageResource(idNuevaImagen);
    }


    // Método para enviarResultados
    public void enviarResultados(View view){
        Toast.makeText(getApplicationContext(), "Error 069: Resultados no enviados.", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Tu Chinpokomon es el peor diseñado de la historia.", Toast.LENGTH_SHORT).show();
    }




    // Método para volver al main
    public void volver (View view){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);

    }




}