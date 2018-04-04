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

import java.util.ArrayList;
import java.util.Arrays;

public class MedecineAddSearchList extends AppCompatActivity {

    //Button to add a new medecine
    private Button btnAddNewMedecine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add_search_list);

        pressBtnAddNewMedecine();

        // adding list
        final ArrayList<String> medecine = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.medecine_array)));
        ListView list;


        Intent intent = new Intent(MedecineAddSearchList.this, TreatmentDetails.class);
        list = (ListView) findViewById(R.id.listofmedecinesearchlist);
        list.setAdapter(new ListViewWithAddBtnAdapter(medecine, MedecineAddSearchList.this, intent, R.layout.listmedecineaddsearchlist_layout, R.id.listview_listofmedecineaddsearchlist, R.id.addMedecineForTreatmentButton));


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
