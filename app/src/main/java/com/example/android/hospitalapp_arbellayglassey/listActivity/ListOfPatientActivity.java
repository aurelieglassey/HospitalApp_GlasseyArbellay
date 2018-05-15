package com.example.android.hospitalapp_arbellayglassey.listActivity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithDelBtnAdapterPatient;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientAdd;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientDetails;
import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientModify;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListOfPatientActivity extends AppCompatActivity {

    //Variables
    private Button btnNewPatient;
    private List<PatientEntity> patientEntities  = new ArrayList<>();
    private ListViewWithDelBtnAdapterPatient adapterPatient;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_patient);

        //Button to add a new patient for the list
        pressBtnNewPatient();
        setupNavBar();


        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            patientEntities.clear();
                            patientEntities.addAll(toPatients(dataSnapshot));
                            List<PatientEntity> tempList = new ArrayList<>(patientEntities);
                            adapterPatient.refreshEvents(tempList);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("listpatientact", "getAll: onCancelled", databaseError.toException());
                    }
                });




        ListView list;

        //Intent to switch between the activities
        Intent intent = new Intent(ListOfPatientActivity.this, PatientDetails.class);
        list = (ListView) findViewById(R.id.listofpatient);
        adapterPatient =  new ListViewWithDelBtnAdapterPatient( patientEntities, ListOfPatientActivity.this, intent, R.layout.listofpatient_laylout, R.id.listview_listofpatient, R.id.deletePatientButton);
        list.setAdapter(adapterPatient);

    }

    @Override
    public void onRestart(){
        super.onRestart();

        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            patientEntities.clear();
                            patientEntities.addAll(toPatients(dataSnapshot));
                            List<PatientEntity> tempList = new ArrayList<>(patientEntities);
                            adapterPatient.refreshEvents(tempList);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("listpatientact", "getAll: onCancelled", databaseError.toException());
                    }
                });


    }

    //Read te db from our application
    public List<PatientEntity> toPatients(DataSnapshot dataSnapshot) {

        //Access to the database creator
        //DatabaseCreator dbCreator = DatabaseCreator.getInstance(ListOfPatientActivity.this);

        //Execute and get all the patients from our  firebase database
        List<PatientEntity> patientEntities = new ArrayList<>();
        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){
            PatientEntity entity = childDataSnapshot.getValue(PatientEntity.class);
            entity.setIdP(childDataSnapshot.getKey());
            patientEntities.add(entity);
        }
        return patientEntities;
    }

    //When the user decide to add a patient
    public void pressBtnNewPatient(){
        btnNewPatient = (Button) findViewById(R.id.btn_add_patient);
        btnNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfPatientActivity.this, PatientAdd.class);
                ListOfPatientActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_list_of_patient);
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
        mDrawerLayout = findViewById(R.id.drawer_layout_list_of_patient);

        NavigationView navigationView = findViewById(R.id.nav_view_list_patient);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Toast.makeText(ListOfPatientActivity.this, "You are already on this activity", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(ListOfPatientActivity.this, ListOfMedecineActivity.class);
                                ListOfPatientActivity.this.startActivity(intentMed);
                                finish();
                                break;
                            default:
                                break;
                        }
                        mDrawerLayout.closeDrawers();


                        return true;
                    }
                });
    }


}
