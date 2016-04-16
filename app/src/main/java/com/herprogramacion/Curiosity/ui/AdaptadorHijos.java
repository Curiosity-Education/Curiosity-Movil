package com.herprogramacion.Curiosity.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.herprogramacion.Curiosity.R;
import com.herprogramacion.Curiosity.modelo.Hijo;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorHijos
        extends RecyclerView.Adapter<AdaptadorHijos.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre_completo;
        public TextView actividad;
        public TextView promedio;
        public ViewHolder(View v) {
            super(v);


            nombre_completo = (TextView) v.findViewById(R.id.texto_nombre_completo);
            actividad = (TextView) v.findViewById(R.id.texto_actividad);
            promedio = (TextView) v.findViewById(R.id.texto_max_promedio);
        }
    }


    public AdaptadorHijos() {
    }

    @Override
    public int getItemCount() {
        return Hijo.hijos.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_hijo, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Hijo item = Hijo.hijos.get(i);
        viewHolder.nombre_completo.setText(item.getNombre_completo());
        viewHolder.promedio.setText(item.getMaxPts());
        viewHolder.actividad.setText(item.getActividad());

    }



}