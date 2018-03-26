package com.example.android.dwaynejohnsonquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_display_results );

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String name = extras.getString( "Name" );
        int totalQuestionsCorrect = extras.getInt( "Total Questions Correct" );
        double percentageCorrect = extras.getDouble( "Percent Correct" );
        Boolean allAnswersCorrect = extras.getBoolean( "Quiz Correct" );

        if (allAnswersCorrect) {
            String message = "Congratulations " + name + " You've answered all questions correctly!";
            TextView textView = findViewById(R.id.resultsMessage);
            textView.setText(message);
        } else {
            String message = "Sorry, " + name + " you submitted answers that are wrong. \n \nYou answered a total of " + totalQuestionsCorrect +
                    " questions correctly.\n\n" + "Total score = " + percentageCorrect + "%";
            TextView textView = findViewById( R.id.resultsMessage );
            textView.setText( message );
        }

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Close Results Page
                finish();
                System.exit(0);
            }
        });
    }
}
