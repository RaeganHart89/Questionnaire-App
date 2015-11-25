package questionapp.rharttech.dev.com.questionapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private String name = "";
    private TextView InGameScore;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect variables to interface items
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        InGameScore = (TextView)findViewById(R.id.InGameScore);

        //set questionnaire text
        lblQuestion.setText("Is my name Raegan?");

        //set image picture
        imgPicture.setImageResource(R.drawable.raegan);

        index = 0;
        score = 0;

        //onclick listeners

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                determineButtonPress(true);
            }

        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                determineButtonPress(false);
            }
        });
               generateQuestions();
               setUpQuestion();
               Paper.init(this);
    }

     private void generateQuestions() {
           Log.d("Raegan_App", "Reached generateQuestions");
           questions = new ArrayList<>();

           questions.add(new QuestionObject("Is this the Avengers logo?", true, R.drawable.avengers));
           questions.add(new QuestionObject("Is this a symbol of a Transformer?", true, R.drawable.autobot2));
           questions.add(new QuestionObject("Is this the symbol of Spiderman?", false, R.drawable.superman));
           questions.add(new QuestionObject("Is this the symbol of Wonder Woman?", true, R.drawable.women));
           questions.add(new QuestionObject("Is this the symbol of Flash?", false, R.drawable.captainmarvel));
           questions.add(new QuestionObject("Is this the Skeletor logo?", false, R.drawable.punisher));
           questions.add(new QuestionObject("Is this the symbol of Green Lantern?", true, R.drawable.greenlantern));
           questions.add(new QuestionObject("Is this the Incredibles logo?", false, R.drawable.catwomen));
           questions.add(new QuestionObject("Is this the symbol of Captain America?", true, R.drawable.captain));
           questions.add(new QuestionObject("Is this the symbol of Dead Pool?", true, R.drawable.deadpool));
           Log.d("Raegan_App", "Completed generateQuestions");
    }
     private void setUpQuestion(){

         if (index == questions.size()){
             //they've used all their questions time to end the game
             Log.d("Raegan_App", "Ended all questions");

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
            player = MediaPlayer.create(MainActivity.this, R.raw.good);
            player.start();
            //you were right
            Toast.makeText(MainActivity.this, "Correct!!", Toast.LENGTH_SHORT).show();

            score ++;
            InGameScore.setText("Your Score so far is " + score);
        }else {
            player = MediaPlayer.create(MainActivity.this, R.raw.bad);
            player.start();
            //you were wrong

            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();

        }

        setUpQuestion();

    }
    private void endGame() {
        Log.d("Raegan_App", "Reached onResume");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations!");
        builder.setMessage("You scored " + score + " points this round." + "\n\n" + "Please enter your name.");

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();

                // New high score and user name
                HighScoreObject highScore = new HighScoreObject(score, name, new Date().getTime());

                // Get user prefs
                List<HighScoreObject> highScores = Paper.book().read("High scores", new ArrayList<HighScoreObject>());

                // Add item - scores
                highScores.add(highScore);

                // Save again
                Paper.book().write("High scores", highScores);

                // Return back to the introduction screen
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                // Return back to the introduction screen
                finish();
            }
        });
        builder.show();
        Log.d("Raegan_App", "Completed endGame");
    }

    }

