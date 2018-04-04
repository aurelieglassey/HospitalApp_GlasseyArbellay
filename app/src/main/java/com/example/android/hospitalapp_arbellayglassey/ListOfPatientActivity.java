package com.example.android.hospitalapp_arbellayglassey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfPatientActivity extends AppCompatActivity {

    //Button add a new patient
    private Button btnNewPatient;
    //pussh

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patient);


        final String [] patient = getResources().getStringArray(R.array.patient_array);
        ListView list;

        btnNewPatient = (Button) findViewById(R.id.btn_add_patient);
        btnNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfPatientActivity.this, PatientAdd.class);
                ListOfPatientActivity.this.startActivity(intent);
            }
        });

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
                ImageButton imageButton1 = (ImageButton) view.findViewById(R.id.deletePatientButton);
                textView1.setText(patient[position]);

                return view;
            }
        };

        list = (ListView) findViewById(R.id.listofpatient);
        list.setAdapter(adapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListOfPatientActivity.this, "Patient selected: "+ patient[position], Toast.LENGTH_LONG).show();
            }
        });



                //Intent intent = new Intent(ListOfPatientActivity.this, PatientDetails.class);
                //ListOfPatientActivity.this.startActivity(intent);







    }



    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
