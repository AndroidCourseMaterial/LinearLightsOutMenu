package edu.rosehulman.linearlightsoutmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LightsOutGameActivity extends Activity {

    public static final String NUM_BUTTONS_KEY = "num_buttons";

    private int mNumButtons;
    private LightsOutGame mGame;
    private List<Button> mButtons;
    private TextView mGameStateTextView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        this.mNumButtons = getIntent().getIntExtra(NUM_BUTTONS_KEY, 7);

        this.mGame = new LightsOutGame(this.mNumButtons);

        this.mButtons = new ArrayList<Button>();
        TableRow buttonRow = new TableRow(this);

        this.mGameStateTextView = (TextView) findViewById(R.id.game_state);

        for (int i = 0; i < this.mNumButtons; i++) {
            Button currentButton = new Button(this);
            currentButton.setTag(new Integer(i)); // Preparing for later
            this.mButtons.add(currentButton);
            buttonRow.addView(currentButton);
            currentButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    updateView(mGame.pressedButtonAtIndex((Integer) v.getTag()));
                }
            });
        }

        TableLayout buttonTable = (TableLayout) findViewById(R.id.button_table);
        buttonTable.addView(buttonRow);

        Button newGame = (Button) findViewById(R.id.new_game_button);
        newGame.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mGame = new LightsOutGame(mNumButtons);
                updateView(false);
            }
        });
        updateView(false);
    }

    private void updateView(boolean didWin) {
        for (int i = 0; i < this.mNumButtons; i++) {
            this.mButtons.get(i).setText("" + this.mGame.getValueAtIndex(i));
            this.mButtons.get(i).setEnabled(!didWin);
        }

        Resources r = this.getResources();

        String gameStateString;

        int numPresses = this.mGame.getNumPresses();

        if (didWin) {
            if (numPresses == 1) {
                gameStateString = r.getString(R.string.you_won_one_move);
            } else {
                gameStateString = r.getString(R.string.you_won_format, numPresses);
            }
        } else {
            if (numPresses == 0) {
                gameStateString = r.getString(R.string.game_start);
            } else if (numPresses == 1) {
                gameStateString = r.getString(R.string.game_one_move);
            } else {
                gameStateString = r.getString(R.string.game_format, numPresses);
            }
        }

        this.mGameStateTextView.setText(gameStateString);
    }
}