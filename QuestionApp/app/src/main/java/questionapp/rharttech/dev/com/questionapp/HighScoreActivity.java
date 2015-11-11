package questionapp.rharttech.dev.com.questionapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class HighScoreActivity extends ActionBarActivity {

    private Button btnHighScore;
    private Button btnAbout;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);


        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HighScoreActivity.this, ProfileActivity.class);
                startActivity(i);
                Toast.makeText(HighScoreActivity.this, "Score is" , Toast.LENGTH_SHORT).show();
            }
        });
    }

}
