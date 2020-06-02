package com.alexblackmore.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    Button playButtonBtn;
    CheckBox additionCB;
    CheckBox subtractionCB;
    View.OnClickListener playListener;
    TextView welcomeET;
    boolean includeAdditionBool;
    boolean includeSubtractionBool;
    public static String EXTRA_ADDITIONBOOL = "com.alexblackmore.quiz.ADDITION";
    public static String EXTRA_SUBTRACTIONBOOL = "com.alexblackmore.quiz.SUBTRACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        playButtonBtn = findViewById(R.id.playButtonWdg);
        welcomeET = findViewById(R.id.welcomeWdg);
        additionCB = findViewById(R.id.addnCheckBoxWdg);
        subtractionCB = findViewById(R.id.subtnCheckBoxWdg);

        playListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                includeAdditionBool = additionCB.isChecked();
                includeSubtractionBool = subtractionCB.isChecked();

                Intent loadQuizIntent = new Intent(v.getContext(), MainActivity.class);
                loadQuizIntent.putExtra(EXTRA_ADDITIONBOOL, includeAdditionBool);
                loadQuizIntent.putExtra(EXTRA_SUBTRACTIONBOOL, includeSubtractionBool);

                System.out.println("*******WELCOME*********");
                System.out.println("addition boolean is " + includeAdditionBool + "and the subtraction bool is " + includeSubtractionBool);

                if ((!includeAdditionBool) && (!includeSubtractionBool)) {
                    Toast.makeText(welcome.this, "You must select at least one!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(loadQuizIntent);
                }
            }
        };
        playButtonBtn.setOnClickListener(playListener);
    }
}
