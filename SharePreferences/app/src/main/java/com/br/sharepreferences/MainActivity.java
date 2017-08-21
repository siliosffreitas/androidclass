package com.br.sharepreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Switch swtLigado;
    private EditText txtNome;
    private Button btnSave;

    private SharedPreferences prefs;
    private final String PREF_NAME = "MyPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        swtLigado = (Switch) findViewById(R.id.swtLigado);
        txtNome = (EditText) findViewById(R.id.txtNome);
        btnSave = (Button) findViewById(R.id.btnSave);

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
                Toast.makeText(MainActivity.this, "Dados salvos", Toast.LENGTH_SHORT).show();
            }
        });


        // pegando valores salvos no prefs
        int progress = prefs.getInt("progresso", 23);
        seekBar.setProgress(progress);

        boolean ligado = prefs.getBoolean("ligado", true);
        swtLigado.setChecked(ligado);

        String texto = prefs.getString("texto", "");
        txtNome.setText(texto);

        seekBar.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override public void onGlobalLayout() {
                        saveData();
                    }
                });

        swtLigado.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override public void onGlobalLayout() {
                        saveData();
                    }
                });

    }

    private void saveData(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("progresso", seekBar.getProgress());
        editor.putBoolean("ligado", swtLigado.isChecked());
        editor.putString("texto", txtNome.getText().toString());
        editor.commit();
    }
}
