package com.example.trabajo01_multimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trabajo01_multimedia.clases.ConfiguracionDB;

import java.util.HashMap;
import java.util.Map;

public class Coleccion extends AppCompatActivity {

    public static final String EMAIL_KEY = "com.example.trabajo01_multimedia.coleccion.email" ;
    public static final String PASSWORD_KEY = "com.example.trabajo01_multimedia.coleccion.password" ;
    public static final String SHARED_PREFS ="com.example.trabajo01_multimedia.coleccion.shared_prefs" ; ;
    // FirebaseDatabase database;
    EditText edt_login_email;
    EditText edt_login_password;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coleccion);
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);
        //------------------------------------
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);


    }

    public void entrar(View view)
    {
        String email = String.valueOf(edt_login_email.getText());
        String password = String.valueOf(edt_login_password.getText());
        //-----------------------------------
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/loguear_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("autenticacion ok")) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(EMAIL_KEY, email);
                            editor.putString(PASSWORD_KEY, password);
                            editor.apply();

                            Toast.makeText(Coleccion.this, "se ha logueado correctamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DatosUsuario.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "error en la autenticacion", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),String.valueOf(error.getMessage()),Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Coleccion.this);
        requestQueue.add(request);
    }

    public void registrarse(View view)
    {
        String email = String.valueOf(edt_login_email.getText());
        String password = String.valueOf(edt_login_password.getText());
        //-----------------------------------

        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ + "/registrar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("registro ok")) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(EMAIL_KEY, email);
                            editor.putString(PASSWORD_KEY, password);
                            editor.apply();

                            Toast.makeText(getApplicationContext(), "se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DatosUsuario.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),String.valueOf(error.getMessage()),Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Coleccion.this);
        requestQueue.add(request);
    }
    // Método para volver al main
    public void volver (View view){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);

    }


}