package br.com.cab.aula12.persistencia.bancodedados;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.cab.aula12.persistencia.bancodedados.adapter.AdapterListViewLinguagem;
import br.com.cab.aula12.persistencia.bancodedados.model.Linguagem;

public class ListViewCustomActivity extends Activity {
	
	private ListView lstLinguagens;
	
	private ArrayAdapter<Linguagem> adapter;
	private List<Linguagem> lista;

	private DatabaseHelper helper;

	private String pesquisa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_customizado);
		
		lstLinguagens = (ListView) findViewById(R.id.lstLinguagens);
		
//		lista = preencheListaLinguagens();
		

		// prepara acesso ao banco de dados
		helper = new DatabaseHelper(this);

		buscarLinguagem(pesquisa);
		
		registerForContextMenu(lstLinguagens);

		lstLinguagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(ListViewCustomActivity.this, CadastroLinguagemActivity.class);
				intent.putExtra("linguagem", lista.get(position));
				startActivityForResult(intent, 1);
			}
		});
	}

	private void buscarLinguagem(String linguagem) {
		if(linguagem == null){
			linguagem = "";
		}

		// criando e instanciando o banco de dados
		SQLiteDatabase db = helper.getWritableDatabase();

		String[] coluns = {"_id", "nome", "codigo"};
		String[] args = {"%"+linguagem+"%"};

		Cursor cursor = db.query(true, "Linguagem", coluns, " nome like ? ", args, null, null, null, null);

		Linguagem l;
		lista = new ArrayList<>();
		if (cursor.moveToFirst()) {
			do {
				l = new Linguagem();
				l.setId(cursor.getLong(cursor.getColumnIndex("_id")));
				l.setNome(cursor.getString(cursor.getColumnIndex("nome")));
				l.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
				lista.add(l);
			} while (cursor.moveToNext());
		}

		db.close();

		adapter = new AdapterListViewLinguagem(ListViewCustomActivity.this, lista);
		lstLinguagens.setAdapter(adapter);
	}

	private void remover(Linguagem l){
		// criando e instanciando o banco de dados
		SQLiteDatabase db = helper.getWritableDatabase();

		String [] args = {l.getId()+""};

		db.delete("Linguagem", " _id = ? ", args);

		db.close();

		Toast.makeText(getApplicationContext(), "Removido com sucesso", Toast.LENGTH_LONG).show();
		buscarLinguagem(pesquisa);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_context, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
//	        case R.id.action_duplique:
//	        	Toast.makeText(getApplicationContext(), "Duplicado com sucesso", Toast.LENGTH_LONG).show();
//	        	adapter.insert(lista.get((int) info.id), (int)info.id);
//	            return true;
	        case R.id.action_delete:

//	        	adapter.remove(lista.get((int) info.id));
				remover(lista.get((int) info.id));
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id){
		case R.id.action_add:
			Intent intent = new Intent(ListViewCustomActivity.this, CadastroLinguagemActivity.class);
			startActivityForResult(intent, 1);
			return true;

			case R.id.action_search:
				Intent i = new Intent(ListViewCustomActivity.this, PesquisaActivity.class);
				i.putExtra("linguagem", pesquisa);
				startActivityForResult(i, 2);
				return true;

		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1 && resultCode == RESULT_OK){
			buscarLinguagem(pesquisa);
		}

		if(requestCode == 2 && resultCode == RESULT_OK) {
			pesquisa = data.getExtras().getString("linguagem");
			buscarLinguagem(pesquisa);
		}
	}

}
