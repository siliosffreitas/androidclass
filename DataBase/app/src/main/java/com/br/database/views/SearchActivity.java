package com.br.database.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.br.database.R;

public class SearchActivity extends AppCompatActivity {

    private EditText txtPesquisa;
    private Button btnPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        txtPesquisa = (EditText) findViewById(R.id.txtPesquisa);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        Bundle extras = getIntent().getExtras();
        txtPesquisa.setText(extras.getString("pesquisa"));


        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("pesquisa", txtPesquisa.getText().toString());
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}
