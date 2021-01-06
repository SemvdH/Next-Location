package com.a1.nextlocation.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.a1.nextlocation.MainActivity;
import com.a1.nextlocation.R;

public class SettingsFragment extends Fragment {

    private ImageView imageButton;

    SwitchCompat fontChanger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inflate the layout for this fragment
        Spinner dropdown = view.findViewById(R.id.dropdown_menu_Settings);

        this.imageButton = view.findViewById(R.id.settings_back_button);
        this.imageButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });


        fontChanger = view.findViewById(R.id.BigFont);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("com.a1.nextlocation",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        fontChanger.setChecked(sharedPreferences.getBoolean("switch", false));

        if (fontChanger.isChecked()){
            requireActivity().setTheme(R.style.Theme_NextLocationBig);
        }else if (!fontChanger.isChecked()){
            requireActivity().setTheme(R.style.Theme_NextLocation);
        }

        fontChanger.setOnClickListener(view1 -> {
            if(fontChanger.isChecked())
            {
                requireActivity().setTheme(R.style.Theme_NextLocationBig);
                editor.putBoolean("switch",true);
                editor.apply();
            }
            if(!fontChanger.isChecked())
            {
                requireActivity().setTheme(R.style.Theme_NextLocation);
                editor.putBoolean("switch",false);
                editor.apply();
            }
            editor.commit();
        });

        String[] items = new String[]{"Nederlands", "Engels", "Chinees"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(arrayAdapter);
    }
}