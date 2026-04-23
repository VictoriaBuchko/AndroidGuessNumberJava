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
public class HumanGuessActivity extends AppCompatActivity {
    private int secretNumber;
    private int attempts;
    private EditText etSecret;
    private EditText etGuess;
    private TextView tvPhase;
    private TextView tvHint;
    private TextView tvAttempts;
    private Button btnSetSecret;
    private Button btnCheckGuess;
    private Button btnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_human_guess);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etSecret = findViewById(R.id.et_secret);
        etGuess = findViewById(R.id.et_guess);
        tvPhase = findViewById(R.id.tv_phase);
        tvHint = findViewById(R.id.tv_hint);
        tvAttempts = findViewById(R.id.tv_attempts);
        btnSetSecret = findViewById(R.id.btn_set_secret);
        btnCheckGuess = findViewById(R.id.btn_check_guess);
        btnNewGame = findViewById(R.id.btn_new_game);

        startNewGame();
    }

    private void startNewGame() {
        attempts = 0;
        //перший гравець вводить число
        tvPhase.setText(R.string.human_phase_enter);
        etSecret.setText("");
        etSecret.setEnabled(true);
        etSecret.setVisibility(View.VISIBLE);
        btnSetSecret.setVisibility(View.VISIBLE);

        etGuess.setText("");
        etGuess.setEnabled(false);
        etGuess.setVisibility(View.GONE);
        btnCheckGuess.setVisibility(View.GONE);

        tvHint.setText("");
        tvAttempts.setText("");
        btnNewGame.setVisibility(View.GONE);
    }

    //перший гравець підтверджує своє число
    public void setSecret(View view) {
        String input = etSecret.getText().toString().trim();
        if (input.isEmpty()) {
            tvHint.setText(R.string.human_empty_input);
            return;
        }

        secretNumber = Integer.parseInt(input);
        Log.d("HUMAN_GAME", "Секретне число встановлено: " + secretNumber);

        //вгадування
        tvPhase.setText(R.string.human_phase_guess);
        tvHint.setText("");

        etSecret.setVisibility(View.GONE);
        btnSetSecret.setVisibility(View.GONE);

        etGuess.setEnabled(true);
        etGuess.setVisibility(View.VISIBLE);
        btnCheckGuess.setVisibility(View.VISIBLE);
    }

    //другий гравець вводить здогадку
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
            Log.d("HUMAN_GAME", "Вгадано за " + attempts + " спроб");
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