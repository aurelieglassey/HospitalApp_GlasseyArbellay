package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class TreatmentDetails extends AppCompatActivity {


    //Button to add a medecine to a treatment
    private Button btnAddMedecineToTreatment;
    //Button to modify a treatment
    private ImageButton btnModifyTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);

        //Add a medecine to the treatment of a patient
        addMedecineToTreatment();
        pressBtnModifyTreatment();

        // adding list
        final ArrayList<String> medecine = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.medecine_array)));
        ListView list;


        Intent intent = new Intent(TreatmentDetails.this, MedecineDetails.class);
        list = (ListView) findViewById(R.id.listofmedicinefortreatment);
        list.setAdapter(new ListViewWithDelBtnAdapter(medecine, TreatmentDetails.this, intent, R.layout.listofmedicinefortreatment_layout, R.id.listview_listofmedecinefortreatment, R.id.deleteMedecineForTreatmentButton));
    }


    //When the user decide to add medecine to a treatment
    public void addMedecineToTreatment(){
        //Find the id view for the button add a medecine to a treatment
        btnAddMedecineToTreatment = (Button) findViewById(R.id.btn_add_medicine);

        //Add a listener to access to the medecine add search activity
        btnAddMedecineToTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentDetails.this, MedecineAddSearchList.class);
                TreatmentDetails.this.startActivity(intent);
            }
        });
    }

    //When the user decide to modify the treatment of a patient
    public void pressBtnModifyTreatment(){

        //Find the id view for the button modify the treatment
        btnModifyTreatment = (ImageButton) findViewById(R.id.btn_modify_treatment);

        //Add a listener to modify the treatment of a patient
        btnModifyTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentDetails.this, TreatmentModify.class);
                TreatmentDetails.this.startActivity(intent);
            }
        });

    }

}
