package com.example.android.hospitalapp_arbellayglassey.settings;

import android.app.DialogFragment;
import android.content.Intent;
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

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientModify;

public class Settings extends AppCompatActivity {


    //buttons of the the settings
    private Button btnAbout;
    private Button btnLanguage;
    private Button btnTheme;
    private DrawerLayout mDrawerLayout;

    //Title of the settings
    private String titleSettings = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        titleSettings = this.getString(R.string.title_settings);

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
                Toast.makeText(Settings.this, "Sorry, but this activity is not implemented ( ͡☉ ͜ʖ ͡☉) ", Toast.LENGTH_LONG).show();;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(titleSettings);
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

            finish();


        return true;
    }

    //setup navigation drawer
    //this method setup the navigation drawer and implement the button to go to the list
    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout_settings);

        NavigationView navigationView = findViewById(R.id.nav_view_settings);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(Settings.this, ListOfPatientActivity.class);
                                Settings.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(Settings.this, ListOfMedecineActivity.class);
                                Settings.this.startActivity(intentMed);
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
