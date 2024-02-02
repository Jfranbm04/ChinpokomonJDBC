package com.example.trabajo01_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabajo01_multimedia.clases.Chinpokomon;

import Controladores.ChinpokomonController;
import Modelo.ConfiguracionDB;
import com.example.trabajo01_multimedia.utilidades.ImagenesBlobBitmap;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class anadirChinpokomon extends AppCompatActivity {

    public static final int NUEVA_IMAGEN = 1;
    TextView codigoView, nombreView, nivelView, tipoView, movimientoView;
    ImageView imageAñadirView;
    String codigo, nombre, nivel, tipo, movimiento;
    DatabaseReference mRootReference;
    Uri imagen_seleccionada = null;
    // ---------------------------------------------------------------------------------
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    // ---------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_chinpokomon);

        codigoView = findViewById(R.id.txtCod);
        nombreView = findViewById(R.id.txtNom);
        nivelView = findViewById(R.id.txtNvl);
        tipoView = findViewById(R.id.txtType);
        movimientoView = findViewById(R.id.txtMov);
        imageAñadirView = findViewById(R.id.imageAñadir);
        mRootReference = FirebaseDatabase.getInstance().getReference("Coleccion");

    }


    public void insertarProducto_foto(View view)
    {
        try {
            int id = Integer.valueOf(String.valueOf(codigoView.getText()).trim());
            String nombre = String.valueOf(nombreView.getText()).trim();
            int nivel = Integer.valueOf(String.valueOf(nivelView.getText()).trim());
            String tipo = String.valueOf(tipoView.getText()).trim();
            String movimiento = String.valueOf(movimientoView.getText()).trim();

            //------------------------------------------------------------------------
            Chinpokomon c1 = new Chinpokomon(id, nombre, nivel, tipo, movimiento);
            boolean insercionOK = ChinpokomonController.insertarChinpo(c1);
            //------------------------------------------------------------------------


            //---------------------- codigo para añadir la foto al MYSQL ------------------------------
            /*
            Toast.makeText(this, "Codigo del chinpoko: "+c1.getCodigo(), Toast.LENGTH_SHORT).show();
            if (imagen_seleccionada != null) {
                insertarFotodb(String.valueOf(c1.getCodigo()), imageAñadirView);
                // insertarFotodb(String.valueOf(c1.getCodigo()), imageAñadirView);
                // insertarFotodb(id, imageAñadirView);
            }


            */
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    /*
    public void insertarFotodb(String idProducto, ImageView imgNuevopFoto) {
        try{
            StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/insertar_foto.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("foto insertada")) {
                                Toast.makeText(anadirChinpokomon.this, "imagen registrada", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), DatosUsuario.class));
                                finish();
                            } else {
                                Toast.makeText(anadirChinpokomon.this, "Error no se subir la foto", Toast.LENGTH_SHORT).show();
                                Log.i("AAAAAA", "Response: " + response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(anadirChinpokomon.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            ){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params=new HashMap<>();
                    params.put("codigo",idProducto);
                    imgNuevopFoto.buildDrawingCache();
                    Bitmap foto_bm = imgNuevopFoto.getDrawingCache();
                    byte[] fotobytes = ImagenesBlobBitmap.bitmap_to_bytes_png(foto_bm);
                    String fotostring = ImagenesBlobBitmap.byte_to_string(fotobytes);
                    params.put("imagen",fotostring);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(anadirChinpokomon.this);
            requestQueue.add(request);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    */



    /*
    //-----------------------------------------------------------------------------------------------------------------

    public void insertarProductodb(Chinpokomon c1) {
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/insertar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("datos insertados")) {
                            Toast.makeText(anadirChinpokomon.this, "registrado correctamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DatosUsuario.class));
                            finish();
                        } else {
                            Toast.makeText(anadirChinpokomon.this, "Error no se puede registrar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(anadirChinpokomon.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("codigo",String.valueOf(c1.getCodigo()));
                params.put("nombre",c1.getNombre());
                params.put("nivel",String.valueOf(c1.getNivel()));
                params.put("tipo",String.valueOf(c1.getTipo()));
                params.put("movimiento",String.valueOf(c1.getMovimiento()));

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(anadirChinpokomon.this);
        requestQueue.add(request);
    }



    //-----------------------------------------------------------------------------------------------------------------

    public void insertarFotodb(String idProducto, ImageView imgNuevopFoto) {
        try{
            StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/insertar_foto.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("foto insertada")) {
                                Toast.makeText(anadirChinpokomon.this, "imagen registrada", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), DatosUsuario.class));
                                finish();
                            } else {
                                Toast.makeText(anadirChinpokomon.this, "Error no se subir la foto", Toast.LENGTH_SHORT).show();
                                Log.i("AAAAAA", "Response: " + response);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(anadirChinpokomon.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            ){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params=new HashMap<>();
                    params.put("codigo",idProducto);
                    imgNuevopFoto.buildDrawingCache();
                    Bitmap foto_bm = imgNuevopFoto.getDrawingCache();
                    byte[] fotobytes = ImagenesBlobBitmap.bitmap_to_bytes_png(foto_bm);
                    String fotostring = ImagenesBlobBitmap.byte_to_string(fotobytes);
                    params.put("imagen",fotostring);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(anadirChinpokomon.this);
            requestQueue.add(request);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    */


    //--------------------------------------------------------------------------
    //--------CODIGO PARA CAMBIAR LA IMAGEN----------------
    public void cambiar_imagen(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecciona una imagen");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, NUEVA_IMAGEN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUEVA_IMAGEN && resultCode == Activity.RESULT_OK) {
            imagen_seleccionada = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagen_seleccionada);
                imageAñadirView.setImageBitmap(bitmap);

                //---------------------------------------------

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





//-------------------------------------------------------------------------------------

/*
    public void añadir(View view){
        codigo = codigoView.getText().toString();
        nombre = nombreView.getText().toString();
        nivel = nivelView.getText().toString();
        tipo = tipoView.getText().toString();
        movimiento = movimientoView.getText().toString();


        if(codigo.isEmpty() || nombre.isEmpty() || nivel.isEmpty() || tipo.isEmpty() || movimiento.isEmpty()){
            Toast.makeText(this, "Añade todos los datos.", Toast.LENGTH_SHORT).show();
        }else{
            DatabaseReference usersRef = ref.child("Coleccion");
            Map<String, Object> users = new HashMap<>();

            users.put("Chinpo 1", new Chinpokomon(codigo, nombre, nivel, tipo, movimiento));
            // users.put("Chinpo 1", new Chinpokomon(codigo, nombre, nivel, tipo, movimiento, R.drawable.imagen1));
            usersRef.setValue(users);

            // postChinpokomon(codigo, nombre, nivel, tipo, movimiento);
            Toast.makeText(this, "1 Chinpokomon añadido a la colección.", Toast.LENGTH_SHORT).show();
            finish();
        }



    }

    public void modificar(View view){
        codigo = codigoView.getText().toString();
        nombre = nombreView.getText().toString();
        nivel = nivelView.getText().toString();
        tipo = tipoView.getText().toString();
        movimiento = movimientoView.getText().toString();

        if(codigo.isEmpty() || nombre.isEmpty() || nivel.isEmpty() || tipo.isEmpty() || movimiento.isEmpty()){
            Toast.makeText(this, "Añade todos los datos.", Toast.LENGTH_SHORT).show();
        }else{
            DatabaseReference usersRef = ref.child("Coleccion");
            Map<String, Object> users = new HashMap<>();

            users.put("Chinpo 1", new Chinpokomon(codigo, nombre, nivel, tipo, movimiento));
            usersRef.child("Coleccion").updateChildren(users);
            Toast.makeText(this, "1 Chinpokomon actualizado.", Toast.LENGTH_SHORT).show();
            finish();


        }
    }
*/








    // Método para volver a la colección
    public void volver (View view){
        Intent anterior = new Intent(this, DatosUsuario.class);
        startActivity(anterior);

}


}