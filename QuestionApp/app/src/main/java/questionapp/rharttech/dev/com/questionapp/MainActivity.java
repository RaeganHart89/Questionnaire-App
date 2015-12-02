package questionapp.rharttech.dev.com.questionapp;
// Required Imports
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends ActionBarActivity {

    //Variables
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
    private ProgressBar progressBar;
    private int valuePerQuestion;
    public int questionProgressValue;

    //onCreate to Set-up activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect variables to interface items
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        InGameScore = (TextView) findViewById(R.id.InGameScore);
        //Set-up progress bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(Color.rgb(255, 6, 6), PorterDuff.Mode.SRC_IN); // Change colour of progressBar to match theme

        //Set question text
        lblQuestion.setText("Is my name Raegan?");

        //Set logo
        imgPicture.setImageResource(R.drawable.raegan);

        //Variables declared
        index = 0;
        score = 0;

        //onclick listener for True button
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                determineButtonPress(true);
            }

        });
        //onclick listener for False button
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                determineButtonPress(false);
            }
        });
        //Call methods
        generateQuestions();
        setUpQuestion();
        setUpProgressBar();
        //Initialise Paper
        Paper.init(this);
    }

     //Generate Questions
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
           questions.add(new QuestionObject("Is this Batman's logo?", true, R.drawable.batman));
           questions.add(new QuestionObject("Is this the symbol of Thor?", false, R.drawable.hellboy));
           questions.add(new QuestionObject("Is this the Fantastic Four logo?", true, R.drawable.fantasticfour));
           questions.add(new QuestionObject("Is this an Xmen logo?", true, R.drawable.xmen));
           questions.add(new QuestionObject("Is this the symbol for Reactor?", false, R.drawable.robin));
           questions.add(new QuestionObject("Is this the symbol for Spawn?", true, R.drawable.spawn));
           questions.add(new QuestionObject("Is this the symbol for the Great American Hero?", true, R.drawable.greatamerican));
           questions.add(new QuestionObject("Is this the Incredibles logo?", true, R.drawable.incredibles));
           questions.add(new QuestionObject("Is this the Double Dare logo?", false, R.drawable.daredevil));
           questions.add(new QuestionObject("Is this the symbol of a decepticon?", true, R.drawable.decepticon));
         Log.d("Raegan_App", "Completed generateQuestions");
    }
    // Set up questions
     private void setUpQuestion(){

         if (index == questions.size()){
             //All questions answered; end game
             Log.d("Raegan_App", "Ended all questions");
             endGame();
         }else {
             //Get current question
             currentQuestion = questions.get(index);
             //Display current question and logo
             lblQuestion.setText(currentQuestion.getQuestion());
             imgPicture.setImageResource(currentQuestion.getPicture());
             index++;
         }
     }
    // Sets up progress bar to display progress
    private void setUpProgressBar() {
        // Set maximum value
        progressBar.setMax(100);
        // Determines value for each question and rounds
        valuePerQuestion = Math.round(100 / questions.size());
        // Add individual value
        questionProgressValue = questionProgressValue + valuePerQuestion;
        // Sets progress
        progressBar.setProgress(valuePerQuestion);
    }
    // Determine button press
    private void determineButtonPress (boolean answer){
        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer) {
            player = MediaPlayer.create(MainActivity.this, R.raw.good);
            player.start();
            //you were right
            Toast.makeText(MainActivity.this, "Correct!!", Toast.LENGTH_SHORT).show();

            score ++;
            InGameScore.setText("Your Score so far is " + score);
            // Adds individual question value to the existing value for question answered
            questionProgressValue = questionProgressValue + valuePerQuestion;
            // Sets progress on progress bar
            progressBar.setProgress(questionProgressValue);
        }else {
            // Sets audio file that will be played
            player = MediaPlayer.create(MainActivity.this, R.raw.bad);
            player.start();
            // Display correct message to the screen
            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();
            // Adds individual question value to the existing value for question answered
            questionProgressValue = questionProgressValue + valuePerQuestion;
            // Sets progress on progress bar
            progressBar.setProgress(questionProgressValue);
        }
        // Call setUpQuestion method
        setUpQuestion();
    }

    private void endGame() {
        Log.d("Raegan_App", "Reached onResume");
        // Set up alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set title
        builder.setTitle("Congratulations!");
        // Set message to display score and prompt user to enter their name
        builder.setMessage("You scored " + score + " points this round." + "\n\n" + "Please enter your name.");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify inputs expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();
                // New high score and user name
                HighScoreObject highScore = new HighScoreObject(score, name, new Date().getTime());
                // Get user
                List<HighScoreObject> highScores = Paper.book().read("High scores", new ArrayList<HighScoreObject>());
                // Add item to scores
                highScores.add(highScore);
                // Save
                Paper.book().write("High scores", highScores);
                // Return back to introduction
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                // Return back to introduction screen
                finish();
            }
        });
        // Display alert dialog
        builder.show();
        Log.d("Raegan_App", "Completed endGame");
    }

}

