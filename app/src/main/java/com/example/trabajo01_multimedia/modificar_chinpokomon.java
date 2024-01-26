package com.example.trabajo01_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.example.trabajo01_multimedia.clases.ConfiguracionDB;
import com.example.trabajo01_multimedia.utilidades.ImagenesBlobBitmap;

import java.util.HashMap;
import java.util.Map;

public class modificar_chinpokomon extends AppCompatActivity {

    private Chinpokomon c;
    anadirChinpokomon ac = new anadirChinpokomon();
    EditText codigoView, nombreView, nivelView, tipoView, movimientoView;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_chinpokomon);

        //----------------------------------------------------------------
        codigoView = findViewById(R.id.txtCod5);
        nombreView = findViewById(R.id.txtNom5);
        nivelView = findViewById(R.id.txtNvl5);
        tipoView = findViewById(R.id.txtType5);
        movimientoView = findViewById(R.id.txtMov5);
        imagen = findViewById(R.id.imageAñadir5);

        codigoView = (EditText) findViewById(R.id.txtCod5);
        nombreView = (EditText) findViewById(R.id.txtNom5);
        nivelView = (EditText) findViewById(R.id.txtNvl5);
        tipoView = (EditText) findViewById(R.id.txtType5);
        movimientoView = (EditText) findViewById(R.id.txtMov5);
        imagen = (ImageView) findViewById(R.id.imageAñadir5);
        //----------------------------------------------------------------
        Intent intent = getIntent();
        if(intent != null)
        {
            c = (Chinpokomon) intent.getSerializableExtra(ViewHolderChinpokomon.EXTRA_DETALLES_PRODUCTO);
            //---------------------- cargo la foto  ----------------------------------------
            byte[] fotobinaria = (byte[]) intent.getByteArrayExtra(ViewHolderChinpokomon.EXTRA_DETALLES_IMAGEN_PRODUCTO);
            Bitmap fotobitmap = ImagenesBlobBitmap.bytes_to_bitmap(fotobinaria, ConfiguracionDB.ancho_imagen,ConfiguracionDB.alto_imagen);
            imagen.setImageBitmap(fotobitmap);
        }
        else{
            c = new Chinpokomon();
        }
        //-----------------------------------------------------------------
        codigoView.setText(c.getCodigo());
        nombreView.setText(c.getNombre());
        nivelView.setText(String.valueOf(c.getNivel()));
        tipoView.setText(String.valueOf(c.getTipo()));
        movimientoView.setText(String.valueOf(c.getMovimiento()));


    }


    //----------------------------------------------------
    public void borrar_producto(View view)
    {
        borrar_fotodb(c.getCodigo());                                                               // Estan los dos vacíos, hay que pasarle un objeto de fuera (on create)
        borrar_productodb(c.getCodigo());
    }

    private void borrar_productodb(String codigo) {
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/eliminar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("datos eliminados")) {
                            Toast.makeText(modificar_chinpokomon.this, "datos eliminados", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(modificar_chinpokomon.this, "Error no se puede eliminar el dato", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(modificar_chinpokomon.this,String.valueOf(error.getMessage()),Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("codigo",codigo);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(modificar_chinpokomon.this);
        requestQueue.add(request);
    }

    //----------------------------------------------------------------------------
    private void borrar_fotodb(String codigo) {
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/eliminar_foto.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("datos eliminados")) {
                            Toast.makeText(modificar_chinpokomon.this, "datos eliminados", Toast.LENGTH_SHORT).show();
                            //  startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            //  finish();
                        } else {
                            Toast.makeText(modificar_chinpokomon.this, "Error no se puede eliminar el dato", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(modificar_chinpokomon.this,String.valueOf(error.getMessage()),Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("codigo",codigo);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(modificar_chinpokomon.this);
        requestQueue.add(request);
    }


    //---------------------------------------------------
    public void editar_producto(View view)
    {
        int codigo = Integer.valueOf(String.valueOf(codigoView.getText()));
        String nombre = String.valueOf(nombreView.getText());
        int nivel = Integer.valueOf(String.valueOf(nivelView.getText()));
        String tipo = String.valueOf(String.valueOf(tipoView.getText()));
        String movimiento = String.valueOf(movimientoView.getText());

        Chinpokomon c1 = new Chinpokomon(codigo,nombre,nivel,tipo, movimiento);

        if(imagen != null) {
            borrar_fotodb(c.getCodigo());
            ac.insertarFotodb(c.getCodigo(),imagen);

        }
        editar_productodb(c1);

    }

    //--------------------------------------------------------------------------

    private void editar_productodb(Chinpokomon c1) {
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/actualizar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("datos actualizados")) {
                            Toast.makeText(modificar_chinpokomon.this, "actualizado correctamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(modificar_chinpokomon.this, "Error no se puede actualizar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(modificar_chinpokomon.this,String.valueOf(error.getMessage()),Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(modificar_chinpokomon.this);
        requestQueue.add(request);
    }
    private void editar_fotodb(Chinpokomon c1, ImageView img_detalles_imagenp) {
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/actualizar_foto.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("datos actualizados")) {
                            Toast.makeText(modificar_chinpokomon.this, "actualizado correctamente", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            //finish();
                        } else {
                            Toast.makeText(modificar_chinpokomon.this, "Error no se puede actualizar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(modificar_chinpokomon.this,String.valueOf(error.getMessage()),Toast.LENGTH_SHORT).show();
            }
        }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("codigo",c1.getCodigo());
                img_detalles_imagenp.buildDrawingCache();
                Bitmap foto_bm = img_detalles_imagenp.getDrawingCache();
                byte[] fotobytes = ImagenesBlobBitmap.bitmap_to_bytes_png(foto_bm);
                String fotostring = ImagenesBlobBitmap.byte_to_string(fotobytes);
                params.put("imagen",fotostring);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(modificar_chinpokomon.this);
        requestQueue.add(request);
    }
    //--------------------------------------------------------------------------



}