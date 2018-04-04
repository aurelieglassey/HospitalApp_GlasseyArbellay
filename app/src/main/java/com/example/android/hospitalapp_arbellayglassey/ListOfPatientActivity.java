package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;

public class ListOfPatientActivity extends AppCompatActivity {

    //Button add a new patient
    private Button btnNewPatient;
    //pussh

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patient);

        //Button to add a new patient for the list
        pressBtnNewPatient();

        final ArrayList<String> patient = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.patient_array)));
        ListView list;


        Intent intent = new Intent(ListOfPatientActivity.this, PatientDetails.class);
        list = (ListView) findViewById(R.id.listofpatient);
        list.setAdapter(new ListViewWithDelBtnAdapter(patient, ListOfPatientActivity.this, intent, R.layout.listofpatient_laylout, R.id.listview_listofpatient, R.id.deletePatientButton));



    }


    //When the user decide to add a patient
    public void pressBtnNewPatient(){
        btnNewPatient = (Button) findViewById(R.id.btn_add_patient);
        btnNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfPatientActivity.this, PatientAdd.class);
                ListOfPatientActivity.this.startActivity(intent);
            }
        });
    }



    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
