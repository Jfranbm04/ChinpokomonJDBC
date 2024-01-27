package com.example.trabajo01_multimedia;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listaChinpoAdapter extends RecyclerView.Adapter<ViewHolderChinpokomon>{

    Context context;
    List<Chinpokomon> listaChinpokomon;
    private LayoutInflater inflate = null;


    public listaChinpoAdapter(Context context, List<Chinpokomon> listaChinpokomon) {
        this.context = context;
        this.listaChinpokomon = listaChinpokomon;
        inflate =  LayoutInflater.from(this.context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Chinpokomon> getListaChinpokomon() {
        return listaChinpokomon;
    }

    //----------------------------------------------------------------------------------------------


    @NonNull
    @Override
    public ViewHolderChinpokomon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflate.inflate(R.layout.item_chinpokomon,parent,false);
        ViewHolderChinpokomon vhc = new ViewHolderChinpokomon(mItemView,this);
        return vhc;
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolderChinpokomon holder, int position) {
        Chinpokomon c = this.getListaChinpokomon().get(position);

        holder.codigoView.setText(listaChinpokomon.get(position).getCodigo());
        holder.nombreView.setText(listaChinpokomon.get(position).getNombre());
        holder.nivelView.setText(listaChinpokomon.get(position).getNivel());
        holder.tipoView.setText(listaChinpokomon.get(position).getTipo());
        holder.movimientoView.setText(listaChinpokomon.get(position).getMovimiento());
        // holder.imageView.setImageResource(listaChinpokomon.get(position).getImagen());
        String codigo = c.getCodigo();

        cargarImagen(codigo, holder.getImageView(), context);

    }

    //----------------------------------------------------------------------------------------------

    @Override
    public int getItemCount() {
        return listaChinpokomon.size();
    }


    public void setListaChinpokomon(List<Chinpokomon> listaChinpokomon) {
        this.listaChinpokomon = listaChinpokomon;
    }


    //----------------------------------------------------------------------------------------------
    private void cargarImagen(String idProducto, ImageView img_foto, Context contexto) {
        StringRequest request =new StringRequest(Request.Method.POST, ConfiguracionDB.DIRECCION_URL_RAIZ+ "/mostrar_foto.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Jorge tontito", "Response: " + response);

                            JSONObject jsonObject = new JSONObject(response);
                            String exito=jsonObject.getString("exito");
                            JSONArray jsonArray =jsonObject.getJSONArray("chinpokomon_fotos");
                            if (exito.equals("1")){
                                int cuantos = jsonArray.length();
                                if (cuantos > 0) {
                                    JSONObject object = jsonArray.getJSONObject(0);
                                    String codigo = object.getString("codigo");
                                    String imagen = object.getString("imagen");
                                    byte[] fotobyte = ImagenesBlobBitmap.string_to_byte(imagen);
                                    Bitmap fotobitmap = ImagenesBlobBitmap.bytes_to_bitmap(fotobyte, ConfiguracionDB.ancho_imagen,ConfiguracionDB.alto_imagen);
                                    img_foto.setImageBitmap(fotobitmap);
                                }
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
                Log.i("mysql1","error al pedir la foto");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("codigo",idProducto);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        requestQueue.add(request);
    }



}
