
package com.sms.forwarder;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static String POST_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText url = findViewById(R.id.urlField);
        Button start = findViewById(R.id.startBtn);
        Button stop = findViewById(R.id.stopBtn);

        start.setOnClickListener(v -> {
            POST_URL = url.getText().toString();
            Toast.makeText(this, "Listener Started", Toast.LENGTH_SHORT).show();
        });

        stop.setOnClickListener(v -> {
            POST_URL = "";
            Toast.makeText(this, "Listener Stopped", Toast.LENGTH_SHORT).show();
        });
    }
}
