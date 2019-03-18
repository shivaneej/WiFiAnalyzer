package com.example.wifianalyzer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MeasureWiFiActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_measure_wi_fi);
        Context c = getApplicationContext();
        getStrength(c);
    }

    public void getStrength(Context context) {

        String ssid = null, bssid, output,fileop;
        int rssi, speed,freq;

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    rssi = connectionInfo.getRssi();
                    ssid = connectionInfo.getSSID();
                    bssid = connectionInfo.getBSSID();
                    speed = connectionInfo.getLinkSpeed();
                    freq = connectionInfo.getFrequency();
                    output = new String("RSSI: " + rssi + " dBm\nSSID: " + ssid + "\nBSSID: " + bssid + "\nLink Speed: " + speed + " Mbps"+"\nFrequency: "+freq+" MHz");
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(output);
//                    fileop = new String("\n"+rssi+"\t"+ssid+"\t"+speed);
//                    writeToFile(fileop);
                }
                else {
                    output = "Please connect your device to a WiFi Network.";
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(output);
                }
            final Button button = (Button) findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });

        }
    }
//    public void writeToFile(String op)
//    {
//        String file = "output.txt";
//        try
//        {
//            BufferedWriter b = new BufferedWriter(new FileWriter(file));
//            b.write(op);
//            b.close();
//        }
//        catch (IOException e){e.printStackTrace();}
//    }
}
