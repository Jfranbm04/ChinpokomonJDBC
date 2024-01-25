package com.example.trabajo01_multimedia;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderChinpokomon extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView codigoView, nombreView, nivelView, tipoView, movimientoView;

    public ViewHolderChinpokomon(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgChinpokomon);

        codigoView = itemView.findViewById(R.id.txtCodigo);
        nombreView = itemView.findViewById(R.id.txtNombreDatos);
        nivelView = itemView.findViewById(R.id.txtNivel);
        tipoView = itemView.findViewById(R.id.txtTipo);
        movimientoView = itemView.findViewById(R.id.txtMovimiento);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
