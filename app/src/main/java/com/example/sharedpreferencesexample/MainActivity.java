package com.example.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button apply, save;
    private TextView textView;
    private EditText editText;
    private Switch aSwitch;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadData();
        updateViews();
    }

    public void initViews() {
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        apply = findViewById(R.id.applyTextButton);
        save = findViewById(R.id.saveButton);
        aSwitch = findViewById(R.id.switch1);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, textView.getText().toString());
        editor.putBoolean(SWITCH1, aSwitch.isChecked());
        editor.apply();

        Toast.makeText(this, "Data saved correctly", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        switchState = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews() {
        textView.setText(text);
        aSwitch.setChecked(switchState);
    }
}
