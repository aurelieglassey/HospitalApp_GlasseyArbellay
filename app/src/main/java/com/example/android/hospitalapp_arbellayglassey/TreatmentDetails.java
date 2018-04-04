package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TreatmentDetails extends AppCompatActivity {


    //Button to add a medecine to a treatment
    private Button btnAddMedecineToTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);


        //Find the id view for the button add a medecine to a treatment
        btnAddMedecineToTreatment = (Button) findViewById(R.id.btn_show_treatment);

        //Add a listener to access to the medecine add search activity
        btnAddMedecineToTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentDetails.this, MedecineAddSearchList.class);
                TreatmentDetails.this.startActivity(intent);
            }
        });



        // adding list
    }
}
