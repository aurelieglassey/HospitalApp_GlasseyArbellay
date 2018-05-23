package com.example.android.hospitalapp_arbellayglassey.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.hospitalapp_arbellayglassey.MainActivity;
import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;

import java.util.Locale;

public class SettingsLanguage extends AppCompatActivity implements View.OnClickListener {

    private String titleLanguage = "";
    private Locale myLocale;
    private Button btnEnglish, btnFrench;
    private DrawerLayout mDrawerLayout;


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

    // it change the langue
    public void changeLang(String lang)
    {
        //ignorecase
        if (lang.equalsIgnoreCase(""))
            return;
        //set locale
        myLocale = new Locale(lang);
        //save
        saveLocale(lang);

        //set the new configurtion of the language
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        // update the change
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    //Save the language to locale
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

    // on click listener that will launh the main activity and set the string language
    public void onClick(View v) {
        String lang = "";
        switch (v.getId()) {
            case R.id.btnSettingsLanguageEnglish:
                lang = "en";
                Toast.makeText(SettingsLanguage.this, " Language : English ", Toast.LENGTH_LONG).show();
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;


            case R.id.btnSettingsLanguageFrench:
                lang = "fr";
                Toast.makeText(SettingsLanguage.this, " Langue : Français ", Toast.LENGTH_LONG).show();
                Intent i2 = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i2);
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
        setupNavBar();
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
    //setup navigation drawer
    //this method setup the navigation drawer and implement the button to go to the list
    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout_settings_language);

        NavigationView navigationView = findViewById(R.id.nav_view_settings_language);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(SettingsLanguage.this, ListOfPatientActivity.class);
                                SettingsLanguage.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(SettingsLanguage.this, ListOfMedecineActivity.class);
                                SettingsLanguage.this.startActivity(intentMed);
                                finish();
                                break;
                            default:
                                break;
                        }
                        mDrawerLayout.closeDrawers();


                        return true;
                    }
                });
    }


}
