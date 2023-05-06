package com.example.mycalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;

public class Settings extends AppCompatActivity {
    protected static final String KEY_THEME = "keyTheme";
    private SharedPreferences sharedPreferences;
    private RadioButton nightMode;
    private RadioButton nightModeOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(KEY_THEME, MODE_PRIVATE);

        MaterialButton back = findViewById(R.id.back);
        nightMode = findViewById(R.id.nightTheme);
        nightModeOff = findViewById(R.id.nightThemeOff);

        checkTheme();

        back.setOnClickListener(view -> finish());

        nightMode.setOnClickListener(view -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            saveThemeState(true);
        });

        nightModeOff.setOnClickListener(view -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            saveThemeState(false);
        });
    }

    private void saveThemeState(boolean DarkTheme) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_THEME, DarkTheme).apply();
    }

    public void checkTheme() {
        if (sharedPreferences.getBoolean(KEY_THEME, false)) {
            nightMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            nightModeOff.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}