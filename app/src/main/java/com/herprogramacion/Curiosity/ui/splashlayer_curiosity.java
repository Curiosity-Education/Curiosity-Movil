package com.herprogramacion.Curiosity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.herprogramacion.Curiosity.R;
/**
 * Created by Gerson on 04/04/16.
 */
public class splashlayer_curiosity extends  AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashlayer_curiosity);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent iniciar = new Intent(getApplicationContext(), Login_curiosity.class);
                        startActivity(iniciar);
                        finish();
                    }
                },
                1500);
    }
}
