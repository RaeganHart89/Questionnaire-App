package questionapp.rharttech.dev.com.questionapp;
// Required Imports
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends ActionBarActivity {
    //Variables
    private Button feedback;

        // onCreate method to set up activity
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            feedback = (Button) findViewById(R.id.btn_feedback);
            feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendFeedback();
                }
            });
        }
        // Set-up menu for activity
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        //Start a new activity for sending a feedback email
        private void sendFeedback() {
            final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
            _Intent.setType("text/plain");
            _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
            _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
            _Intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
            startActivity(Intent.createChooser(_Intent, getString(R.string.title_send_feedback)));
        }
    }