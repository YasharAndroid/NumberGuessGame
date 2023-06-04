package com.example.numberguessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private RadioButton radio2, radio3, radio4;

    int digits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.buttonStart);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, GameActivity.class);

                if (!radio2.isChecked() && !radio3.isChecked() && !radio4.isChecked()) {
                    Snackbar.make(v, "Please Select A number of Digits", Snackbar.LENGTH_LONG).show();

                } else {
                    if (radio2.isChecked()) {
                        digits = 2;
                    } else if (radio3.isChecked()) {
                        digits = 3;
                    } else {
                        digits = 4;
                    }

                    intent.putExtra("digits", digits);
                    startActivity(intent);
                }
            }
        });

    }
}