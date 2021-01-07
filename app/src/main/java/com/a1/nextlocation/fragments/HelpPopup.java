package com.a1.nextlocation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.a1.nextlocation.R;

public class HelpPopup extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.help_popup, container, false);
        getDialog().setTitle("Simple Dialog");
        Button okButton = rootView.findViewById(R.id.help_ok_button);
        okButton.setOnClickListener(v -> dismiss());
        return rootView;
    }
}
