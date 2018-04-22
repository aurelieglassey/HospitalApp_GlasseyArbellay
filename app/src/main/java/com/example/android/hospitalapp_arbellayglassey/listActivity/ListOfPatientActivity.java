package com.example.android.hospitalapp_arbellayglassey.listActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithDelBtnAdapterPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.GetPatients;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientAdd;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientDetails;
import com.example.android.hospitalapp_arbellayglassey.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListOfPatientActivity extends AppCompatActivity {

    //Button add a new patient
    private Button btnNewPatient;
    private List<PatientEntity> patientEntities;

    ArrayList<String> patients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patient);

        //Button to add a new patient for the list
        pressBtnNewPatient();

        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListView list;


        Intent intent = new Intent(ListOfPatientActivity.this, PatientDetails.class);
        list = (ListView) findViewById(R.id.listofpatient);
        list.setAdapter(new ListViewWithDelBtnAdapterPatient(patients, patientEntities, ListOfPatientActivity.this, intent, R.layout.listofpatient_laylout, R.id.listview_listofpatient, R.id.deletePatientButton));



    }

    public void readDB() throws ExecutionException, InterruptedException {

        patients = new ArrayList<String>();

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(ListOfPatientActivity.this);

        

        patientEntities = new GetPatients(ListOfPatientActivity.this).execute().get();

        for (PatientEntity p : patientEntities){
           patients.add(p.getName());
        }


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

    //get the db and get the patients list
    private void initiateDB(){


    }
    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
