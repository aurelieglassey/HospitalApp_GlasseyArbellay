package com.example.android.hospitalapp_arbellayglassey.medecine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecines;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.treatment.TreatmentDetails;
import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithAddBtnAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MedecineAddSearchList extends AppCompatActivity {



    private List<MedecineEntity> MedecineEntities;

    ArrayList<String> medecines;
    int idT;
    int idP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add_search_list);

        // adding list
        //final ArrayList<String> medecine = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.medecine_array)));
        ListView list;
        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Intent intent = new Intent(MedecineAddSearchList.this, TreatmentDetails.class);
        Intent intentGetID = getIntent();
        idT = intentGetID.getIntExtra("idT",0);
        idP = intentGetID.getIntExtra("idP", 0);
        list = (ListView) findViewById(R.id.listofmedecinesearchlist);
        list.setAdapter(new ListViewWithAddBtnAdapter(MedecineEntities, idT,idP,MedecineAddSearchList.this, R.layout.listmedecineaddsearchlist_layout, R.id.listview_listofmedecineaddsearchlist, R.id.addMedecineForTreatmentButton));


    }


    //Read the db from our application
    public void readDB() throws ExecutionException, InterruptedException {

        medecines = new ArrayList<String>();

        //access to the database creator
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(MedecineAddSearchList.this);
        //Get all medecines form our db
        MedecineEntities = new AsyncGetMedecines(MedecineAddSearchList.this).execute().get();

        //Add all the medecine in the list to display it
        for (MedecineEntity p : MedecineEntities){
            medecines.add(p.getName());
        }


    }


}
