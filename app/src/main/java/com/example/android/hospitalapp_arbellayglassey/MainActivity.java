package com.example.android.hospitalapp_arbellayglassey;


import android.content.Intent;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button btnListOfPatient;
    private Button btnListOfMedecine;
    private Button btnSettings;
    private Button navBtnListOfPatient;
    private Button navBtnListOfMedecine;
    private DatabaseReference mDatabase;


    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());
//        databaseCreator.createDb(this.getApplication());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        setupNavBar();

        //The 3 Buttons of the main activity
        pressBtnListOfPatient();
        pressBtnListOfMedecine();
        pressBtnSettings();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;

    }


    //When the user press on the button list of medecine
    public void pressBtnListOfMedecine() {
        btnListOfMedecine = (Button) findViewById(R.id.btn_mainActivity_listMedecine);
        btnListOfMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOfMedecineActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    //When the user press on the button list of Patient
    public void pressBtnListOfPatient() {
        btnListOfPatient = (Button) findViewById(R.id.btn_mainActivity_listPatients);
        btnListOfPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOfPatientActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    //When the user press on the button list of Settings
    public void pressBtnSettings() {
        btnSettings = (Button) findViewById(R.id.btn_mainActivity_Settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    //setup navigation drawer

    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout_main_activity);

        NavigationView navigationView = findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(MainActivity.this, ListOfPatientActivity.class);
                                MainActivity.this.startActivity(intentPatient);
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(MainActivity.this, ListOfMedecineActivity.class);
                                MainActivity.this.startActivity(intentMed);
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
