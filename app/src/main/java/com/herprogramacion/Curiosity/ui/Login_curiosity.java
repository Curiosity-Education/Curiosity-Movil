package com.herprogramacion.Curiosity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.herprogramacion.Curiosity.R;
import com.herprogramacion.Curiosity.modelo.Padre;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gerson on 04/04/16.
 */
public class Login_curiosity extends  AppCompatActivity implements View.OnClickListener {
    private static final String LOGIN_URL = Constantes.Login;

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";


    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button login, salir;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_curiosity);
        //--Asignaremos a las variables entrar y salir lo botones en el login para manipularlo

        login = (Button) findViewById(R.id.entrar);
        salir = (Button) findViewById(R.id.salir);

        //---Le establecemos su escuchador a cada Button
        login.setOnClickListener(this);
        salir.setOnClickListener(this);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.entrar:
                userLogin();
                break;
            case R.id.salir:
                setResult(RESULT_OK);
                finish();
                break;

        }
    }


    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                        JSONObject objeto;
                        try {

                            objeto = new JSONObject(response);
                            int padre_id = Integer.parseInt(objeto.optString("padre_id"));
                            int estado = Integer.parseInt(objeto.optString("estado"));
                            String username = objeto.optString("username");
                            String email = objeto.optString("email");
                            String nombre_completo = objeto.optString("nombre_completo");

                            Toast.makeText(getApplicationContext(), objeto.optString("message"), Toast.LENGTH_LONG).show();
                            if (estado == 200) {
                                editTextUsername.setText("");
                                editTextPassword.setText("");
                                Intent intent = new Intent(getApplicationContext(), ActividadPrincipal.class);
                                Padre.id = padre_id;
                                intent.putExtra("user", username);
                                intent.putExtra("nombre_completo", nombre_completo);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                            System.out.print(e);

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("abd", "Error: " + error
                                + ">>" + error.networkResponse.statusCode
                                + ">>" + error.networkResponse.data
                                + ">>" + error.getCause()
                                + ">>" + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}