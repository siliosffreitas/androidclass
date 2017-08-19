package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rcvUfs;
    private UFAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rcvUfs = (RecyclerView) findViewById(R.id.rcvUfs);
        rcvUfs.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvUfs.setLayoutManager(llm);

        String[] ufs = getResources().getStringArray(R.array.ufs);

        adapter = new UFAdapter(ufs);
        rcvUfs.setAdapter(adapter);
    }
}
