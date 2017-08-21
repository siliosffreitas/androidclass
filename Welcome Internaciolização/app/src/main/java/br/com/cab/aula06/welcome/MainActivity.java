package br.com.cab.aula06.welcome;

import br.com.cab.aula06.welcome.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText txtNome;
	private Button btnEntrar;
	private TextView lblBemVindo;
	private TextView lblNome; 
	
	private String nome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtNome 	= (EditText) findViewById(R.id.txtNome);
		btnEntrar 	= (Button) findViewById(R.id.btnEntrar);
		lblBemVindo = (TextView) findViewById(R.id.lblBemVindo);
		lblNome 	= (TextView) findViewById(R.id.lblNome);
		
		btnEntrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nome = txtNome.getText().toString();
				
				if(nome.isEmpty()){
					txtNome.setError(getResources().getString(R.string.request_name));
				} else {
					lblBemVindo.setVisibility(View.VISIBLE);
					lblNome.setVisibility(View.VISIBLE);
					
					lblNome.setText(nome);
				}
			}
		});
	}
	
//	public void entrar (View view){
//		nome = txtNome.getText().toString();
//		
//		lblBemVindo.setVisibility(View.VISIBLE);
//		lblNome.setVisibility(View.VISIBLE);
//		
//		lblNome.setText(nome);
//	}

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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
