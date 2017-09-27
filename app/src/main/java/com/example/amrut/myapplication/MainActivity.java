package com.example.amrut.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.location.Location;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private final static long MIN_DIST = 1; //1 meter
    private final static long MIN_TIME = 1000; //millisec

    Button buttonSend, retrieve_location_button;
    EditText textPhoneNo;
    EditText textSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) findViewById(R.id.editTextSMS);
        retrieve_location_button = (Button) findViewById(R.id.retrieve_location);
        String dummy = "AHA LOG";
        Log.e("variables declared",dummy);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener locationListener = new MyLocationListener();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Log.e("show permission request",dummy);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSION_ACCESS_COARSE_LOCATION);
                Log.e("request permission",dummy);
            }

        }
        Log.e("request location update",dummy);

       // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);

        buttonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();
                MyLocationListener new_list = new MyLocationListener();

                try {
                    String curr_loc = new_list.getMyLocation();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), curr_loc,
                            Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    String curr_loc = new_list.getMyLocation();
                    Toast.makeText(getApplicationContext(),
                            curr_loc,
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }
}

;


