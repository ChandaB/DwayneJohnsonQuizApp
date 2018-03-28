package com.example.android.dwaynejohnsonquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
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
            /*ImageView setup to add a banner if they get all questions correct
            The code below is used to add the image to the display results screen programmatically
            to the empty imageLayout that I added  to the XML file.
            */
            ImageView imageView = new ImageView( this );

            LinearLayout linearLayout = findViewById( R.id.imageLayout );

            LinearLayout.LayoutParams lprams = new LinearLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT );
            linearLayout.setOrientation( LinearLayout.VERTICAL );

            lprams.gravity = Gravity.TOP;

            /*setting image resource and changing settings so that the image doesn't add a lot of
            blank space
                    */
            imageView.setImageResource( R.drawable.congratulations );
            imageView.setScaleType( ImageView.ScaleType.FIT_XY );
            imageView.setAdjustViewBounds( true );
            imageView.setCropToPadding( false );

            //setting image parameters defined earlier
            imageView.setLayoutParams( lprams );

            //add the imageview to the layout
            linearLayout.addView( imageView );

            //Here's where the message is displayed in the textview
            String message = name + ", you've answered all questions correctly!\n\n" +
                    "And you even know your name!";
            TextView textView = findViewById( R.id.resultsMessage );
            textView.setText( message );
        //Here is where a message is displayed if answers are incorrect
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
        Button btn1 = findViewById( R.id.btn1 );
        btn1.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Close Results Page
                finish();
                System.exit( 0 );
            }
        } );
    }
}
