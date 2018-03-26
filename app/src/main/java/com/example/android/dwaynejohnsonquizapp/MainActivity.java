package com.example.android.dwaynejohnsonquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

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

        EditText nameEntry = findViewById( R.id.name_input );
        String name = nameEntry.getText().toString();
        boolean correctScene = isCorrectQuestion2;

        if (name != null && !(name.isEmpty())) {
            isCorrectQuestion1 = true;
        } else {
            isCorrectQuestion1 = false;
        }

        //onQuestion3Checked();

        calculateTotalCorrectAnswers( isCorrectQuestion1, isCorrectQuestion2, isCorrectQuestion3, isCorrectQuestion4, isCorrectQuestion5 );
        // determine whether or not all questions in the quiz were answered correctly
        if ((isCorrectQuestion1) && (isCorrectQuestion2)  && (isCorrectQuestion3)
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

//        intent.putExtra(EXTRA_MESSAGE, name);

//        intent.putExtra( isCorrectScene, correctScene );

    }

    private void calculateTotalCorrectAnswers(boolean isCorrectQuestion1, boolean isCorrectQuestion2, boolean isCorrectQuestion3, boolean isCorrectQuestion4, boolean isCorrectQuestion5) {
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

        this.percentCorrect = (((double)(totalQuestionsCorrect)* 100) / 5);
    }

    public void onQuestion2Selected(View view) {
        //variable to indicate whether or not option selected is correct
        boolean checked = ((RadioButton) view).isChecked();


        //Verify whether or not correct scene was selected
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

        CheckBox checkBox1 = findViewById( R.id.checkbox1 );
        CheckBox checkBox2 = findViewById( R.id.checkbox2 );
        CheckBox checkBox3 = findViewById( R.id.checkbox3 );
        CheckBox checkBox4 = findViewById( R.id.checkbox4 );
        CheckBox checkBox5 = findViewById( R.id.checkbox5 );

        if (checkBox2.isChecked() && checkBox3.isChecked() && checkBox5.isChecked()) {
            isCorrectQuestion3 = true;
        } else if (checkBox1.isChecked() || checkBox4.isChecked()) {
            isCorrectQuestion3 = false;
        }
    }

    public void onQuestion4Selected(View view) {
        boolean checked = ((RadioButton) view).isChecked();


        //Verify whether or not correct scene was selected
        switch (view.getId()) {
            case R.id.central_intelligence:
                if (checked)
                    isCorrectQuestion4 = false;
                break;
            case R.id.jumanji:
                if (checked)
                    isCorrectQuestion4 = true;
                break;
            case R.id.get_smart:
                if (checked)
                    isCorrectQuestion4 = false;
                break;
            case R.id.ride_along:
                if (checked)
                    isCorrectQuestion4 = false;
                break;
        }
    }

    public void onQuestion5Selected(View view) {
        boolean checked = ((RadioButton) view).isChecked();


        //Verify whether or not correct scene was selected
        switch (view.getId()) {
            case R.id.value7:
                if (checked)
                    isCorrectQuestion5 = false;
                break;
            case R.id.value6:
                if (checked)
                    isCorrectQuestion5 = false;
                break;
            case R.id.value5:
                if (checked)
                    isCorrectQuestion5 = false;
                break;
            case R.id.value4:
                if (checked)
                    isCorrectQuestion5 = true;
                break;
        }

    }


    public void resetApp(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
