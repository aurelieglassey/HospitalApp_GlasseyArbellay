package com.example.android.hospitalapp_arbellayglassey.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.hospitalapp_arbellayglassey.R;

import java.util.Locale;

public class SettingsLanguage extends AppCompatActivity implements View.OnClickListener {

    private String titleLanguage = "";
    private Locale myLocale;
    private Button btnEnglish, btnFrench;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_language);

        titleLanguage = this.getString(R.string.title_settings_language);

        this.btnEnglish = (Button) findViewById(R.id.btnSettingsLanguageEnglish);
        this.btnFrench = (Button) findViewById(R.id.btnSettingsLanguageFrench);

        this.btnEnglish.setOnClickListener(this);
        this.btnFrench.setOnClickListener(this);


        loadLocale();

    }


    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

    public void onClick(View v) {
        String lang = "";
        switch (v.getId()) {
            case R.id.btnSettingsLanguageEnglish:
                lang = "en";
                Toast.makeText(SettingsLanguage.this, " Language : English ", Toast.LENGTH_LONG).show();
                finish();

            case R.id.btnSettingsLanguageFrench:
                lang = "fr-rCH";
                Toast.makeText(SettingsLanguage.this, " Langue : Français ", Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                break;
        }
        changeLang(lang);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(titleLanguage);
        setupActionBar();
        return true;

    }
    private void setupActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        else{
            finish();
        }

        return true;
    }


}
