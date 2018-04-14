package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PatientModify extends AppCompatActivity {


    private Button btnModifyPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_modify);
        pressBtnModifyPatient();
    }



   // When the user decide to modify the data of a patient
    public void pressBtnModifyPatient(){

        //Find the id view for the button modify the data of a patient
        btnModifyPatient = (Button) findViewById(R.id.btn_ok_modify_patient);

        //Add a listener to modify the data of a patient
        btnModifyPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientModify.this, PatientDetails.class);
                PatientModify.this.startActivity(intent);
            }
        });

    }
}

