package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
public class AndroidGuessActivity extends AppCompatActivity {
    private int secretNumber;
    private int attempts;
    private EditText etGuess;
    private TextView tvHint;
    private TextView tvAttempts;
    private Button btnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_android_guess);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etGuess = findViewById(R.id.et_guess);
        tvHint = findViewById(R.id.tv_hint);
        tvAttempts = findViewById(R.id.tv_attempts);
        btnNewGame = findViewById(R.id.btn_new_game);

        startNewGame();
    }

    private void startNewGame() {
        secretNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        etGuess.setText("");
        etGuess.setEnabled(true);
        tvHint.setText("");
        tvAttempts.setText("");
        btnNewGame.setVisibility(View.GONE);
        Log.d("ANDROID_GAME", "Нова гра. Секретне число: " + secretNumber);
    }

    public void checkGuess(View view) {
        String input = etGuess.getText().toString().trim();
        if (input.isEmpty()) {
            tvHint.setText(R.string.human_empty_input);
            return;
        }

        int guess = Integer.parseInt(input);
        attempts++;
        tvAttempts.setText(getString(R.string.human_attempts) + attempts);

        if (guess < secretNumber) {
            tvHint.setText(R.string.human_more);
        } else if (guess > secretNumber) {
            tvHint.setText(R.string.human_less);
        } else {
            tvHint.setText(getString(R.string.human_win) + secretNumber + "!");
            etGuess.setEnabled(false);
            btnNewGame.setVisibility(View.VISIBLE);
            Log.d("ANDROID_GAME", "Вгадано за " + attempts + " спроб");
        }

        etGuess.setText("");
    }

    public void newGame(View view) {
        startNewGame();
    }

    public void goBack(View view) {
        finish();
    }
}