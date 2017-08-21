package com.br.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.br.database.adapters.LinguagemAdapter;
import com.br.database.dao.DataBaseHelper;
import com.br.database.views.AddActivity;
import com.br.database.views.OnClick;
import com.br.database.views.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {

    private RecyclerView rcvLinguagens;
    private LinguagemAdapter adapter;

    private DataBaseHelper helper;

    private String pesquisa;

    final int ABRIR_TELA_PESQUISA = 123;
    final int ABRIR_TELA_EDIT = 23;

    List<Linguagem> linguagems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvLinguagens = (RecyclerView) findViewById(R.id.rcvLinguagens);

        helper = new DataBaseHelper(this);


        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        rcvLinguagens.setLayoutManager(llm);

        registerForContextMenu(rcvLinguagens);

        pesquisa(pesquisa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                Intent i = new Intent(MainActivity.this, SearchActivity.class);

                i.putExtra("pesquisa", pesquisa == null ? "" : pesquisa);
                startActivityForResult(i, ABRIR_TELA_PESQUISA);
                return true;
            case R.id.action_add:

                Intent i1 = new Intent(MainActivity.this, AddActivity.class);

                startActivityForResult(i1, ABRIR_TELA_EDIT);
                return true;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == ABRIR_TELA_PESQUISA){
                pesquisa = data.getExtras().getString("pesquisa");
                pesquisa(pesquisa);
            }

            if(requestCode == ABRIR_TELA_EDIT){
                if(data.getExtras().containsKey("linguagem")) {
                    Linguagem l = data.getExtras().getParcelable("linguagem");
                    if (l.getId() == 0) {
                        save(l);
                    } else {
                        edit(l);
                    }
                } else if(data.getExtras().containsKey("id")){
                    remover(data.getExtras().getInt("id"));
                }
            }
        }
    }

    private void remover(int id) {
// criando e instanciando o banco de dados
        SQLiteDatabase db = helper.getWritableDatabase();

        String [] args = {id+""};

        db.delete("Linguagem", " _id = ? ", args);

        db.close();

        Toast.makeText(getApplicationContext(), "Removido com sucesso", Toast.LENGTH_LONG).show();
        pesquisa(pesquisa);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_remove){
            Toast.makeText(this, "Removendo", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contex_menu, menu);
    }

    private void edit(Linguagem l){
        SQLiteDatabase db = helper.getWritableDatabase();

        // criando conjunto de valores para o insert
        ContentValues values = new ContentValues();
        values.put("nome", l.getNome());
        values.put("codigo", l.getCodigo());
        values.put("_id", l.getId());

        String [] args = {""+l.getId()} ;

        // gravando
        long idItem = db.update("Linguagem",  values, " _id = ? ", args);

        db.close();

        pesquisa(pesquisa);
    }


    private void save(Linguagem l){
        SQLiteDatabase db = helper.getWritableDatabase();

        // criando conjunto de valores para o insert
        ContentValues values = new ContentValues();
        values.put("nome", l.getNome());
        values.put("codigo", l.getCodigo());

        // gravando
        long idItem = db.insert("Linguagem", null, values);

        db.close();

        pesquisa(pesquisa);
    }

    private void pesquisa(String linguagem) {
        if(linguagem == null){
            linguagem = "";
        }

        // criando e instanciando o banco de dados
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] coluns = {"_id", "nome", "codigo"};
        String[] args = {"%"+linguagem+"%"};

        Cursor cursor = db.query(true, "Linguagem", coluns, " nome like ? ", args, null, null, null, null);

        Linguagem l;
        linguagems = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                l = new Linguagem();
                l.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                l.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                l.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
                linguagems.add(l);
            } while (cursor.moveToNext());
        }

        db.close();

//        adapter.notifyDataSetChanged();

        adapter = new LinguagemAdapter(MainActivity.this, linguagems);
        rcvLinguagens.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        i.putExtra("linguagem", linguagems.get(position));
        startActivityForResult(i, ABRIR_TELA_EDIT);
    }

    @Override
    public void onLonClick(int position) {
        openContextMenu(rcvLinguagens.getChildAt(position));
    }
}
