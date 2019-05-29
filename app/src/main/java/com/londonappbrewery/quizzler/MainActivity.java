package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button mTrueButton;
    Button mFalseButton;
    TextView mScoreTextView;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;
    int mIndex = 0;
    int mScore = 0;
    int mQuestion;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
           new TrueFalse(R.string.question_13,true)
    };
    final int PROGRESS_BAR_INCREMENT = ( int) Math.ceil(100.0 /
            mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mTrueButton = findViewById(R.id.true_button);
         mFalseButton = findViewById(R.id.false_button);
         mScoreTextView =  findViewById(R.id.score);
         mQuestionTextView = findViewById(R.id.question_text_view);
         mProgressBar = findViewById(R.id.progress_bar);
         mQuestion = mQuestionBank[mIndex].getQuestionId();
        mScoreTextView.setText("Score:"+mScore+"/"+mQuestionBank.length);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show();
                checkAnswer(true);
                updateQuestion();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_LONG).show();
                checkAnswer(true);
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        mScoreTextView.setText("Score:" +mScore+"/"+mQuestionBank.length);
        mIndex++;
        mIndex %= mQuestionBank.length;
        if(mIndex==0){
            //encerrar
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + " points!");
            alert.setPositiveButton("Close Application", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            alert.show();
        }
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestion);
    }

    private void checkAnswer(boolean answer) {
      if(answer==mQuestionBank[mIndex].isAnswer()){
          mScore++;
          Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_LONG).show();
      }else{
          Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_LONG).show();
      }
    }
}
