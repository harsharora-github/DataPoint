package com.hmp.android.datapoints;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Third_activity extends AppCompatActivity {

    TextView count_SC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);

        count_SC = (TextView) findViewById(R.id.sc_count);

        IsFoldercontainsFiles();

    }

    public void IsFoldercontainsFiles() {

        String path = Environment.getExternalStorageDirectory().toString() + "/Download";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        if(directory.canRead()) {
            File[] files = directory.listFiles();
            Log.d("Files", "Size: " + files.length);
            for (int i = 0; i < files.length; i++) {
                Log.d("Files", "FileName:" + files[i].getName());
            }
        }else{
            Log.d("Files", "Unable to read");
        }
    }

}