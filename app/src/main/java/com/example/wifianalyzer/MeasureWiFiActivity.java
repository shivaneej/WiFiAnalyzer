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
import android.widget.TextView;

import java.util.List;

public class MeasureWiFiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_wi_fi);
        Context c = getApplicationContext();
        getStrength(c);

    }
    public void getStrength(Context context)
    {

        String ssid = null,bssid,output;
        int rssi,speed;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null)
            {
                rssi = connectionInfo.getRssi();
                ssid = connectionInfo.getSSID();
                bssid = connectionInfo.getBSSID();
                speed = connectionInfo.getLinkSpeed();
                output = new String("RSSI: "+rssi+" dBm\nSSID: "+ssid+"\nBSSID: "+bssid+"\nSpeed: "+speed+" Mbps");
                Log.d("works","works");
                Log.d("msg",output);
                TextView textView = findViewById(R.id.textView);
                textView.setText(output);
            }
        }
        else
        {
            output = "Please connect your device to a WiFi Network.";
            TextView textView = findViewById(R.id.textView);
            textView.setText(output);
        }

    }

}
