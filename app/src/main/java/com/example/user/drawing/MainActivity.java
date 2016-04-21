package com.example.user.drawing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CanvasView customCanvas;
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, AudioIntentService.class);
        startService(intent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    private final SensorEventListener mSensorListener= new SensorEventListener()
    {
        public void onSensorChanged(SensorEvent se)
        {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            displayAcceleration();
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    @Override
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void displayAcceleration() {

        int accel = Math.abs((int) mAccel);

        if (accel > 1)
        {
            Intent intent = new Intent(this, AudioIntentService.class);
            startService(intent);
            clearCanvas(customCanvas);
        }
    }

}