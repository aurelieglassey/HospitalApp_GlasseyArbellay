package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientAdd extends AppCompatActivity {


    //button ok
    private Button okAddPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add);


        //Find the id view for the button ok to add a patient
        okAddPatient = (Button) findViewById(R.id.btn_ok_add_patient);

        //Add a listener to access to the List of patient activity
        okAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientAdd.this, ListOfPatientActivity.class);
                PatientAdd.this.startActivity(intent);
            }
        });
    }
}
