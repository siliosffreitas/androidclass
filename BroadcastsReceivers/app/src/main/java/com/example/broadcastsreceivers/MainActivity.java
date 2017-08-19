package com.example.broadcastsreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lblCount;
    private Button btnAdd;
    private Button btn2Screen;

    private int count;

    private BroadcastReceiver receiver;
    public static final String INCREMENT_COUNT = "INCREMENT_COUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblCount = (TextView) findViewById(R.id.lblCount);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btn2Screen = (Button) findViewById(R.id.btn2Screen);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementCount();
            }
        });

        btn2Screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                incrementCount();
            }
        };

        registerReceiver(receiver, new IntentFilter(INCREMENT_COUNT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void incrementCount(){
        lblCount.setText(String.valueOf(++count));
    }
}