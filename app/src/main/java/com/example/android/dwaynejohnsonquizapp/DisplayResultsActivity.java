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

        // Get the Intent that started this activity and extract the data that was passed
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String name = extras.getString( "Name" );
        int totalQuestionsCorrect = extras.getInt( "Total Questions Correct" );
        double percentageCorrect = extras.getDouble( "Percent Correct" );
        Boolean allAnswersCorrect = extras.getBoolean( "Quiz Correct" );

        /*Here I added logic to display a different message based on whether or not the user
        submitted all answers correctly.
         */
        if (allAnswersCorrect) {
            String message = "Congratulations " + name + " You've answered all questions correctly! " +
                    "and you even know your name!";
            TextView textView = findViewById(R.id.resultsMessage);
            textView.setText(message);
        } else {
            String message = "Sorry, " + name + " you submitted answers that are wrong. \n \nYou answered a total of " + totalQuestionsCorrect +
                    " questions correctly.\n\n" + "Total score = " + percentageCorrect + "%";
            TextView textView = findViewById( R.id.resultsMessage );
            textView.setText( message );
        }

        /*This is an action to take the user back to the main quiz screen.  Note this does not
        clear the data they entered. If they want to clear all data, they will need to Reset the
        app on the home screen.  As per many sites, you aren't supposed to allow a user to close
        the app via something like a 'Close' button...
        */
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
