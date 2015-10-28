package questionapp.rharttech.dev.com.questionapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class IntroductionActivity extends ActionBarActivity {

    private Button btnPlay;
    private Button btnHighScore;
    private Button btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnHighScore = (Button)findViewById(R.id.btnHighScore);
        btnAbout = (Button)findViewById(R.id.btnAbout);

        btnPlay.setOnClickListener (new View.OnClickListener(){
            @Override
          public void onClick(View v){

            }
        });
        btnHighScore.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

    }



}
