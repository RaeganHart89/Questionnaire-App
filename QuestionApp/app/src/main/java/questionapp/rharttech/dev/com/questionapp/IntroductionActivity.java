package questionapp.rharttech.dev.com.questionapp;
// Required Imports
import android.content.Intent;
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
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends ActionBarActivity {

    // Variables
    private Button btnPlay;
    private Button btnHighScore;
    private Button btnAbout;
    private Button btnClear;

    //onCreate to Set-up activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        //Connect variables to buttons
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnHighScore = (Button) findViewById(R.id.btnHighScore);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnClear = (Button) findViewById(R.id.btnClear);

        // Set OnClick Listeners
        // Play Button
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        // High Score Button
        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
                startActivity(i);
            }
        });
        // About Button
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
        // Clear High Score Button
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().delete("High scores");
                Toast.makeText(IntroductionActivity.this, "High scores cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // On Resume
    protected void onResume() {
        super.onResume();
        Paper.init(this);
        //Read high scores from Paper
        List<HighScoreObject> highScores = Paper.book().read("High scores", new ArrayList<HighScoreObject>());
        //Variable
        int maxScore = 0;
        //High score determined
        for (int i = 0; i < highScores.size(); i++) {
            HighScoreObject h = highScores.get(i);
            if (h.getScore() > maxScore) {
                maxScore = h.getScore();
            }
        }
        //Determines highest score
        TextView bestScoreText = (TextView) findViewById(R.id.bestScore);
        //Customize high score display
        bestScoreText.setText("CURRENT HIGH SCORE = " + maxScore);
        Log.d("Raegan_App", "Reached onResume");
    }
}