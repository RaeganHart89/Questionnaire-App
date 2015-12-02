package questionapp.rharttech.dev.com.questionapp;
// Required Imports
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivity extends ActionBarActivity {

    //Variables
    private ListView listView;
    private List<HighScoreObject> highscores;
    //onCreate to Set-up activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        listView = (ListView)findViewById(R.id.listView);
        // Paper storage for high scores
        Paper.init(this);
        // Read Paper
        highscores = Paper.book().read("High scores", new ArrayList<HighScoreObject>());
        // Set Adapter
        HighscoreAdapter adapter = new HighscoreAdapter(highscores);
        // Set Listview = Adapter
        listView.setAdapter(adapter);
    }

    private class HighscoreAdapter extends ArrayAdapter<HighScoreObject> {

        public HighscoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.row_highscore, null);
            }

            //get the highscore object for the row we're looking at
            HighScoreObject highscore = highscores.get(position);
            Date date = new Date(highscore.getTime());

            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");

            TextView lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);

            lblTitle.setText(highscore.getScore() + " - " + highscore.getName() + " - " + fmtOut.format(date));

            return convertView;
        }

    }

}