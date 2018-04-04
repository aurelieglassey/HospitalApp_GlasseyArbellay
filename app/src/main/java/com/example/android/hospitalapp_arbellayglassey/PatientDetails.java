package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientDetails extends AppCompatActivity {

    //Button to show the treatment
    private Button btnShowTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        //Press on the button Show Treatment
        pressBtnShowTreatment();


    }

    //When the user decide to display the treatment of a patient
    public void pressBtnShowTreatment(){
        //Find the id view for the button show the treatement of a patient
        btnShowTreatment = (Button) findViewById(R.id.btn_show_treatment);

        //Add a listener to access to the  Treatment details activity
        btnShowTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientDetails.this, TreatmentDetails.class);
                PatientDetails.this.startActivity(intent);
            }
        });

    }
}
