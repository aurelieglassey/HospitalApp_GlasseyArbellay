package com.example.android.hospitalapp_arbellayglassey.listActivity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.hospitalapp_arbellayglassey.MainActivity;
import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithDelBtnAdapterMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecines;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineAdd;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineDetails;
import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListOfMedecineActivity extends AppCompatActivity {

    //Variables
    private Button btnAddNewMedecine;
    private List<MedecineEntity> MedecineEntities;
    private ListViewWithDelBtnAdapterMedecine adapterMedecine;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_medecine);

        //Button to add a new Medecine to the list of medecine
        pressBtnAddNewMedecine();
        setupNavBar();

        //Try to access to the database
        try {
            readDB();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        ListView list;

        //Switch between activities
        Intent intent = new Intent(ListOfMedecineActivity.this, MedecineDetails.class);
        list = (ListView) findViewById(R.id.listofmedecine);
        adapterMedecine = (new ListViewWithDelBtnAdapterMedecine(MedecineEntities, ListOfMedecineActivity.this, intent, R.layout.listofmedecine_layout, R.id.listview_listofmedecine, R.id.deleteMedecineButton));
        list.setAdapter(adapterMedecine);

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
    // on restart the activity, to refrehs the data
    @Override
    public void onRestart(){
        super.onRestart();

        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapterMedecine.refreshEvents(MedecineEntities);

    }
    //Read the db from our application
    public void readDB() throws ExecutionException, InterruptedException {

        //access to the database creator
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(ListOfMedecineActivity.this);

        //Get all medecines form our db
        MedecineEntities = new AsyncGetMedecines(ListOfMedecineActivity.this).execute().get();

    }

    //on create the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.list_of_medecine);
        setupActionBar();
        return true;

    }
    private void setupActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        else{
            finish();
        }

        return true;
    }

    //this method setup the navigation drawer and implement the button to go to the list
    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout_list_of_medecine);

        NavigationView navigationView = findViewById(R.id.nav_view_list_medecine);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(ListOfMedecineActivity.this, ListOfPatientActivity.class);
                                ListOfMedecineActivity.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Toast.makeText(ListOfMedecineActivity.this, "You are already on this activity", Toast.LENGTH_LONG);
                                break;
                            default:
                                break;
                        }
                        mDrawerLayout.closeDrawers();

                        finish();
                        return true;
                    }
                });
    }

}

