package com.example.mycalculator;


/*

  @author Maxo Khubulovi
 * @version dated May 6, 2023
 */

import static com.example.mycalculator.Settings.KEY_THEME;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_EQUALS = "result";

    private SharedPreferences sharedPreferences;

    String oldNumber;
    String operator;
    Boolean isNew = true;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);

        sharedPreferences = getSharedPreferences(KEY_THEME, MODE_PRIVATE);


        MaterialButton settings = findViewById(R.id.settings);
        checkNightModeActivated();
        settings.setOnClickListener((view -> {
            Intent runSettings = new Intent(MainActivity.this, Settings.class);
            startActivity(runSettings);
        }));
    }

    @SuppressLint("NonConstantResourceId")
    public void clickNumber(View view) {
        if (isNew)
            result.setText("");
        isNew = false;
        String number = result.getText().toString();
        switch (view.getId()) {
            case R.id.button1:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "1";
                }
                break;
            case R.id.button7:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "7";
                }
                break;
            case R.id.button8:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "8";
                }
                break;
            case R.id.button9:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "9";
                }
                break;
            case R.id.button6:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "6";
                }
                break;
            case R.id.button5:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "5";
                }
                break;
            case R.id.button4:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "4";
                }
                break;
            case R.id.button2:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "2";
                }
                break;
            case R.id.button3:
                if (isZero(number)&& number.length() == 1) {
                    number = number.substring(1);
                } else {
                    number = number + "3";
                }
                break;
            case R.id.button0:
                if (isZero(number) && number.length() == 1) {
                    number = "0";
                } else {
                    number = number + "0";
                }
                break;
            case R.id.button:
                if (dotIsPresent(number)) {
                    number = "0";
                }
                if (isZero(number)) {
                    number = "0.";
                } else {
                    number = number + ".";
                }
                break;
            case R.id.positiv_unpositiv:
                if (isZero(number)) {
                    number = "0";
                } else {
                    if (dotIsUnpossitive(number)) {
                        number = number.substring(1);
                    } else {
                        number = "-" + number;
                    }
                }
                break;
        }
        result.setText(number);
    }

    private boolean dotIsUnpossitive(String number) {
        return number.charAt(0) == '-';
    }

    private boolean dotIsPresent(String number) {
        return number.contains(".");
    }

    private boolean isZero(String number) {
        return number.equals("0") || number.isEmpty();
    }

    @SuppressLint("NonConstantResourceId")
    public void operations(View view) {
        isNew = true;
        oldNumber = result.getText().toString();
        switch (view.getId()) {

            case R.id.minus:
                operator = "-";
                break;
            case R.id.multiply:
                operator = "*";
                break;
            case R.id.plus:
                operator = "+";
                break;
            case R.id.devide:
                operator = "/";
                break;
            case R.id.procents:
                operator = "%";
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void equels(View view) {
        String newNumber = result.getText().toString();
        float answer = 0.0F;
        switch (operator) {

            case "-":
                answer = Float.parseFloat(oldNumber) - Float.parseFloat(newNumber);
                break;
            case "+":
                answer = Float.parseFloat(oldNumber) + Float.parseFloat(newNumber);
                break;
            case "/":
                answer = Float.parseFloat(oldNumber) / Float.parseFloat(newNumber);
                break;
            case "*":
                answer = Float.parseFloat(oldNumber) * Float.parseFloat(newNumber);
                break;
        }
        result.setText(answer + "");
    }

    public void delete(View view) {
        result.setText("0");
        isNew = true;
    }

    public void procents(View view) {
        String number = result.getText().toString();
        float answer = Float.parseFloat(number) / 100;
        number = answer + "";
        result.setText(number);
        operator = "";
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        result.setText(saveInstanceState.getString(KEY_EQUALS));
        makeToast("RestoreIstanceState");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putString(KEY_EQUALS, result.getText().toString());
        makeToast("SaveInstanceState");
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(KEY_THEME, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}