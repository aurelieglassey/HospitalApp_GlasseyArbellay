package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedecineAdd extends AppCompatActivity {

    //Button to add a new Medecine
    private Button btnAddNewMedecine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add);

        //Button add new medecine
        pressBtnAddNewMedecine();
    }

    //When the user decide to add a new medecine
    public void pressBtnAddNewMedecine(){
        btnAddNewMedecine = (Button) findViewById(R.id.buttonOKNewMedecine);
        btnAddNewMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedecineAdd.this, ListOfMedecineActivity.class);
                MedecineAdd.this.startActivity(intent);
            }
        });
    }
}
