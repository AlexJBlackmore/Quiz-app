package com.alexblackmore.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class results extends AppCompatActivity {

    TextView resultTV;
    int receivedScoreInt;
    Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultTV = findViewById(R.id.scoreWdg);
        playAgainButton = findViewById(R.id.playAgainWdg);

        playAgainButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadWelcomeActivityIntent = new Intent(playAgainButton.getContext(), welcome.class);
                startActivity(loadWelcomeActivityIntent);
            }
        });

        Intent receivedResultsIntent = getIntent();
        receivedScoreInt = receivedResultsIntent.getIntExtra(MainActivity.EXTRA_SCORE, 0);

        resultTV.setText(Integer.toString(receivedScoreInt));


    }
}
