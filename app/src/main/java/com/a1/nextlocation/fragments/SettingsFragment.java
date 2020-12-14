package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.a1.nextlocation.MainActivity;
import com.a1.nextlocation.R;

public class SettingsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getView();
        Spinner dropdown = view.findViewById(R.id.dropdown_menu_Settings);

        String[] items = new String[]{"Nederlands", "Engels", "Chinees"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(arrayAdapter);

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}