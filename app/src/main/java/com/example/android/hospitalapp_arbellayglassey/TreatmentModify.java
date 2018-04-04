package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TreatmentModify extends AppCompatActivity {

    //modification traitement ok
    private Button btnModifyTreatmentOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_modify);
        pressBtnModifyTreatment();
    }


    public void pressBtnModifyTreatment(){

        btnModifyTreatmentOk = (Button) findViewById(R.id.btn_modify_treatment);


        btnModifyTreatmentOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentModify.this, TreatmentDetails.class);
                TreatmentModify.this.startActivity(intent);
            }
        });
    }
}
