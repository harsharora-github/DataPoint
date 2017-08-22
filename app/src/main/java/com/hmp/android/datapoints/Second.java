package com.hmp.android.datapoints;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Second extends AppCompatActivity {

    ListView listitem;

    String TAG = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listitem=(ListView)findViewById(R.id.msglist);

        Uri mSmsQueryUri = Uri.parse("content://sms/inbox");
        List<String> messages = new ArrayList<String>();

        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(mSmsQueryUri, null, null, null, null);
            if (cursor == null) {
                Log.i(TAG, "cursor is null. uri: " + mSmsQueryUri);

            }
            for (boolean hasData = cursor.moveToFirst(); hasData; hasData = cursor.moveToNext()) {
                final String body = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                messages.add(body);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        listitem.setAdapter(new ArrayAdapter<String>(Second.this, android.R.layout.simple_list_item_1,messages));
    }
}
