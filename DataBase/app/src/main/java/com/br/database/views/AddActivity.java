package com.br.database.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.br.database.Linguagem;
import com.br.database.R;

public class AddActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtCodigo;
    private Button btnSalvar;
    private Button btnRemove;
    private TextView lblId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        txtNome =(EditText) findViewById(R.id.txtNome);
        txtCodigo =(EditText) findViewById(R.id.txtCodigo);
        btnSalvar =(Button) findViewById(R.id.btnSalvar);
        btnRemove =(Button) findViewById(R.id.btnRemove);
        lblId =(TextView) findViewById(R.id.lblId);

        Bundle extras = getIntent().getExtras();

        if(extras != null && extras.containsKey("linguagem")){
            Linguagem l = extras.getParcelable("linguagem");
            lblId.setText(String.valueOf(l.getId()));
            txtNome.setText(l.getNome());
            txtCodigo.setText(l.getCodigo());
            btnRemove.setVisibility(View.VISIBLE);
        } else {
            btnRemove.setVisibility(View.GONE);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Salvar
                Linguagem l = new Linguagem();
                l.setCodigo(txtCodigo.getText().toString());
                l.setNome(txtNome.getText().toString());

                l.setId(Long.parseLong(lblId.getText().toString()));

                Intent i = getIntent();
                i.putExtra("linguagem", l);

                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("id", Integer.parseInt(lblId.getText().toString()));


                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

    }
}
