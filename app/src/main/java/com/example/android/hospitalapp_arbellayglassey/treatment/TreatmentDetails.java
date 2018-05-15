package com.example.android.hospitalapp_arbellayglassey.treatment;

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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithDelBtnAdapterLink;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineAddSearchList;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineDetails;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TreatmentDetails extends AppCompatActivity {

    //Variables
    private Button btnAddMedecineToTreatment;
    private ImageButton btnModifyTreatment;
    private TreatmentEntity treatmentEntity;
    private PatientEntity patientEntity;
    private List<TreatmentMedecineLinkEntity>  listLinkEntity = new ArrayList<>();
    private String idPatient;
    private TextView textViewAdmission;
    private TextView textViewName;
    private TextView textViewQuantityName;
    private DrawerLayout mDrawerLayout;

 //private TextView textViewAdmission;
    private ListViewWithDelBtnAdapterLink adapterLink;
    private List<MedecineEntity> medecineEntityList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);



        //Add a medecine to the treatment of a patient
        pressAddMedecineToTreatment();
        pressBtnModifyTreatment();
        readFirebase();
        // adding list
        ListView list;


        Intent intent = new Intent(TreatmentDetails.this, MedecineDetails.class);

        list = (ListView) findViewById(R.id.listofmedicinefortreatment);
        adapterLink = new ListViewWithDelBtnAdapterLink(listLinkEntity,medecineEntityList,idPatient,TreatmentDetails.this, intent, R.layout.listofmedicinefortreatment_layout, R.id.listview_listofmedecinefortreatment, R.id.deleteMedecineForTreatmentButton);
        list.setAdapter(adapterLink);
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        readFirebase();

        setText();
        adapterLink.refreshEvents(medecineEntityList, listLinkEntity);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    //Set the id
    public void setId(){
        textViewAdmission = findViewById(R.id.AdmissionTreatmentDetails);
        textViewName = findViewById(R.id.nameTreatmentDetails);
        textViewQuantityName = findViewById(R.id.quantityTreatmentDetails);

    }

    //Set the text
    public void setText(){
        textViewName.setText(treatmentEntity.getName());
        textViewQuantityName.setText(String.valueOf(treatmentEntity.getMaxQuantity()));
        textViewAdmission.setText(patientEntity.getReasonAdmission());

    }

    //When the user decide to add medecine to a treatment
    public void pressAddMedecineToTreatment(){
        //Find the id view for the button add a medecine to a treatment
        btnAddMedecineToTreatment = (Button) findViewById(R.id.btn_add_medicine);

        //Add a listener to access to the medecine add search activity
        btnAddMedecineToTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentDetails.this, MedecineAddSearchList.class);
                intent.putExtra("idT", treatmentEntity.getIdT());
                intent.putExtra("idP", idPatient);
                TreatmentDetails.this.startActivity(intent);
            }
        });
    }

    //When the user decide to modify the treatment of a patient
    public void pressBtnModifyTreatment(){

        //Find the id view for the button modify the treatment
        btnModifyTreatment = (ImageButton) findViewById(R.id.btn_modify_treatment);

        //Add a listener to modify the treatment of a patient
        btnModifyTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentDetails.this, TreatmentModify.class);
                intent.putExtra("idT", treatmentEntity.getIdT());
                intent.putExtra("idP", idPatient);
                TreatmentDetails.this.startActivity(intent);
            }
        });

    }

    public void readFirebase()  {

        //get the intent and add th id to the variable
        Intent intentGetId = getIntent();
        idPatient = intentGetId.getStringExtra("idP");

        // get patient
       FirebaseDatabase.getInstance()
                .getReference("Patients")
                .child(idPatient)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        patientEntity = dataSnapshot.getValue(PatientEntity.class);
                        //find the textview by his id



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("treat details", "getPatient: onCancelled", databaseError.toException());
                    }
                });

       //get treatment
        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .child(idPatient)
                .child("treatment")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        treatmentEntity = dataSnapshot.getValue(TreatmentEntity.class);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                     Log.d("treat details", "getTreatment: onCancelled", databaseError.toException());
                    }
                });


        medecineEntityList= new ArrayList<>();
        //get list of links
        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .child(idPatient)
                .child("treatment")
                .child("links")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            listLinkEntity.clear();
                            listLinkEntity.addAll(toLinks(dataSnapshot));
                            List<TreatmentMedecineLinkEntity> tempList = new ArrayList<>(listLinkEntity);

                            //poulate the list
                            for (TreatmentMedecineLinkEntity linkEntity: listLinkEntity) {
                                medecineEntityList.add(getOneMedecine(linkEntity));
                            }
                            setTitle(patientEntity.getName());
                            setId();
                            setText();

                            adapterLink.refreshEvents(medecineEntityList,tempList);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



        // get medecine

        //listLinkEntity = new AsyncGetLinks(TreatmentDetails.this, treatmentEntity.getIdT()).execute().get();




    }


    private MedecineEntity getOneMedecine(TreatmentMedecineLinkEntity entity){

        final MedecineEntity[] medecineEntity = new MedecineEntity[1];

        // get medecine
        FirebaseDatabase.getInstance()
                .getReference("Medecines")
                .child(entity.getIdM())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            medecineEntity[0] = dataSnapshot.getValue(MedecineEntity.class);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("treat details", "getPatient: onCancelled", databaseError.toException());
                    }
                });

        return medecineEntity[0];
    }

    private List<TreatmentMedecineLinkEntity> toLinks (DataSnapshot snapshot) {

        List<TreatmentMedecineLinkEntity> links = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            TreatmentMedecineLinkEntity entity = childSnapshot.getValue(TreatmentMedecineLinkEntity.class);
           /* entity.setIdTreatment(childSnapshot.getKey());
            entity.setIdMedecine(childSnapshot.getChildren());*/
            links.add(entity);
        }
        return links;


    }
    //set up the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);

        setupActionBar();
        setupNavBar();
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
    //setup navigation drawer
    //this method setup the navigation drawer and implement the button to go to the list
    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layoutt_details_treatment);

        NavigationView navigationView = findViewById(R.id.nav_view_treatment_details);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(TreatmentDetails.this, ListOfPatientActivity.class);
                                TreatmentDetails.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(TreatmentDetails.this, ListOfMedecineActivity.class);
                                TreatmentDetails.this.startActivity(intentMed);
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
