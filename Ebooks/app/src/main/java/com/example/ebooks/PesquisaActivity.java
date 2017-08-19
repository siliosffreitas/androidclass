package com.example.ebooks;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PesquisaActivity extends AppCompatActivity {

    private EditText txtPesquisar;
    private Button btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        txtPesquisar = (EditText) findViewById(R.id.txtPesquisar);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey("livro")){
            txtPesquisar.setText(extras.getString("livro"));
        }

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("livro", txtPesquisar.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
