package com.example.hardware;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    Sensor accelerometer;
    SensorManager sensorManager;

    private TextView lblX;
    private TextView lblY;
    private TextView lblZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        lblX = (TextView) findViewById(R.id.lblX);
        lblY = (TextView) findViewById(R.id.lblY);
        lblZ = (TextView) findViewById(R.id.lblZ);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float sensorX = event.values[0];
        float sensorY = event.values[1];
        float sensorZ = event.values[2];

        lblX.setText("X: " + (sensorX));
        lblY.setText("Y: " + (sensorY));
        lblZ.setText("Z: " + (sensorZ));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
