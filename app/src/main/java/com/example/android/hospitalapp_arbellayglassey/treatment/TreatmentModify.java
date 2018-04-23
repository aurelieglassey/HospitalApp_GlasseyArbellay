package com.example.android.hospitalapp_arbellayglassey.treatment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncGetPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment.AsyncGetTreatment;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment.AsyncUpdateTreatment;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientModify;

import java.util.concurrent.ExecutionException;

public class TreatmentModify extends AppCompatActivity {

    //modification traitement ok
    private Button btnModifyTreatmentOk;
    private TreatmentEntity treatmentEntity;
    private PatientEntity patientEntity;
    private int idTreatment;
    private  int idPatient;
    private TextView textViewAdmission;
    private EditText editTextName;
    private EditText editTextQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_modify);

        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBtnModifyTreatment();

        textViewAdmission = findViewById(R.id.AdmissionTreatmentModify);
        editTextName = findViewById(R.id.nameTreatmentModify);
        editTextQuantity = findViewById(R.id.quantityTreatmentModify);

        textViewAdmission.setText(patientEntity.getReasonAdmission());
        editTextName.setText(treatmentEntity.getName());
        editTextQuantity.setText(String.valueOf(treatmentEntity.getMaxQuantity()));

    }

    //When the user want to apply the changes he has made for the treatment
    public void pressBtnModifyTreatment(){

        btnModifyTreatmentOk = (Button) findViewById(R.id.btn_modify_treatment);

        btnModifyTreatmentOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                treatmentEntity.setName(editTextName.getText().toString());
                treatmentEntity.setMaxQuantity(Integer.parseInt(editTextQuantity.getText().toString()));

                new AsyncUpdateTreatment(TreatmentModify.this).execute(treatmentEntity);
                finish();
                //Intent intent = new Intent(TreatmentModify.this, TreatmentDetails.class);
                //TreatmentModify.this.startActivity(intent);
            }
        });
    }

    //Read the db and get the treatement
    public void readDB() throws ExecutionException, InterruptedException {

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(TreatmentModify.this);
        Intent intentGetId = getIntent();
        
        idTreatment = intentGetId.getIntExtra("idT", 0);
        treatmentEntity = new AsyncGetTreatment(TreatmentModify.this, idTreatment).execute().get();

        idPatient = intentGetId.getIntExtra("idP", 0);
        patientEntity = new AsyncGetPatient(TreatmentModify.this, idPatient).execute().get();


    }



}
