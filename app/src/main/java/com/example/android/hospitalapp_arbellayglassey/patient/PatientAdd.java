package com.example.android.hospitalapp_arbellayglassey.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AddPatient;
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

              /*Put the text in the intent
                intent.putExtra("name", namePatient.getText().toString());
                intent.putExtra("age", agePatient.getText().toString());
                intent.putExtra("gender", genderPatient.getText().toString());
                intent.putExtra("room", roomPatient.getText().toString());
                intent.putExtra("blood", bloodPatient.getText().toString());
                intent.putExtra("admission", admissionPatient.getText().toString());
*/
                String name = namePatient.getText().toString();
                int age = Integer.parseInt(agePatient.getText().toString());
                char gender = genderPatient.getText().charAt(0);
//               int room = Integer.parseInt(roomPatient.getText().toString());
                String blood = bloodPatient.getText().toString();
                String admission = admissionPatient.getText().toString();

                patientEntity = new PatientEntity();
                patientEntity.setName(name);
                patientEntity.setAge(age);
                patientEntity.setGender(gender);
               // patientEntity.setRoomNumber(room);
                patientEntity.setBloodGroup(blood);
                patientEntity.setReasonAdmission(admission);

                addPatient(patientEntity);
                PatientAdd.this.startActivity(intent);
            }
        });
    }


    public void addPatient(PatientEntity patientEntity){

        try {
            Long id = new AddPatient(PatientAdd.this, patientEntity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

