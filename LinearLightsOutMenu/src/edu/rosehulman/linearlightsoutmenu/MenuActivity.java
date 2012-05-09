package edu.rosehulman.linearlightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {
    
    private static final String PREFS_KEY_NUM_BUTTONS = "num_buttons";

    private int mNumButtons;
    private Button mPlayButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        mNumButtons = prefs.getInt(PREFS_KEY_NUM_BUTTONS, 7);
        mPlayButton = (Button) findViewById(R.id.play_button);
        mPlayButton.setText(getString(R.string.play_format, mNumButtons));
        mPlayButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MenuActivity.this, LightsOutGameActivity.class);

                gameIntent.putExtra(LightsOutGameActivity.NUM_BUTTONS_KEY, mNumButtons);

                startActivity(gameIntent);
            }
        });

        ((Button) findViewById(R.id.change_number_of_buttons_button))
                .setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent numButtonsIntent = new Intent(MenuActivity.this,
                                ChangeNumberOfButtonsActivity.class);
                        numButtonsIntent.putExtra(ChangeNumberOfButtonsActivity.NUM_BUTTONS_KEY, 7);
                        startActivityForResult(numButtonsIntent,
                                ChangeNumberOfButtonsActivity.REQUEST_CODE);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ChangeNumberOfButtonsActivity.REQUEST_CODE) {
            mNumButtons = data.getIntExtra(ChangeNumberOfButtonsActivity.NUM_BUTTONS_KEY, 7);
            mPlayButton.setText(getString(R.string.play_format, mNumButtons));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_NUM_BUTTONS, mNumButtons);
        editor.commit();
    }
}