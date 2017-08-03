package com.hmp.android.datapoints;

import android.app.ActivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn,btnn1,mem;
    TextView txt,txt1,txt2,free,occu;
    String str;
    double availableMegs,percentAvail;
    float freeSpace,occupiedSpace,totalSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmsReceiver.bindListener(new SmsListner() {
            @Override
            public void messageReceived(String messageHeader) {

                //From the received text string you may do string operations to get the required OTP
                //It depends on your SMS format
                Log.e("Message", messageHeader);
                Toast.makeText(MainActivity.this, "Message: " + messageHeader, Toast.LENGTH_LONG).show();

            }
        });

        BatteryLevelReceiver.bindListener(new BatteryListner() {
            @Override
            public void BatteryStatus(String BatteryHeader) {

                //From the received text string you may do string operations to get the required OTP
                //It depends on your SMS format
                Log.e("Message", BatteryHeader);
                Toast.makeText(MainActivity.this, "Message: " + BatteryHeader, Toast.LENGTH_LONG).show();

            }
        });

        btn =(Button)findViewById(R.id.btnn);
        txt =(TextView)findViewById(R.id.lang);
        txt1=(TextView)findViewById(R.id.ramm);
        txt2=(TextView)findViewById(R.id.rammp);
        free=(TextView)findViewById(R.id.freespace);
        occu=(TextView)findViewById(R.id.occupied);
        btnn1=(Button)findViewById(R.id.btnn1);
        mem = (Button)findViewById(R.id.mem);
        btn.setOnClickListener(this);
        btnn1.setOnClickListener(this);
        mem.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.btnn:
                str = Locale.getDefault().getDisplayLanguage();
                txt.setText(str);
                break;
            case R.id.btnn1:
                Get_Current_Ram();
                txt1.setText((Double.toString(availableMegs)));
                txt2.setText((Double.toString(percentAvail)));
                break;
            case R.id.mem:
                Internal_Memory_Status();
        }

        }


    private void Internal_Memory_Status() {

        totalSpace = DeviceMemory.getInternalStorageSpace();
         occupiedSpace = DeviceMemory.getInternalUsedSpace();
         freeSpace = DeviceMemory.getInternalFreeSpace();
        DecimalFormat outputFormat = new DecimalFormat("#.##");
        if (null != occu) {
            occu.setText(outputFormat.format(occupiedSpace) + " MB"+ " Occupied");
        }

        if (null != free) {
            free.setText(outputFormat.format(freeSpace) + " MB"+" Free");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Get_Current_Ram(){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
         availableMegs = mi.availMem / 0x100000L;

//Percentage can be calculated for API 16+
         percentAvail = mi.availMem / (double)mi.totalMem;
    }


}
