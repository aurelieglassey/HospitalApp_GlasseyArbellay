package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MedecineDetails extends AppCompatActivity {

    //Button modify the data of a Medecine
    private ImageButton btnModifyMedecine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_details);
        pressBtnModifyTreatment();
    }


    //When the user decide to modify the data of a medecine
    public void pressBtnModifyTreatment(){

        //Find the id view for the button modify the data of a medecine
        btnModifyMedecine = (ImageButton) findViewById(R.id.btn_modify_MedecineDetails);

        //Add a listener to modify the data of a medecine
        btnModifyMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedecineDetails.this, MedecineModify.class);
                MedecineDetails.this.startActivity(intent);
            }
        });

    }
}
