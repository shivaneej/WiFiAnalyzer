package com.example.wifianalyzer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        tv = (TextView)findViewById(R.id.fileop);
        String line = null,op=null;
        try {
            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "WifiAnalyzer");
            FileInputStream fileInputStream = new FileInputStream(new File(dir,"Log.txt"));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            line = br.readLine();
            op=line+"\n";
            while(line!= null)
            {
                op = op + line+"\n";
                line = br.readLine();
            }
            fileInputStream.close();
            tv.setText(op);
            br.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File Not Found Error", Toast.LENGTH_SHORT).show();
        }catch(IOException e) {
            Toast.makeText(this, "IO Error", Toast.LENGTH_SHORT).show();
        }
    }
}
