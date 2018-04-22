package com.example.android.hospitalapp_arbellayglassey.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncAddPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;

import java.util.concurrent.ExecutionException;

public class PatientAdd extends AppCompatActivity {


    //button ok
    private Button okAddPatient;
    private PatientEntity patientEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add);

        //Button to confirm that we want to add this new patient
        pressOkAddPatient();


    }

    //When the user decide to add the patient to the list he press on ok
    public void pressOkAddPatient(){
        //Find the id view for the button ok to add a patient
        okAddPatient = (Button) findViewById(R.id.btn_ok_add_patient);

        //Add a listener to access to the List of patient activity
        okAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get all id of the texte view
                EditText namePatient = (EditText)findViewById(R.id.namePatientDetails);
                EditText agePatient = (EditText)findViewById(R.id.agePatientAdd);
                EditText genderPatient = (EditText)findViewById(R.id.genderPatientAdd);
                EditText roomPatient = (EditText)findViewById(R.id.ageRoomPatientAdd);
                EditText bloodPatient = (EditText)findViewById(R.id.bloodGroupPatientAdd);
                EditText admissionPatient = (EditText)findViewById(R.id.admissionPatientAdd);
                Intent intent = new Intent(PatientAdd.this, ListOfPatientActivity.class);

                patientEntity = new PatientEntity();
                patientEntity.setName(namePatient.getText().toString());
                patientEntity.setAge(Integer.parseInt(agePatient.getText().toString()));
                patientEntity.setGender(genderPatient.getText().charAt(0));
                patientEntity.setRoomNumber(Integer.parseInt(roomPatient.getText().toString()));
                patientEntity.setBloodGroup(bloodPatient.getText().toString());
                patientEntity.setReasonAdmission(admissionPatient.getText().toString());

                addPatient(patientEntity);
                PatientAdd.this.startActivity(intent);
            }
        });
    }


    public void addPatient(PatientEntity patientEntity){

        try {
            Long id = new AsyncAddPatient(PatientAdd.this, patientEntity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

