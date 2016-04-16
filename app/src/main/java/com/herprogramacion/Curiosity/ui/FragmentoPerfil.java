package com.herprogramacion.Curiosity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herprogramacion.Curiosity.R;

/**
 * Fragmento para la pestaña "PERFIL" De la sección "Mi Cuenta"
 */
public class FragmentoPerfil extends Fragment {

    public FragmentoPerfil() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentProfile= inflater.inflate(R.layout.fragmento_perfil, container, false);
        Intent intent = getActivity().getIntent();
        TextView user = (TextView)fragmentProfile.findViewById(R.id.texto_user);
        TextView nombre_completo = (TextView)fragmentProfile.findViewById(R.id.texto_nombre);
        TextView email = (TextView)fragmentProfile.findViewById(R.id.texto_email);

        user.setText(intent.getStringExtra("user"));
        nombre_completo.setText(intent.getStringExtra("nombre_completo"));
        email.setText(intent.getStringExtra("email"));

        return fragmentProfile;

    }
}
