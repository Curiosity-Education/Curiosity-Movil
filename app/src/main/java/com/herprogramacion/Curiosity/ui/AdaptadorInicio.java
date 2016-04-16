package com.herprogramacion.Curiosity.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herprogramacion.Curiosity.R;
import com.herprogramacion.Curiosity.modelo.Momento;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView desc;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            desc = (TextView) v.findViewById(R.id.descripcion_ini);
            imagen = (ImageView) v.findViewById(R.id.imagen_novedad);
        }
    }

    public AdaptadorInicio() {
    }

    @Override
    public int getItemCount() {
        return Momento.moments.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Momento item = Momento.moments.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getImagen())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.desc.setText(item.getDescripcion());

    }


}