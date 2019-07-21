package com.example.yo7a.healthwatcher;

import android.Manifest;
import android.app.Dialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.yo7a.healthwatcher.MyLocation.LocationResult;
import com.example.yo7a.healthwatcher.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Primary extends AppCompatActivity {

    private String user;
    private int p;
    LocationManager locationManager;
    private String Location = "";
    private String Lat = "";
    private String Lng = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        ImageButton HeartRate = (ImageButton)this.findViewById(R.id.HR);
        ImageButton BloodPressure = (ImageButton)this.findViewById(R.id.BP);
        ImageButton Ox2 = (ImageButton)this.findViewById(R.id.O2);
        ImageButton RRate = (ImageButton)this.findViewById(R.id.RR);
        ImageButton VitalSigns = (ImageButton)this.findViewById(R.id.VS);
        ImageButton Abt = (ImageButton)this.findViewById(R.id.About);
        ImageButton CallEmergency = (ImageButton)this.findViewById(R.id.CL);
        ImageButton helpMeButtonListener = (ImageButton)this.findViewById(R.id.helpMeButton);
        ImageButton HeartBLE = (ImageButton)this.findViewById(R.id.HT);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            //The key argument here must match that used in the other activity
        }

        Abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),settings.class);
                startActivity(i);
                finish();
            }
        });


        //Every Test Button sends the username + the test number, to go to the wanted test after the instructions activity
        HeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=1;
                Intent i = new Intent(v.getContext(),StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

        BloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=2;
                Intent i = new Intent(v.getContext(),StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

        RRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=3;
                Intent i = new Intent(v.getContext(),StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

        Ox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=4;
                Intent i = new Intent(v.getContext(),StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();

            }
        });

        VitalSigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=5;
                Intent i = new Intent(v.getContext(),StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

        CallEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "0815381188";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(Primary.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {


                    return;
                }
                startActivity(intent);
            }
        });

        helpMeButtonListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationResult locationResult = new LocationResult() {
                    @Override
                    public void gotLocation(Location location) {
                        // Got the location!
                        AppPreferences appPrefs;
                        appPrefs = new AppPreferences(getApplicationContext());
                        getAddress(location);
                        Log.d("Location value", Location);
                        String message = appPrefs.getPrefs("message") + "\n" + "BestKnownLocation : \n"
                                + Location;
                        for (int i = 1; i < 6; i++) {
                            String contactNumber = appPrefs.getPrefs("contact" + i);
                            if (contactNumber.compareTo("") != 0)
                                sendSMS(contactNumber, message);
                        }
                    }
                };
                MyLocation myLocation = new MyLocation();
                myLocation.getLocation(getApplicationContext(), locationResult);

            }
        });


        HeartBLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getPackageManager().getLaunchIntentForPackage("org.marco45.polarheartmonitor");
                startActivity(i);

            }
        });




    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        Primary.super.onBackPressed();
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }

    public void getAddress(Location location) {
        Geocoder geocoder = new Geocoder(getApplicationContext(),
                Locale.getDefault());
        Lat = location.getLatitude() + "";
        Lng = location.getLongitude() + "";
        try {
            List<Address> listAddresses = geocoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(), 1);
            if (null != listAddresses && listAddresses.size() > 0) {
                Location = listAddresses.get(0).getAddressLine(0) + "\n"
                        + listAddresses.get(0).getAddressLine(1) + "\n"
                        + listAddresses.get(0).getAddressLine(2) + "\n"
                        + "Latitude : " + Lat + "\n" + "Longitude : " + Lng;
                Toast.makeText(getBaseContext(), Location, Toast.LENGTH_SHORT)
                        .show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSMS(String phoneNumber, String message) {
        Toast.makeText(getBaseContext(),
                "Sending a message to \n" + phoneNumber, Toast.LENGTH_SHORT)
                .show();
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Settings:
                Intent i = new Intent(this, settings.class);
                startActivityForResult(i, 11);
                break;
            case R.id.About:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.about);
                dialog.setTitle("About SOSApp");
                dialog.show();
                break;
        }
        return true;
    }

}


