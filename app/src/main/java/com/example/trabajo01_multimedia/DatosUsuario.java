package com.example.trabajo01_multimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabajo01_multimedia.clases.Chinpokomon;
import com.example.trabajo01_multimedia.clases.ConfiguracionDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DatosUsuario extends AppCompatActivity {

    DatabaseReference ref;
    RecyclerView recyclerView;
    List<Chinpokomon> listaChinpokomon;
    listaChinpoAdapter chinpoAdapter;
    TextView txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        recyclerView = findViewById(R.id.recyclerView);
        listaChinpokomon = new ArrayList<Chinpokomon>();

        txtBuscar = findViewById(R.id.txtBuscar);
        /*
        listaChinpokomon.add(new Chinpokomon(4, "Chorizo", 2, "Fantasma", "Cambiar de color"));
        listaChinpokomon.add(new Chinpokomon(5, "Sombrilla", 67, "Dinosaurio", "Gritar flojo"));
        listaChinpokomon.add(new Chinpokomon(6, "Carlos", 25, "Mexicano", "Comer guacamole"));
        listaChinpokomon.add(new Chinpokomon(7, "Zapato", 10, "Fuego", "Andar", R.drawable.imagen7));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new listaChinpoAdapter(getApplicationContext(),listaChinpokomon));


        String codigoBorrar = textBorrar.toString();

         */

        //-----------------------------------------------------------
        chinpoAdapter = new listaChinpoAdapter(this,listaChinpokomon);
        recyclerView.setAdapter(chinpoAdapter);
        mostrarProductos();

        //-------------------------------------------------------------
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            // In portrait
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }


    public void mostrarProductos() {
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ+ "/mostrar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listaChinpokomon.clear();
                        try {

                            Log.d("JSONResponse", "Response: " + response);

                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            Log.d("JSONResponse", "exito: " + exito);

                            JSONArray jsonArray = jsonObject.getJSONArray("listaChinpokomon");

                            if (exito.equals("1")){
                                for (int i=0;i<jsonArray.length();i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    int codigo = Integer.valueOf(object.getString("codigo"));
                                    String nombre = object.getString("nombre");
                                    int nivel = Integer.valueOf(object.getString("nivel"));
                                    String tipo = object.getString("tipo");
                                    String movimiento = object.getString("movimiento");

                                    Chinpokomon c1 = new Chinpokomon(codigo, nombre, nivel, tipo, movimiento);
                                    listaChinpokomon.add(c1);
                                }
                                chinpoAdapter.setListaChinpokomon(listaChinpokomon);
                                chinpoAdapter.notifyDataSetChanged();
                            }
                        }
                        catch (JSONException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(MostrarProductosActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i("mysql1","error al pedir los datos");
            }
        }
        ){


        };
        RequestQueue requestQueue = Volley.newRequestQueue(DatosUsuario.this);
        requestQueue.add(request);
    }







    //---------------------------------------------------------------------------------
    public void buscarProductos(View view) {

        String texto = String.valueOf(txtBuscar.getText());
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ+ "/mostrar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listaChinpokomon.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String exito=jsonObject.getString("exito");
                            JSONArray jsonArray =jsonObject.getJSONArray("listaChinpokomon");

                            if (exito.equals("1")){
                                for (int i=0;i<jsonArray.length();i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    int codigo = Integer.valueOf(object.getString("codigo"));
                                    String nombre = object.getString("nombre");
                                    int nivel = Integer.valueOf(object.getString("nivel"));
                                    String tipo = object.getString("tipo");
                                    String movimiento = object.getString("movimiento");

                                    Chinpokomon c1 = new Chinpokomon(codigo, nombre, nivel, tipo, movimiento);
                                    if(c1.getNombre().contains(texto)) {
                                        listaChinpokomon.add(c1);
                                    }
                                }
                                chinpoAdapter.setListaChinpokomon(listaChinpokomon);
                                chinpoAdapter.notifyDataSetChanged();
                            }
                        }
                        catch (JSONException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DatosUsuario.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i("mysql1","error al pedir los datos");
            }
        }
        ){


        };
        RequestQueue requestQueue = Volley.newRequestQueue(DatosUsuario.this);
        requestQueue.add(request);
    }





    // Método para volver al main
    public void volver (View view){
        Intent anterior = new Intent(this, Coleccion.class);
        startActivity(anterior);

    }

    // Método para añadir chinpokomon
    public void anadir (View view){
        Intent anadir = new Intent(this, anadirChinpokomon.class);
        startActivity(anadir);

    }

    // Métodos para borrar chinpokomom
    public void borrar(View view){
        String codigoBorrar = txtBuscar.toString();
        if(codigoBorrar.isEmpty()){
            Toast.makeText(this, "Escribe un código.", Toast.LENGTH_SHORT).show();
        }else{
            borrarChinpokomon(codigoBorrar);
        }

    }
    public void borrarChinpokomon(String codigo) {
        // Obtiene la referencia al nodo específico que deseas borrar

        DatabaseReference chinpokomonRef = ref.child("Chinpokomones").child(codigo);

        // Elimina el nodo
        chinpokomonRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DatosUsuario.this, "Chinpokomon borrado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DatosUsuario.this, "Error al borrar Chinpokomon", Toast.LENGTH_SHORT).show();
                    Log.e("Firebase", "Error: " + task.getException().getMessage());
                }
            }
        });
    }





}