package com.example.android.hospitalapp_arbellayglassey.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncGetPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncUpdatePatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.util.concurrent.ExecutionException;


public class PatientModify extends AppCompatActivity {


    private Button btnModifyPatient;

    private PatientEntity patientEntity;
    private int idPatient;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextGender;
    private EditText editTextRoom;
    private EditText editTextBlood;
    private EditText editTextAdmission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_modify);


        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBtnModifyPatient();

        editTextName = findViewById(R.id.namePatientModify);
        editTextAge = findViewById(R.id.agePatientModify);
        editTextGender = findViewById(R.id.genderPatientModify);
        editTextRoom = findViewById(R.id.ageRoomPatientModify);
        editTextBlood = findViewById(R.id.bloodGroupPatientModify);
        editTextAdmission = findViewById(R.id.admissionPatientModify);

        editTextName.setText(patientEntity.getName());
        editTextAge.setText(String.valueOf(patientEntity.getAge()));
        editTextGender.setText(String.valueOf(patientEntity.getGender()));
        editTextRoom.setText(String.valueOf(patientEntity.getRoomNumber()));
        editTextBlood.setText(patientEntity.getBloodGroup());
        editTextAdmission.setText(patientEntity.getReasonAdmission());

    }


    public void readDB() throws ExecutionException, InterruptedException {

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(PatientModify.this);

        Intent intentGetId = getIntent();
        idPatient = intentGetId.getIntExtra("idP", 0);

        patientEntity = new AsyncGetPatient(PatientModify.this, idPatient).execute().get();


    }

   // When the user decide to modify the data of a patient
    public void pressBtnModifyPatient(){

        //Find the id view for the button modify the data of a patient
        btnModifyPatient = (Button) findViewById(R.id.btn_ok_modify_patient);

        //Add a listener to modify the data of a patient
        btnModifyPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientEntity.setName(editTextName.getText().toString());
                patientEntity.setAge(Integer.parseInt(editTextAge.getText().toString()));
                patientEntity.setGender(editTextGender.getText().charAt(0));
                patientEntity.setRoomNumber(Integer.parseInt(editTextRoom.getText().toString()));
                patientEntity.setBloodGroup(editTextBlood.getText().toString());
                patientEntity.setReasonAdmission(editTextAdmission.getText().toString());

                new AsyncUpdatePatient(PatientModify.this).execute(patientEntity);
                //Intent intent = new Intent(PatientModify.this, PatientDetails.class);
                //intent.putExtra("idP", patientEntity.getIdP());
                //PatientModify.this.startActivity(intent);
                finish();
            }
        });

    }
}

