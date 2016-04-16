package com.herprogramacion.Curiosity.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.herprogramacion.Curiosity.modelo.Padre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Fragmento para la pestaña "Recordatorio" de la sección "Mi Cuenta"
 */
public class FragmentoRecordatorio extends Fragment implements View.OnClickListener {
    String mensaje;
    String hijo_recuerda;
    View vista;
    Spinner spinner;
    static String[]  nombre_completo = new String[10];
    public FragmentoRecordatorio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragmento_recordatorio, container, false);
        spinner = (Spinner)vista.findViewById(R.id.hijos);
        cargarList(getActivity(),spinner);
        Button btnEnviarRecordatorio = (Button)vista.findViewById(R.id.enviarRecordatorio);


        btnEnviarRecordatorio.setOnClickListener(this);
        return vista;
    }
    public static  void cargarList(final android.support.v4.app.FragmentActivity activity ,final Spinner  spinner){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constantes.URL+"/gethijos",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(activity.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        //openProfile();
                        JSONArray objeto;
                        Hijo hijo = new Hijo();
                        try {

                            objeto = new JSONArray(response);
                            for(int i = 0;i < objeto.length(); i++ ){

                                JSONObject childJSONObject = objeto.getJSONObject(i);
                                nombre_completo[i] = childJSONObject.getString("username");
                                hijo.addListAdapter(childJSONObject.getString("username"));


                            }
                            ArrayAdapter spinner_adapter = new ArrayAdapter(activity, android.R.layout.simple_spinner_item,hijo.getlistaAdapter());
                            spinner.setAdapter(spinner_adapter);


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
                map.put("id",Padre.id+"");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        EditText mensajeTexto = (EditText)vista.findViewById(R.id.mensaje);
        mensaje = mensajeTexto.getText().toString();
        hijo_recuerda = spinner.getSelectedItem().toString();
            enviarMensaje(getActivity());
    }

    public void  enviarMensaje(final android.support.v4.app.FragmentActivity activity){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.URL+"/sendMensaje",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity.getApplicationContext(),"El mensaje se envio a tu hijo", Toast.LENGTH_LONG).show();

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
                /*for (int i=0; i < nombre_completo.length;i++){
                    hijo_recuerda = hijo_recuerda.replace(nombre_completo[i],"");
                }*/
                Map<String, String> map = new HashMap<String, String>();
                map.put("padre_avisa", Padre.id+"");
                map.put("mensaje",mensaje);
                map.put("hijo_recuerda",hijo_recuerda);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }
}
