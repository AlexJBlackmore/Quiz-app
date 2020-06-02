package com.alexblackmore.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    public static String EXTRA_SCORE = "com.alexblackmore.quiz.SCORE";
    int scoreInt;
    boolean includeAddBool;
    boolean includeSubBool;
    Button answer1Btn;
    Button answer2Btn;
    View.OnClickListener answerPressListener;
    TextView countdownET;
    TextView questionET;
    TextView timerET;
    String correctAnswer;
    String wrongAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerET = findViewById(R.id.timerWdg);
        countdownET = findViewById(R.id.countdownWdg);
        answer1Btn = findViewById(R.id.answer1Wdg);
        answer2Btn = findViewById(R.id.answer2Wdg);
        questionET = findViewById(R.id.questionWdg);

        answer1Btn.setVisibility(View.INVISIBLE);
        answer2Btn.setVisibility(View.INVISIBLE);

        Intent receivedIntent = getIntent();
        includeAddBool = receivedIntent.getBooleanExtra(welcome.EXTRA_ADDITIONBOOL, false);
        includeSubBool = receivedIntent.getBooleanExtra(welcome.EXTRA_SUBTRACTIONBOOL, false);

        System.out.println("*******MAIN ACTIVITY*********");
        System.out.println("addition boolean is " + includeAddBool + "and the subtraction bool is " + includeSubBool);

        scoreInt = 0;
        startCountdown();
    }



    public void beginQuiz() {

        questionET.setVisibility(View.VISIBLE);
        answer1Btn.setVisibility(View.VISIBLE);
        answer2Btn.setVisibility(View.VISIBLE);

        answerPressListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button buttonifyingTheParam = (Button) v;

                if (correctAnswer.equals((buttonifyingTheParam.getText().toString()))) {

                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    scoreInt++;

                } else if (wrongAnswer.equals((buttonifyingTheParam.getText().toString()))) {

                    Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(MainActivity.this, "Something strange has happened.", Toast.LENGTH_SHORT).show();

                }

                generateQuestion();
            }
        };

        answer1Btn.setOnClickListener(answerPressListener);
        answer2Btn.setOnClickListener(answerPressListener);

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerET.setText(Long.toString(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Intent loadResultsIntent = new Intent(answer1Btn.getContext(), results.class);
                loadResultsIntent.putExtra(EXTRA_SCORE, scoreInt);
                startActivity(loadResultsIntent);
            }
        }.start();


        generateQuestion();

    }

    public void generateAdditionQuestion() {

        int number1;
        int number2;
        Random rand = new Random();

        number1 = rand.nextInt(10);
        number2 = rand.nextInt(10);


        String questionString = number1 + " + " + number2;
        questionET.setText(questionString);

        correctAnswer = Integer.toString(number1 + number2);
        wrongAnswer = Integer.toString(rand.nextInt(10));

        while (wrongAnswer.equals(correctAnswer)) {

            wrongAnswer = Integer.toString(rand.nextInt(10));

        }

        if (rand.nextInt(2) == 1) {
            answer1Btn.setText(correctAnswer);
            answer2Btn.setText(wrongAnswer);
        } else {
            answer2Btn.setText(correctAnswer);
            answer1Btn.setText(wrongAnswer);
        }
    }

    public void generateSubtractionQuestion() {

        int number1;
        int number2;
        Random rand = new Random();

        number1 = rand.nextInt(10);
        number2 = rand.nextInt(10);


        String questionString = number1 + " - " + number2;
        questionET.setText(questionString);

        correctAnswer = Integer.toString(number1 - number2);
        wrongAnswer = Integer.toString(rand.nextInt(10));

        while (wrongAnswer.equals(correctAnswer)) {

            wrongAnswer = Integer.toString(rand.nextInt(10));

        }

        if (rand.nextInt(2) == 1) {
            answer1Btn.setText(correctAnswer);
            answer2Btn.setText(wrongAnswer);
        } else {
            answer2Btn.setText(correctAnswer);
            answer1Btn.setText(wrongAnswer);
        }
    }



    public void generateQuestion() {
        Random r = new Random();

        if (includeAddBool && includeSubBool) {
            int randomNum = r.nextInt(2);

            switch (randomNum) {
                case 0:
                    generateAdditionQuestion();
                    break;
                case 1:
                    generateSubtractionQuestion();
                    break;
            }
        }

        if ((!includeAddBool) && includeSubBool) {
           generateSubtractionQuestion();
        }

        if ((!includeSubBool) && includeAddBool) {
            generateAdditionQuestion();
        }
    }

    public void startCountdown() {

        new CountDownTimer(4000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        if (countdownET.getText().equals("3")) {

                            countdownET.setText("2");

                        } else if (countdownET.getText().equals("2")) {

                            countdownET.setText("1");

                        } else if (countdownET.getText().equals("1")) {

                            countdownET.setText("GO!");

                        }
                    }
                }, 1000);

            }

            @Override
            public void onFinish() {
                countdownET.setVisibility(View.INVISIBLE);
                beginQuiz();
            }
        }.start();

    }
}
