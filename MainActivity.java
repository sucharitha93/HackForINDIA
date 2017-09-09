package amrita.cse.amuda.hackforindia;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    double accX,accY,accZ;
    float prevAcc,maxAcc;

    private static final float ALPHA = 0.8F;
    private float gravity[] = new float[3];
    TextView tv;
//    ArrayList<Float> accx = new ArrayList<Float>();
//    ArrayList<Float> accy = new ArrayList<Float>();
//    ArrayList<Float> accz = new ArrayList<Float>();
//
//    private float calcGravityForce(float currentVal, int index) {
//        return  ALPHA * gravity[index] + (1 - ALPHA) * currentVal;
//    }
//
//    private float calcMaxAcceleration(SensorEvent event) {
//        gravity[0] = calcGravityForce(event.values[0], 0);
//        gravity[1] = calcGravityForce(event.values[1], 1);
//        gravity[2] = calcGravityForce(event.values[2], 2);
//
//        float accX = event.values[0] - gravity[0];
//        float accY = event.values[1] - gravity[1];
//        float accZ = event.values[2] - gravity[2];
//
//        float max1 = Math.max(accX, accY);
//        return Math.max(max1, accZ);
//    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        maxAcc = calcMaxAcceleration(event);
//
//        if(Math.abs(maxAcc-prevAcc) > 2)
//        {
//            tv.setText("Max Acceleration: "+(maxAcc-prevAcc));
//            prevAcc=maxAcc;
//        }

       // timestamp = event.timestamp;
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
//            accx.add(event.values[0]);
//            accy.add(event.values[1]);
//            accz.add(event.values[2]);
            accX=event.values[0];
            accY=event.values[1];
            accZ=event.values[2];
        }
        double gX = accX / 9.8f;
        double gY = accY / 9.8f;
        double gZ = accZ / 9.8f;

        double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);
        if(gForce>2&& gForce<3 && gZ > gY)
        {
            tv.setText("PIT DETECTED !");
            //tv.append("X: "+accX+" Y: "+accY+" Z: "+accZ+"\n");
        }
        else if(gY>gZ && gForce >=3)
        {
            tv.setText("Crash Detected");
            //tv.append("X: "+accX+" Y: "+accY+" Z: "+accZ+"\n");
        }
        else
        {

            //tv.setText("X: "+accX+" Y: "+accY+" Z: "+accZ+"\n");
        }


        //tv.append("X: "+accx.get(accx.size()-1)+" Y: "+accy.get(accy.size()-1)+" Z: "+accz.get(accz.size()-1)+"\n");
        tv.append("X: "+accX+" Y: "+accY+" Z: "+accZ+"\n");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prevAcc=0;

        tv = (TextView)findViewById(R.id.textView);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }
}
