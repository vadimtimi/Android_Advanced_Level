package com.refresh.pos.app02;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;
    private Display mDisplay;
    private ListView listView;
    private ScrollView scrollViewParent;
    private WindowManager mWindowManager;
    private float mSensorX = 0;
    private float mSensorY = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listSensor = sm.getSensorList(Sensor.TYPE_ALL);

        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sm.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE).size()!=0){
            Sensor s = sm.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sm.getSensorList(Sensor.TYPE_LIGHT).size()!=0){
            Sensor s = sm.getSensorList(Sensor.TYPE_LIGHT).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }

        // Get an instance of the WindowManager
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        List<String> listSensorType = new ArrayList<>();
        for (int i = 0; i < listSensor.size(); i++) {
            StringBuilder sb = new StringBuilder();
            if (listSensor.get(i).getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            }

            sb.append("name = ").append(listSensor.get(i).getName())
                    .append(", type = ").append(listSensor.get(i).getType())
                    .append("\nvendor = ").append(listSensor.get(i).getVendor())
                    .append(" ,version = ").append(listSensor.get(i).getVersion())
                    .append("\nmax = ").append(listSensor.get(i).getMaximumRange())
                    .append("\npower = ").append(listSensor.get(i).getPower())
                    .append(", resolution = ").append(listSensor.get(i).getResolution())
                    .append("\n--------------------------------------\n");

            listSensorType.add(sb.toString());
        }

        listView = findViewById(R.id.listSensors);
        // используем адаптер данных
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSensorType));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                String senName = (String) ((TextView) itemClicked).getText();
                getInfoSensor(senName);
            }
        });
    }

    private void getInfoSensor(String sensorName)
    {
        Toast.makeText(getApplicationContext(), sensorName , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        StringBuilder mInfo = new StringBuilder();

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            mInfo = new StringBuilder("Освещенность:");
            mInfo.append(event.values[0]);
            mInfo.append("\n");
        }
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            mInfo = new StringBuilder("Температура:");
            mInfo.append(event.values[0]);
            mInfo.append("\n");
        }
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            mInfo = new StringBuilder("Давление:");
            mInfo.append(event.values[0]);
            mInfo.append("\n");
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            switch (mDisplay.getRotation()) {
                case Surface.ROTATION_0:
                    mSensorX = event.values[0];
                    mSensorY = event.values[1];

                    //textview.setText(String.valueOf( mSensorX));
                    break;
                case Surface.ROTATION_90:
                    mSensorX = -event.values[1];
                    mSensorY = event.values[0];
                    //textview.setText(String.valueOf( mSensorX));
                    break;
                case Surface.ROTATION_180:
                    mSensorX = -event.values[0];
                    mSensorY = -event.values[1];
                    //textview.setText(String.valueOf( mSensorX));
                    break;
                case Surface.ROTATION_270:
                    mSensorX = event.values[1];
                    mSensorY = -event.values[0];
                    //textview.setText(String.valueOf( mSensorX));
                    break;
            }
        }

        mInfo.append(String.valueOf( mSensorX) + " x " + String.valueOf( mSensorY) );

        getInfoSensor(mInfo.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
