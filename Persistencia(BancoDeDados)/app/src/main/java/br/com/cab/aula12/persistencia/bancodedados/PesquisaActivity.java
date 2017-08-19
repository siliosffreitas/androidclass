package br.com.cab.aula12.persistencia.bancodedados;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by silio on 17/08/17.
 */

public class PesquisaActivity extends Activity {

    private EditText txtPesquisar;
    private Button btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        txtPesquisar = (EditText) findViewById(R.id.txtPesquisar);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisar);

        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey("linguagem")){
            txtPesquisar.setText(extras.getString("linguagem"));
        }

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("linguagem", txtPesquisar.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
