package edu.rosehulman.linearlightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class ChangeNumberOfButtonsActivity extends Activity implements OnClickListener {

    public static final String NUM_BUTTONS_KEY = "num_buttons";
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_number_of_buttons_activity);
        
        RadioButton radio3 = (RadioButton) findViewById(R.id.radio_3);
        RadioButton radio5 = (RadioButton) findViewById(R.id.radio_5);
        RadioButton radio7 = (RadioButton) findViewById(R.id.radio_7);
        RadioButton radio9 = (RadioButton) findViewById(R.id.radio_9);
        
        radio3.setOnClickListener(this);
        radio5.setOnClickListener(this);
        radio7.setOnClickListener(this);
        radio9.setOnClickListener(this);
        
        int selected = getIntent().getIntExtra(NUM_BUTTONS_KEY, 0);
        
        switch (selected) {
        case 3:
            radio3.setChecked(true);
            break;
        case 5:
            radio5.setChecked(true);
            break;
        case 7:
            radio7.setChecked(true);
            break;
        case 9:
            radio9.setChecked(true);
            break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        case R.id.radio_3:
            finishWithResult(3);
            return;

        case R.id.radio_5:
            finishWithResult(5);
            return;

        case R.id.radio_7:
            finishWithResult(7);
            return;

        case R.id.radio_9:
            finishWithResult(9);
            return;
        }
    }

    private void finishWithResult(int numButtons) {
        Intent result = new Intent();
        result.putExtra(NUM_BUTTONS_KEY, numButtons);
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
