package com.example.trabajo01_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabajo01_multimedia.MainActivity;
import com.example.trabajo01_multimedia.R;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    // Método para volver al main
    public void volver (View view){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);

    }
}