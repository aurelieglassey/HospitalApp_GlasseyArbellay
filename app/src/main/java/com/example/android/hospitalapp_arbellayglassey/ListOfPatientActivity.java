package com.example.android.hospitalapp_arbellayglassey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListOfPatientActivity extends AppCompatActivity {

    // test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patient);

        final String [] patient = getResources().getStringArray(R.array.patient_array);
        ListView list;

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, R.layout.listofpatient_laylout, patient) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                //If View doesn't exist create a new view
                if (convertView == null) {
                    // Create the Layout
                    LayoutInflater inflater = getLayoutInflater();
                    view = inflater.inflate(R.layout.listofpatient_laylout, parent, false);
                } else {
                    view = convertView;
                }

                //Add Text to the layout
                TextView textView1 = (TextView) view.findViewById(R.id.listview_listofpatient);
                textView1.setText(patient[position]);

                return view;
            }
        };

        list = (ListView) findViewById(R.id.listofpatient);
        list.setAdapter(adapter);





    }



    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
