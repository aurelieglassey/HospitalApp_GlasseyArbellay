package com.example.android.hospitalapp_arbellayglassey.listActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithDelBtnAdapterMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecines;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineAdd;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineDetails;
import com.example.android.hospitalapp_arbellayglassey.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListOfMedecineActivity extends AppCompatActivity {

    //test
    //Button add a new medecine
    private Button btnAddNewMedecine;
    private List<MedecineEntity> MedecineEntities;

    ArrayList<String> medecines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_medecine);

        //Button to add a new Medecine to the list of medecine
        pressBtnAddNewMedecine();

        //Try to access to the database
        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListView list;

        //Switch between activities
        Intent intent = new Intent(ListOfMedecineActivity.this, MedecineDetails.class);
        list = (ListView) findViewById(R.id.listofmedecine);
        list.setAdapter(new ListViewWithDelBtnAdapterMedecine(MedecineEntities, ListOfMedecineActivity.this, intent, R.layout.listofmedecine_layout, R.id.listview_listofmedecine, R.id.deleteMedecineButton));


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

    //Read the db from our application
    public void readDB() throws ExecutionException, InterruptedException {

        medecines = new ArrayList<String>();

        //access to the database creator
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(ListOfMedecineActivity.this);

        //Get all medecines form our db
        MedecineEntities = new AsyncGetMedecines(ListOfMedecineActivity.this).execute().get();

        //Add all the medecine in the list to display it
        for (MedecineEntity p : MedecineEntities){
            medecines.add(p.getName());
        }


    }


}
