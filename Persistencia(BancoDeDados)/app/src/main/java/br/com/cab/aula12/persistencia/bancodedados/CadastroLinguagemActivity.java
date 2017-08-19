package br.com.cab.aula12.persistencia.bancodedados;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import br.com.cab.aula12.persistencia.bancodedados.model.Linguagem;

public class CadastroLinguagemActivity extends Activity {

	private EditText txtLinguagem;
	private EditText txtCodigo;
	private TextView lblId;

	private DatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_linguagem);

		lblId = (TextView) findViewById(R.id.lblId);
		txtLinguagem = (EditText) findViewById(R.id.txtLinguagem);
		txtCodigo = (EditText) findViewById(R.id.txtCodigo);

		// prepara acesso ao banco de dados
		helper = new DatabaseHelper(this);

		if(getIntent() != null && getIntent().getExtras() != null) {
			Linguagem l = getIntent().getExtras().getParcelable("linguagem");
			if (l != null) {
				lblId.setText(l.getId() + "");
				txtCodigo.setText(l.getCodigo());
				txtLinguagem.setText(l.getNome());
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_cadastro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
			case R.id.action_save:
				if (lblId.getText().equals("0")) {
					cadastrar();
				} else {
					editar();
				}
		}
		return super.onOptionsItemSelected(item);
	}

	private void editar() {
		String nome, codigo;
		nome = txtLinguagem.getText().toString();
		codigo = txtCodigo.getText().toString();

		// verifica se os dados foram informados
		if (nome.isEmpty()) {
			txtLinguagem.setError("Informe o nome da linguagem");
		}

		if (codigo.isEmpty()) {
			txtCodigo.setError("Informe o código da linguagem");
		}

		if (!nome.isEmpty() && !codigo.isEmpty()) {

			// criando e instanciando o banco de dados
			SQLiteDatabase db = helper.getWritableDatabase();

			// criando conjunto de valores para o insert
			ContentValues values = new ContentValues();
			values.put("nome", nome);
			values.put("codigo", codigo);

			String [] args = {lblId.getText().toString()};

			// gravando
			long idItem = db.update("Linguagem", values, " _id = ? ", args);

			db.close();

			Linguagem linguagem = new Linguagem(idItem, nome, codigo);
			Intent intent = getIntent();
			intent.putExtra("linguagem", linguagem);
			setResult(RESULT_OK, intent);
			finish();
		}
	}

	private void cadastrar() {
		String nome, codigo;
		nome = txtLinguagem.getText().toString();
		codigo = txtCodigo.getText().toString();

		// verifica se os dados foram informados
		if (nome.isEmpty()) {
			txtLinguagem.setError("Informe o nome da linguagem");
		}

		if (codigo.isEmpty()) {
			txtCodigo.setError("Informe o código da linguagem");
		}

		if (!nome.isEmpty() && !codigo.isEmpty()) {

			// criando e instanciando o banco de dados
			SQLiteDatabase db = helper.getWritableDatabase();

			// criando conjunto de valores para o insert
			ContentValues values = new ContentValues();
			values.put("nome", nome);
			values.put("codigo", codigo);

			// gravando
			long idItem = db.insert("Linguagem", null, values);

			db.close();

			Linguagem linguagem = new Linguagem(idItem, nome, codigo);
			Intent intent = getIntent();
			intent.putExtra("linguagem", linguagem);
			setResult(RESULT_OK, intent);
			finish();
		}
	}
}
