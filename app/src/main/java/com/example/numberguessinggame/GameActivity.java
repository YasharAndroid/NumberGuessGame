package com.example.numberguessinggame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    private TextView textViewLast, textViewRight, textViewHint;
    private Button buttonConfirm;
    private EditText editTextGuess;
    int digits;

    Random r = new Random();
    int random;
    int remainingRight = 10;

    ArrayList<Integer> guessList = new ArrayList<>();
    int userAttempt = 0;

    Animation animationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        digits = getIntent().getIntExtra("digits", 0);
        getSupportActionBar().setTitle(digits + " Digits Number Guess");
        setContentView(R.layout.activity_game);


        textViewLast = findViewById(R.id.textViewLast);
        textViewRight = findViewById(R.id.textViewRight);
        textViewHint = findViewById(R.id.textViewHint);
        editTextGuess = findViewById(R.id.editTextGuess);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        if (digits == 2) {
            random = r.nextInt(90) + 10;
        }
        if (digits == 3) {
            random = r.nextInt(900) + 100;
        }
        if (digits == 4) {
            random = r.nextInt(9000) + 1000;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = editTextGuess.getText().toString();
                if (guess.equals("")) {
                    Toast.makeText(GameActivity.this, "Please enter a guess number", Toast.LENGTH_SHORT).show();
                } else {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);

                    animationTextView = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.game_animation);
                    textViewHint.setAnimation(animationTextView);

                    userAttempt++;
                    remainingRight--;

                    int userGuess = Integer.parseInt(guess);
                    Log.i("guess", "" + random);
                    guessList.add(userGuess);
                    textViewLast.setText("Your Last guess is : " + guess);
                    textViewRight.setText("Your remaining right is : " + remainingRight);

                    if (random == userGuess) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game").setIcon(R.drawable.emoji_emotions).setCancelable(false)
                                .setMessage("Good Job,\nmy guess was " +
                                        "" + random + "\n\nYou got the number in " + userAttempt + " attempts.\n\n Your guess : " + guessList +
                                        "\nWould you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }
                    if (random < userGuess) {
                        textViewHint.setText("Decrease your guess");
                    }
                    if (random > userGuess) {
                        textViewHint.setText("Increase your guess");
                    }

                    if (remainingRight == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game").setIcon(R.drawable.mood_bad).setCancelable(false)
                                .setMessage("Sorry, your right to guess is over\nmy guess was " +
                                        "" + random + "\n\n Your guess : " + guessList +
                                        "\nWould you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }

                    editTextGuess.setText("");
                }
            }
        });


    }
}