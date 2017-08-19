package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int hour = intent.getExtras().getInt("hour");
        int minute = intent.getExtras().getInt("minute");
        Log.i("Service", "iniciando service");
        new CalculateTime().execute(hour, minute);

        return START_REDELIVER_INTENT;
    }

    private class CalculateTime extends AsyncTask<Integer,  Void, Void> {
        @Override
        protected Void doInBackground(Integer... params) {
            int hour  = params[0];
            int minute  = params[1];

            while (true) {
                try {
                    Thread.sleep( 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                int currentMinute = rightNow.get(Calendar.MINUTE);
                if(hour == currentHour && minute == currentMinute){
                    stopSelf();
                    return null;
                }
                Log.i("Service", "esperando mais um pouco");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Acorda!!!", Toast.LENGTH_SHORT).show();
        Log.i("Service", "acabou a espera");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
