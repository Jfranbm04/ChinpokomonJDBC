package com.example.trabajo01_multimedia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo01_multimedia.clases.Chinpokomon;
import com.example.trabajo01_multimedia.utilidades.ImagenesBlobBitmap;

public class ViewHolderChinpokomon extends RecyclerView.ViewHolder implements View.OnClickListener{

// alsdghjkasñdk.g agjksjdlñnakscojdgavloik;           // Revisar tambien el fichero viewholderChinpokomon y modificar_chinpokomon
    public static final String EXTRA_DETALLES_PRODUCTO = "com.example.trabajo01_multimedia.viewHolderChinpokomon.detalles_producto" ;
    public static final String EXTRA_DETALLES_IMAGEN_PRODUCTO = "com.example.trabajo01_multimedia.viewHolderChinpokomon.detalles_producto_imagen";

    ImageView imageView;
    TextView codigoView, nombreView, nivelView, tipoView, movimientoView;
    listaChinpoAdapter lca;


    public ViewHolderChinpokomon(@NonNull View itemView, listaChinpoAdapter listaChinpoAdapter) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgChinpokomon);

        codigoView = itemView.findViewById(R.id.txtCodigo);
        nombreView = itemView.findViewById(R.id.txtNombreDatos);
        nivelView = itemView.findViewById(R.id.txtNivel);
        tipoView = itemView.findViewById(R.id.txtTipo);
        movimientoView = itemView.findViewById(R.id.txtMovimiento);

        lca = listaChinpoAdapter;
        itemView.setOnClickListener(this);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    //-------------------------------------------------------------------------------------------
    // Al pulsar en un objeto de la pantalla coleccion lleva a editar o borrar
    @Override
    public void onClick(View view) {
        int posicion = getLayoutPosition();
        Chinpokomon c = lca.getListaChinpokomon().get(posicion);
        Intent intent = new Intent(lca.getContext(), modificar_chinpokomon.class);
        intent.putExtra(EXTRA_DETALLES_PRODUCTO,c);
        imageView.buildDrawingCache();
        Bitmap foto_bm = imageView.getDrawingCache();
        byte[] fotobytes = ImagenesBlobBitmap.bitmap_to_bytes_png(foto_bm);
        intent.putExtra(EXTRA_DETALLES_IMAGEN_PRODUCTO,fotobytes );
        //intent.putExtra(EXTRA_POSICION_CASILLA, posicion);

        lca.getContext().startActivity(intent);
        //  ((MostrarProductosActivity)lpa.getContexto()).finish();

    }




}
