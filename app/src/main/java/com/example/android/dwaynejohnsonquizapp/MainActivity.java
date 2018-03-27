package com.example.android.dwaynejohnsonquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isCorrectQuestion1;
    boolean isCorrectQuestion2;
    boolean isCorrectQuestion3;
    boolean isCorrectQuestion4;
    boolean isCorrectQuestion5;
    int totalQuestionsCorrect = 0;
    boolean allAnswersCorrect;
    double percentCorrect = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void submitAnswers(View view) {

        /*Instead of creating a seperate method to confirm the user entered a name, I opted
        * to place this code inside the submitAnswers method which is the onClick action
        * when the user attempts to submit their answers.  Here a check is performed to
        * confirm the EditText field is not empty.  If it is not empty, then the
        * isCorrectQuestion1 value is set to true and the calculation to determine whether or not
        * all answers was correct is executed, along with the passing of data over to the
        * display results activity.  If the name is empty, then a toast message is displayed
        * prompting the user to enter their name before they can submit their answers.*/
        EditText nameEntry = findViewById( R.id.name_input );
        String name = nameEntry.getText().toString();

        if (name != null && !(name.isEmpty())) {
            isCorrectQuestion1 = true;
            calculateTotalCorrectAnswers( isCorrectQuestion1, isCorrectQuestion2, isCorrectQuestion3, isCorrectQuestion4, isCorrectQuestion5 );
            // determine whether or not all questions in the quiz were answered correctly
            if ((isCorrectQuestion1) && (isCorrectQuestion2) && (isCorrectQuestion3)
                    && (isCorrectQuestion4) && (isCorrectQuestion5)) {
                allAnswersCorrect = true;
            } else {
                allAnswersCorrect = false;
            }

            Intent intent = new Intent( this, DisplayResultsActivity.class );
            Bundle extras = new Bundle();
            extras.putString( "Name", name );
            extras.putBoolean( "Quiz Correct", allAnswersCorrect );
            extras.putInt( "Total Questions Correct", totalQuestionsCorrect );
            extras.putDouble( "Percent Correct", percentCorrect );
            intent.putExtras( extras );
            startActivity( intent );

        } else {
            isCorrectQuestion1 = false;
            tToast( "Sorry, you must enter your name to continue" );
        }

    }

    private void calculateTotalCorrectAnswers(boolean isCorrectQuestion1, boolean isCorrectQuestion2, boolean isCorrectQuestion3, boolean isCorrectQuestion4, boolean isCorrectQuestion5) {
        //A series of 'if' statements to increase the total number of questions correct
        if (isCorrectQuestion1) {
            totalQuestionsCorrect += 1;
        }

        if (isCorrectQuestion2) {
            totalQuestionsCorrect += 1;
        }

        if (isCorrectQuestion3) {
            totalQuestionsCorrect += 1;
        }

        if (isCorrectQuestion4) {
            totalQuestionsCorrect += 1;
        }

        if (isCorrectQuestion5) {
            totalQuestionsCorrect += 1;
        }

        //Calculation of total percentage of questions correct out of 5.  Cast to a double
        //to make sure number doesn't get calculated as an integer due to whole numbers
        this.percentCorrect = (((double) (totalQuestionsCorrect) * 100) / 5);
    }

    public void onQuestion2Selected(View view) {
        //variable to indicate whether or not option selected is correct in switch statement below
        boolean checked = ((RadioButton) view).isChecked();


        //Verify whether or not correct scene was selected
        //Using a switch statement for demonstration purposes
        switch (view.getId()) {
            case R.id.dwayne_johnson1:
                if (checked)
                    isCorrectQuestion2 = false;
                break;
            case R.id.dwayne_johnson2:
                if (checked)
                    isCorrectQuestion2 = true;
                break;
            case R.id.dwayne_johnson3:
                if (checked)
                    isCorrectQuestion2 = false;
                break;
            case R.id.dwayne_johnson4:
                if (checked)
                    isCorrectQuestion2 = false;
                break;


        }
    }

    public void onQuestion3Checked(View view) {
        /*For Question three, there are 3 checkboxes that must be selected to consider
        * the question as correct, if either of the other two checkboxes is checked
        * the the question is considered incorrect
        * NOTE: I had to rearrange the order for the app to work correctly, I originally
        * put the true scenario first, but then if checkbox 1 or 4 were checked, the logic was
        * being skipped so the question was being counted as correct*/

        CheckBox checkBox1 = findViewById( R.id.checkbox1 );
        CheckBox checkBox2 = findViewById( R.id.checkbox2 );
        CheckBox checkBox3 = findViewById( R.id.checkbox3 );
        CheckBox checkBox4 = findViewById( R.id.checkbox4 );
        CheckBox checkBox5 = findViewById( R.id.checkbox5 );

        if (checkBox1.isChecked() || checkBox4.isChecked()) {
            isCorrectQuestion3 = false;
        } else if (checkBox2.isChecked() && checkBox3.isChecked() && checkBox5.isChecked()) {
            isCorrectQuestion3 = true;
        }
    }

    public void onQuestion4Selected(View view) {
    /*For the first radio button in Question 2, I used a switch statement to check
    to see whether or not the user answered the question right.  For the remainder of the
    the questions, I simplified the code since there can only be one correct answer.  Here
    I just check to see if jumanji is checked by using a simple if/else statement
     */

        RadioButton radioButton = findViewById(R.id.jumanji);

        if (radioButton.isChecked()){
            isCorrectQuestion4 =  true;
        } else {
            isCorrectQuestion4 =  false;
        }

    }

    public void onQuestion5Selected(View view) {

        /*Same logic as question 4 to determine whether or not user selected correct
        number of movies Dwayne starred in as Luke Hobbs*/
        RadioButton radioButton = findViewById(R.id.value4);

        if (radioButton.isChecked()){
            isCorrectQuestion5 =  true;
        } else {
            isCorrectQuestion5 =  false;
        }
    }

    public void resetApp(View view) {
        Intent intent = getIntent();
        finish();
        startActivity( intent );
    }

    /**
     * Toast invocation to display messages in the app
     *
     * @param s = string for toast message
     */
    private void tToast(String s) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText( context, s, duration );
        toast.show();
    }
}
