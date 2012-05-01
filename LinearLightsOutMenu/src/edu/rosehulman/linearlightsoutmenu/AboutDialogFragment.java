package edu.rosehulman.linearlightsoutmenu;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AboutDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.about));
        View result = inflater.inflate(R.layout.about_dialog_fragment, null);
        
        ((Button) result.findViewById(R.id.about_dialog_fragment_ok_button)).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return result;
    }
}
