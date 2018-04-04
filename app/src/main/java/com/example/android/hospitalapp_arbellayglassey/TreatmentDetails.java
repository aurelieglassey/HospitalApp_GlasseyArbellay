package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TreatmentDetails extends AppCompatActivity {


    //Button to add a medecine to a treatment
    private Button btnAddMedecineToTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);

        //Add a medecine to the treatment of a patient
        addMedecineToTreatment();


        // adding list
        final String [] medicineForTreatment = getResources().getStringArray(R.array.medecine_array);
        ListView list;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listofmedicinefortreatment_layout, medicineForTreatment) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                //If View doesn't exist create a new view
                if (convertView == null) {
                    // Create the Layout
                    LayoutInflater inflater = getLayoutInflater();
                    view = inflater.inflate(R.layout.listofmedicinefortreatment_layout, parent, false);
                } else {
                    view = convertView;
                }

                //Add Text to the layout
                TextView textView1 = (TextView) view.findViewById(R.id.listview_listofmedecinefortreatment);
                textView1.setText(medicineForTreatment[position]);

                return view;
            }
        };

        list = (ListView) findViewById(R.id.listofmedicinefortreatment);
        list.setAdapter(adapter);

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

}
