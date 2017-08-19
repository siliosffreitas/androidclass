package com.example.ebooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvBooks;
    private ProgressBar progressBar;
    private BookAdapter adapter;

    private String pesquisa;

    private Retrofit retrofit;
    private BookService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        rcvBooks = (RecyclerView) findViewById(R.id.rcvBooks);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        rcvBooks.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvBooks.setLayoutManager(llm);



        retrofit = new Retrofit.Builder()
                .baseUrl(BookService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BookService.class);


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
            case R.id.action_search:
                Intent i = new Intent(MainActivity.this, PesquisaActivity.class);
                i.putExtra("livro", pesquisa);
                startActivityForResult(i, 2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private boolean startLoader(){
        progressBar.setVisibility(View.VISIBLE);
        return true;
    }

    private void stopLoader(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK) {
            pesquisa = data.getExtras().getString("livro");
            setTitle("Ebooks sobre "+pesquisa);
            buscarLivros(pesquisa);
        }
    }


    private void buscarLivros( String busca) {

        Call<ResponseBook> requestCountBusOn = service.searchBooks(busca);
        requestCountBusOn.enqueue(new Callback<ResponseBook>() {

            boolean start = startLoader();

            @Override
            public void onResponse(Call<ResponseBook> call, Response<ResponseBook> response) {
                if (response.isSuccessful()) {

                    Log.i("onResponse.ok", "code "+response.message());
                    List<Book> books = response.body().getBooks();
                    adapter = new BookAdapter(books);
                    rcvBooks.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Algum problema aconteceu ao processar sua requisição. Código "+response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("onResponse.erro", "code "+response.code());
                }
                stopLoader();
            }

            @Override
            public void onFailure(Call<ResponseBook> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sem Conexão", Toast.LENGTH_SHORT).show();
                Log.e("onFailure", t.getMessage());
                stopLoader();
            }
        });
    }
}
