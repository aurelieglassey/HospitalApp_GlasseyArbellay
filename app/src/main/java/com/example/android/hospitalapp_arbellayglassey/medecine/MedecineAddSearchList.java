package com.example.android.hospitalapp_arbellayglassey.medecine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.treatment.TreatmentDetails;
import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithAddBtnAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MedecineAddSearchList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add_search_list);

        // adding list
        final ArrayList<String> medecine = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.medecine_array)));
        ListView list;


        Intent intent = new Intent(MedecineAddSearchList.this, TreatmentDetails.class);
        list = (ListView) findViewById(R.id.listofmedecinesearchlist);
        list.setAdapter(new ListViewWithAddBtnAdapter(medecine, MedecineAddSearchList.this, intent, R.layout.listmedecineaddsearchlist_layout, R.id.listview_listofmedecineaddsearchlist, R.id.addMedecineForTreatmentButton));


    }



}
