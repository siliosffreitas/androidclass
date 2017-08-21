package br.com.cab.aula12.presistencia.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FilesExternalStorageActivity extends Activity {

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
					// armazenamento no cartão de memória
                    File sdCard = Environment.getExternalStorageDirectory();
                    File directory = new File (sdCard.getAbsolutePath() +"/MyFiles");
                    if(!directory.exists()){
                    	directory.mkdirs();
                    }
                    File file = new File(directory, "textfile.txt");
                    FileOutputStream fOut = new FileOutputStream(file);
                    
					OutputStreamWriter osw = new OutputStreamWriter(fOut);

					// escrevendo string no arquivo
					osw.write(str);
					osw.flush();
					osw.close();

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
					File sdCard = Environment.getExternalStorageDirectory();
                    File directory = new File (sdCard.getAbsolutePath() + "/MyFiles");
                    File file = new File(directory, "textfile.txt");
                    FileInputStream fIn = new FileInputStream(file);
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

}
