package com.example.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView lblNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        lblNome = (TextView) findViewById(R.id.lblNome);

        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("nome");

        lblNome.setText(nome);
    }

}
