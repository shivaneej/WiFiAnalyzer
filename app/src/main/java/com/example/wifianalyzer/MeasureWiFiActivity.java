package com.example.wifianalyzer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MeasureWiFiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_wi_fi);
    }
    public void getStrength(Context context)
    {

        String ssid = null,bssid;
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
                String output = new String("RSSI: "+rssi+"\nSSID: "+ssid+"\nBSSID: "+bssid+"\nSpeed: "+speed);
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText(output);
            }
        }

    }

}
