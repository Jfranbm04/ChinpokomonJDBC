package com.example.trabajo01_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Método para ir a la actividad conocerMarca
    public void conocer(View view){
        Intent activity2 = new Intent(this, Activity2.class);
        startActivity(activity2);
    }

    // Método para ir a la actividad crearChinpokomon
    public void crearChinpokomon(View view){
        Intent crearChinpokomon = new Intent(this, crearChinpokomon.class); // Corregido el nombre de la clase
        startActivity(crearChinpokomon);
    }

    // Método para ir a la tienda
    public void tienda(View view){
        Intent tienda = new Intent(this, tiendaChinpokomon.class);
        startActivity(tienda);
    }

    public void coleccion(View view){
        Intent activity2 = new Intent(this, Coleccion.class);
        startActivity(activity2);
    }

}