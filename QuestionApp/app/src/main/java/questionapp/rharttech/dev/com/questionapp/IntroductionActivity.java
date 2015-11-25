package questionapp.rharttech.dev.com.questionapp;

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

    private Button btnPlay;
    private Button btnHighScore;
    private Button btnAbout;
    private Button btnClear;
    private TextView bestScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnHighScore = (Button) findViewById(R.id.btnHighScore);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
                startActivity(i);

            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().delete("High scores");
                Toast.makeText(IntroductionActivity.this, "High scores cleared", Toast.LENGTH_SHORT).show();
            }
        });


    }

    protected void onResume() {
        super.onResume();
        Paper.init(this);
        //Displays highscores on the main app page
        List<HighScoreObject> highScores = Paper.book().read("High scores", new ArrayList<HighScoreObject>());
        int maxScore = 0;
        for (int i = 0; i < highScores.size(); i++) {
            HighScoreObject h = highScores.get(i);
            if (h.getScore() > maxScore) {
                maxScore = h.getScore();
            }
        }
        //Sets the test for the highest score
        TextView bestScoreText = (TextView) findViewById(R.id.bestScore);
        bestScoreText.setText("CURRENT HIGH SCORE = " + maxScore);
        Log.d("Raegan_App", "Reached onResume");
    }
}