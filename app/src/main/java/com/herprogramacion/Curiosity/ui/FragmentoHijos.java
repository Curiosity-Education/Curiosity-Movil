package com.herprogramacion.Curiosity.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.herprogramacion.Curiosity.R;
import com.herprogramacion.Curiosity.modelo.Hijo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Fragmento para la pestaña "DIRECCIONES" De la sección "Mi Cuenta"
 */
public class FragmentoHijos extends Fragment {

    boolean cargar=true;
    String id;
    private LinearLayoutManager linearLayout;

    public FragmentoHijos() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(cargar){
            cargarList(getActivity());
            cargar=false;
        }
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        RecyclerView reciclador = (RecyclerView)view.findViewById(R.id.reciclador);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);
        id="37";
        AdaptadorHijos adaptador = new AdaptadorHijos();
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));



        return view;
    }

    public static  void cargarList(final android.support.v4.app.FragmentActivity activity){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constantes.URL+"/gethijos",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(activity.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        //openProfile();
                        JSONArray objeto;
                        try {
                            objeto = new JSONArray(response);
                            for(int i = 0;i < objeto.length(); i++ ){
                                JSONObject childJSONObject = objeto.getJSONObject(i);
                                Hijo hijo = new Hijo(childJSONObject.getString("nombre_completo"), "Mayor promedio : " + childJSONObject.getString("max_promedio"),"Actividad: "+childJSONObject.getString("actividad"));
                                hijo.addToList();

                            }

                        } catch (JSONException e) {
                            Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity.getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id","37");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }


}
