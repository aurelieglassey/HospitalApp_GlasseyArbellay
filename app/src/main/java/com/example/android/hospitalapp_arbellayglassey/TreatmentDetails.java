package com.example.android.hospitalapp_arbellayglassey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TreatmentDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);


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



        // adding list
    }
}
