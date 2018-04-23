package com.example.android.hospitalapp_arbellayglassey.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncAddPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment.AsyncAddTreatment;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.treatment.TreatmentDetails;

import java.util.concurrent.ExecutionException;

public class PatientAdd extends AppCompatActivity {


    //button ok
    private Button okAddPatient;
    private PatientEntity patientEntity;
    private TreatmentEntity treatmentEntity;
    //private String messageError = this.getString(R.string.Error_fill_this_fields);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add);

        //Button to confirm that we want to add this new patient
        pressOkAddPatient();


    }

    //When the user want to add a new patient and the data are ok to be added
    //button ok
    public void pressOkAddPatient(){
        //Find the id view for the button ok to add a patient
        okAddPatient = (Button) findViewById(R.id.btn_ok_add_patient);

        //Add a listener to access to the List of patient activity
        okAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get all id of a patient of the texte view
                EditText namePatient = (EditText)findViewById(R.id.namePatientDetails);
                EditText agePatient = (EditText)findViewById(R.id.agePatientAdd);
                EditText genderPatient = (EditText)findViewById(R.id.genderPatientAdd);
                EditText roomPatient = (EditText)findViewById(R.id.ageRoomPatientAdd);
                EditText bloodPatient = (EditText)findViewById(R.id.bloodGroupPatientAdd);
                EditText admissionPatient = (EditText)findViewById(R.id.admissionPatientAdd);
                Intent intent = new Intent(PatientAdd.this, ListOfPatientActivity.class);

                //Add the data of the text view in the patient
                patientEntity = new PatientEntity();
                patientEntity.setName(namePatient.getText().toString());
                patientEntity.setAge(Integer.parseInt(agePatient.getText().toString()));
                patientEntity.setGender(genderPatient.getText().charAt(0));
                patientEntity.setRoomNumber(Integer.parseInt(roomPatient.getText().toString()));
                patientEntity.setBloodGroup(bloodPatient.getText().toString());
                patientEntity.setReasonAdmission(admissionPatient.getText().toString());


                  //Check if there is an error when the user fill the textview
                //or if the user doesn't fill anyting.
                int error = 0;
                if (namePatient.getText().toString().length() == 0  ){
                   //namePatient.setError(messageError);
                    namePatient.setError("Please enter a name");
                    namePatient.requestFocus();
                    error = 1;
                }

               /* if (agePatient.getText().toString().length() == 0){
                    agePatient.setError("Please enter a age");
                    agePatient.requestFocus();
                    error = 1;
                }
*/

               /*
                int s = Integer.parseInt(agePatient.getText().toString());
                if (s == 0){
                    agePatient.setError("Please enter a age");
                    agePatient.requestFocus();
                    error = 1;
                }
                */

/*
                String s = String.valueOf(agePatient.getText().toString());
                if (s.length()== 0){
                    agePatient.setError("Please enter a age");
                    agePatient.requestFocus();
                    error = 1;
                }

*/



                //if the error is 0, it means that the fields are correctly fill
                if (error == 0){

                    //Call the method add patient
                    addPatient(patientEntity);

                    //PatientAdd.this.startActivity(intent);
                    finish();
                }
            }


        });
    }


    //Add a patient with the class AsyncAddPatient
    public void addPatient(PatientEntity patientEntity){

        try {
            //Add the new patient in the db
            Long id = new AsyncAddPatient(PatientAdd.this, patientEntity).execute().get();

            //Add a treatment for the new patient.
            //Create a name just for the new patient
            String namePatient = patientEntity.getName().toString();
            String officialNameTreatment = " Treatment - " + namePatient;

            //create a treatment
            treatmentEntity = new TreatmentEntity(officialNameTreatment,  id.intValue());
            //Add the treatment in the db
            Long idTreatment = new AsyncAddTreatment(PatientAdd.this, treatmentEntity).execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }



}

