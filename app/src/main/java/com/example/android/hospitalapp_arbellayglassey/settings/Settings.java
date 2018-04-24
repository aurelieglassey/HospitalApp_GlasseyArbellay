package com.example.android.hospitalapp_arbellayglassey.settings;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.hospitalapp_arbellayglassey.R;

public class Settings extends AppCompatActivity {


    //buttons of the the settings
    private Button btnAbout;
    private Button btnLanguage;
    private Button btnTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //When the user press on "Language"
        pressBtnLanguage();

        //When the user press on "About"
        pressBtnAbout();

        //When the user press on "Background
        pressBtnBackground();

    }

    //When the user press on "About"
    //The button direct the user to the AboutActivity
    public void pressBtnAbout(){

        //find the button by his id
        btnAbout = (Button) findViewById(R.id.btnSettingsAbout);

        //Clik listener to go to the SettingsAbout
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SettingsAbout.class);
                Settings.this.startActivity(intent);
            }
        });

    }

    //When the user press on "Language"
    //The button direct the user to the LanguageActivity
    public void pressBtnLanguage(){

        //find the button by his id
        btnLanguage = (Button) findViewById(R.id.btnSettingsLanguage);

        //Clik listener to go to the SettingsLanguage
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SettingsLanguage.class);
                Settings.this.startActivity(intent);
            }
        });

    }

    //When the user press on "Background"
    //The button direct the user to the BackgroundActivity
    public void pressBtnBackground(){

        //find the button by his id
        btnTheme = (Button) findViewById(R.id.btnSettingsTheme);

        //Clik listener to go to the SettingsBackground
        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SettingsTheme.class);
                Settings.this.startActivity(intent);
            }
        });

    }




}
