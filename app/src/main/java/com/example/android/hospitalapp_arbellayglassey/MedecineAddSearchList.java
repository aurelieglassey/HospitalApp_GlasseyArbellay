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

public class MedecineAddSearchList extends AppCompatActivity {

    //Button to add a new medecine
    private Button btnAddNewMedecine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add_search_list);

        pressBtnAddNewMedecine();

        //Create a list of string with medecine with the content of the medecine array
        final String [] medecineAddSearchList = getResources().getStringArray(R.array.medecine_array);
        ListView list;

        //Create an ArrayAdapter to adapte in a view the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listmedecineaddsearchlist_layout, medecineAddSearchList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                //If View doesn't exist create a new view
                if (convertView == null) {
                    // Create the Layout
                    LayoutInflater inflater = getLayoutInflater();
                    view = inflater.inflate(R.layout.listmedecineaddsearchlist_layout, parent, false);
                } else {
                    view = convertView;
                }

                //Add Text to the layout
                TextView textView1 = (TextView) view.findViewById(R.id.listview_listofmedecinefortreatment);
                textView1.setText(medecineAddSearchList[position]);

                return view;
            }
        };

        //Find the view where we want to display the data
        list = (ListView) findViewById(R.id.listofmedecinesearchlist);
        list.setAdapter(adapter);



    }


    public void pressBtnAddNewMedecine(){

        //Find the id view for the button add a new medecine
        btnAddNewMedecine = (Button) findViewById(R.id.btn_add_new_medicine);

        //Add a listener to access to new medecine activity
        btnAddNewMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedecineAddSearchList.this, MedecineAdd.class);
                MedecineAddSearchList.this.startActivity(intent);
            }
        });
    }
}
