package com.a1.nextlocation.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.a1.nextlocation.MainActivity;
import com.a1.nextlocation.R;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    private SharedPreferences.Editor editor;
    private SwitchCompat fontSwitch;
    private SwitchCompat imperialSwitch;
    private SwitchCompat colorBlindMode;
    private Refreshable refreshable;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        refreshable = (Refreshable) context;
    }

    @SuppressLint("CommitPrefEdits")
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
        initializeButtons(view);

        return view;
    }

    private void initializeButtons(View view) {
        //Initialises back button
        ImageView backButton = view.findViewById(R.id.settings_back_button);
        backButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE);

        //Initialises imperial switchCompat
        this.imperialSwitch = view.findViewById(R.id.imperial_button);
        this.imperialSwitch.setChecked(sharedPreferences.getBoolean("imperialSwitch", false));

        this.imperialSwitch.setOnClickListener(view1 -> {
            editor.putBoolean("imperialSwitch", imperialSwitch.isChecked());
            editor.apply();
            editor.commit();
        });

        //Initialises 65+ switchCompat
        this.fontSwitch = view.findViewById(R.id.font_changer);

        fontSwitch.setChecked(sharedPreferences.getBoolean("fontSwitch", false));

        //Initial check to see what setting was last chosen
        if (fontSwitch.isChecked()){
            requireActivity().setTheme(R.style.Theme_NextLocationBig);
        }else if (!fontSwitch.isChecked()){
            requireActivity().setTheme(R.style.Theme_NextLocation);
        }

        //Changes the font settings depending on the state of the toggle

        fontSwitch.setOnClickListener(view1 -> {
            if(fontSwitch.isChecked())
            {
                requireActivity().setTheme(R.style.Theme_NextLocationBig);
            }
            if(!fontSwitch.isChecked())
            {
                requireActivity().setTheme(R.style.Theme_NextLocation);;
            }
            editor.putBoolean("fontSwitch",fontSwitch.isChecked());
            editor.apply();
            editor.commit();
        });

        this.colorBlindMode = view.findViewById(R.id.colourblindSwitch);
        this.colorBlindMode.setOnClickListener(view1 -> {
            editor.putBoolean("colorBlindModeSwitch", imperialSwitch.isChecked());
            editor.apply();
            editor.commit();

            if (colorBlindMode.isChecked()){
                requireActivity().setTheme(R.style.Theme_NextLocation);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                getActivity().recreate();
                System.out.println("AAN");
            }else if (!colorBlindMode.isChecked()){
                requireActivity().setTheme(R.style.Theme_NextLocation);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                getActivity().recreate();
                System.out.println("UIT");
            }

        });
    }

    private void initializeLanguageDropdown(View view) {
        Spinner languageDropdown = view.findViewById(R.id.dropdown_menu_Settings);
        String[] items = new String[]{getResources().getString(R.string.Dutch), getResources().getString(R.string.English)};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        languageDropdown.setAdapter(arrayAdapter);

        // set the language dropdown on the currently selected language stored in the sharedPreferences
        languageDropdown.setSelection(languageToDropdownPosition(getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("Language", "nl")));

        long previousID = languageDropdown.getSelectedItemId();
        languageDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLocale(dropdownPositionToLanguage(id));
                // refresh fragment on language change
                if (id != previousID) {
                    refresh();
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

        refreshable.refreshAndNavigateTo(R.id.settings);
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