package questionapp.rharttech.dev.com.questionapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends ActionBarActivity {

    //variables go here
    private Button btnTrue;
    private Button btnFalse;
    private TextView lblQuestion;
    private ImageView imgPicture;

    private List<QuestionObject> questions;

    private QuestionObject currentQuestion;
    private int index;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect variables to interface items
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);

        //set questionnaire text
        lblQuestion.setText("Is my name Raegan?");

        //set image picture
        imgPicture.setImageResource(R.drawable.raegan);

        index = 0;
        score = 0;

        //onclick listeners
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();

                determineButtonPress(false);

            }
        });
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Correct!!", Toast.LENGTH_SHORT).show();

                determineButtonPress(true);

            }

        });
               generateQuestions();

               setUpQuestion();

               Paper.init(this);




    }

     private void generateQuestions() {

           questions = new ArrayList<>();

           questions.add(new QuestionObject("Is the capital of England, London?", true, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of Germany, Berlin?", true, R.drawable.berlin));
           questions.add(new QuestionObject("Is the capital of Australia, Perth?", false, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of America, Washington?", true, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of Brazil, Rio de Janeiro?", false, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of France, Paris", true, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of Russia, Sochi?", false, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of Iceland, Reykjavik?", true, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of China, Shanghai?", false, R.drawable.london));
           questions.add(new QuestionObject("Is the capital of India, New Delhi?", true, R.drawable.london));

    }
     private void setUpQuestion(){

         if (index == questions.size()){
             //they've used all their questions time to end the game
             Log.d("Question_App", "Ended all questions");

             endGame();
         }else {
             //set up a normal question

             currentQuestion = questions.get(index);

             lblQuestion.setText(currentQuestion.getQuestion());
             imgPicture.setImageResource(currentQuestion.getPicture());

             index++;
         }
     }


    private void determineButtonPress (boolean answer){

        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer) {
            //you were right
            Toast.makeText(MainActivity.this, "Correct!!", Toast.LENGTH_SHORT).show();

            score ++;
        }else {
            //you were wrong

            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();

        }

        setUpQuestion();

    }
    private void endGame(){

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations")
                .setMessage("You scored " + score + " points this round! ")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        HighScoreObject highScore = new HighScoreObject(score, "raegan", new Date().getTime());

                        List<HighScoreObject> highScores = Paper.book().read("highScores", new ArrayList<HighScoreObject>());

                        highScores.add(highScore);

                        Paper.book().write("highscores", highScores);

                        // return back to intro screen
                        finish();
                    }
                })
                .create();

        alertDialog.show();
                }


    }

