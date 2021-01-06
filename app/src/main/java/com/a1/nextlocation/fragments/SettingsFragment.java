package com.a1.nextlocation.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.a1.nextlocation.MainActivity;
import com.a1.nextlocation.R;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    private SharedPreferences.Editor editor;
    
    private ImageView imageButton;

    SwitchCompat fontChanger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editor = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initializeLanguageDropdown(view);

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

        return view;
    }

    private void initializeLanguageDropdown(View view) {
        Spinner languageDropdown = view.findViewById(R.id.dropdown_menu_Settings);
        String[] items = new String[]{"Nederlands", "Engels", "Chinees"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        languageDropdown.setAdapter(arrayAdapter);

        // set the language dropdown on the currently selected language stored in the sharedPreferences
        languageDropdown.setSelection(languageToDropdownPosition(getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("Language", "")));

        long previousID = languageDropdown.getSelectedItemId();
        languageDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLocale(dropdownPositionToLanguage(id));
                if (id != previousID) {
                    Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.detach(currentFragment);
                    fragmentTransaction.attach(currentFragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * converts the languageDropdown position to the belonging language
     *
     * @param id desired position to convert
     * @return the language belonging to the position of the languageDropdown
     */
    private String dropdownPositionToLanguage(long id) {
        switch ((int) id) {
            case 0:
                return "nl";
            case 1:
                return "en";
            default:
                return "";
        }
    }

    /**
     * converts language to the languageDropdown position
     *
     * @param language desired language to convert
     * @return the position of the language in the languageDropdown
     */
    private int languageToDropdownPosition(String language) {
        switch (language) {
            case "nl":
                return 0;
            case "en":
                return 1;
            default:
                return 1;
        }
    }

    /**
     * reloads the fragment
     */
    private void refresh() {
        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(currentFragment);
        fragmentTransaction.attach(currentFragment);
        fragmentTransaction.commit();
    }

    /**
     * changes the current language to the desired language and saves this setting in SharedPreferences
     *
     * @param language the desired language to translate to
     */
    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
        editor.putString("Language", language);
        editor.apply();
    }
}