package com.example.android.hospitalapp_arbellayglassey.listActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithDelBtnAdapter;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineAdd;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineDetails;
import com.example.android.hospitalapp_arbellayglassey.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ListOfMedecineActivity extends AppCompatActivity {

    //Button add a new medecine
    private Button btnAddNewMedecine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_medecine);

        //Button to add a new Medecine to the list of medecine
        pressBtnAddNewMedecine();


        final ArrayList<String> medecine = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.medecine_array)));
        ListView list;

        Intent intent = new Intent(ListOfMedecineActivity.this, MedecineDetails.class);
        list = (ListView) findViewById(R.id.listofmedecine);
        list.setAdapter(new ListViewWithDelBtnAdapter(medecine, ListOfMedecineActivity.this, intent, R.layout.listofmedecine_layout, R.id.listview_listofmedecine, R.id.deleteMedecineButton));


    }


    //When the user press on the button add new medecine
    public void pressBtnAddNewMedecine(){
        btnAddNewMedecine = (Button) findViewById(R.id.btn_add_medecine);
        btnAddNewMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfMedecineActivity.this, MedecineAdd.class);
                ListOfMedecineActivity.this.startActivity(intent);
            }
        });


    }


}
