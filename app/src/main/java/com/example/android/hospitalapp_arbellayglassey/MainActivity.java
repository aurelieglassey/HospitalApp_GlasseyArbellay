package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;

public class MainActivity extends AppCompatActivity {
    private Button btnListOfPatient;
    private Button btnListOfMedecine;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
    public void pressBtnListOfMedecine(){
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
    public void pressBtnListOfPatient(){
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
    public void pressBtnSettings(){
        btnSettings = (Button) findViewById(R.id.btn_mainActivity_Settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                MainActivity.this.startActivity(intent);
            }
        });




    }

}
