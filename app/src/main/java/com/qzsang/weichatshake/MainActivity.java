package com.qzsang.weichatshake;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qzsang.weichatshake.view.ShakeView;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;
    private SensorEventListener mSensorEventListener;

    private ShakeView svShake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        svShake = (ShakeView) findViewById(R.id.sv_shake);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //获取 SensorManager 负责管理传感器
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));

        if (mSensorEventListener == null) {
            mSensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];

//                    Log.e("onSensorChanged",x + "," + y + "," + z + "," );
                    if (Math.abs(x) > 25 || Math.abs(y) > 25 || Math.abs(z) > 25 ) {
                        Log.e("onSensorChanged",x + "," + y + "," + z + "," );
                        svShake.startAnimation();
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }

        if (mSensorManager != null) {
            //获取加速度传感器
            mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mAccelerometerSensor != null && mSensorEventListener != null) {
                mSensorManager.registerListener(mSensorEventListener, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    protected void onPause() {
        // 务必要在pause中注销 mSensorManager
        // 否则会造成界面退出后摇一摇依旧生效的bug
        if (mSensorManager != null && mSensorEventListener != null) {
            mSensorManager.unregisterListener(mSensorEventListener);
        }
        super.onPause();
    }


}
