package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private long timeOnCreate;
    private long timeOnStart;
    private long timeOnResume;
    private long timeOnPause;
    private long timeOnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timeOnCreate = System.currentTimeMillis();
        Log.d(TAG, "onCreate вызван");

        Button myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(v -> {
            Log.d(TAG, "Кнопка була натиснута");
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        timeOnStart = System.currentTimeMillis();
        Log.d(TAG, "onStart вызван");
        Log.d(TAG, "onCreate - onStart: " + (timeOnStart - timeOnCreate) + " мс");
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeOnResume = System.currentTimeMillis();
        Log.d(TAG, "onResume вызван");
        Log.d(TAG, "onStart - onResume: " + (timeOnResume - timeOnStart) + " мс");
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeOnPause = System.currentTimeMillis();
        Log.d(TAG, "onPause вызван");
    }

    @Override
    protected void onStop() {
        super.onStop();
        timeOnStop = System.currentTimeMillis();
        Log.d(TAG, "onStop вызван");
        Log.d(TAG, "onPause - onStop: " + (timeOnStop - timeOnPause) + " мс");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        long timeOnDestroy = System.currentTimeMillis();
        Log.d(TAG, "onDestroy вызван");
        Log.d(TAG, "onStop - onDestroy: " + (timeOnDestroy - timeOnStop) + " мс");
    }
}