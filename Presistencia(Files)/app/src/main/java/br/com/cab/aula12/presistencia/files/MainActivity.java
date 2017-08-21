package br.com.cab.aula12.presistencia.files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView txtNome;
	private Button btnSalvar;
	private Button btnCarregar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtNome = (EditText) findViewById(R.id.txtTexto);
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
		btnCarregar = (Button) findViewById(R.id.btnCarregar);

		btnSalvar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = txtNome.getText().toString();
				try {
					FileOutputStream fOut = openFileOutput("arquivo.txt", MODE_WORLD_READABLE);
					OutputStreamWriter osw = new OutputStreamWriter(fOut);

					// escrevendo string no arquivo
					osw.write(str);
					osw.flush();
					osw.close();

					//---display file saved message---
					Toast.makeText(getBaseContext(), "String salva!",Toast.LENGTH_SHORT).show();

				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});

		btnCarregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final int READ_BLOCK_SIZE = 100;
				try {
					FileInputStream fIn = openFileInput("arquivo.txt");
					InputStreamReader isr = new InputStreamReader(fIn);
					char[] inputBuffer = new char[READ_BLOCK_SIZE];
					String s = "";
					int charRead;
					// lê o arquivo, caractere por caractere
					while ((charRead = isr.read(inputBuffer))>0) {
						// converte os caracteres em uma string
						String readString = String.copyValueOf(inputBuffer, 0, charRead);
						s += readString;
						inputBuffer = new char[READ_BLOCK_SIZE];
					}
					// coloca a string carregada no EditText
					txtNome.setText(s);
					Toast.makeText(getBaseContext(), "String carregada!", Toast.LENGTH_SHORT).show();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_externalStorage) {
			Intent intent = new Intent(MainActivity.this , FilesExternalStorageActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
