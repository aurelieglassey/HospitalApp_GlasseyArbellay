package com.example.android.hospitalapp_arbellayglassey.patient;

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
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;
import com.example.android.hospitalapp_arbellayglassey.treatment.TreatmentDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class PatientDetails extends AppCompatActivity {

    //Variables
    private Button btnShowTreatment;
    private ImageButton btnModifyPatient;
    private PatientEntity patientEntity;
    private String idPatient;
    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewGender;
    private TextView textViewRoom;
    private TextView textViewBloodGroup;
    private TextView textViewAdmission;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        readFirebase();

        setupNavBar();



    }
    // on restart
    @Override
    public void onRestart() {
        super.onRestart();

        readFirebase();
        setText();
    }

    //find by id
    public void setId(){
        textViewName = findViewById(R.id.namePatientDetails);
        textViewAge = findViewById(R.id.agePatientDetails);
        textViewGender = findViewById(R.id.genderPatientDetails);
        textViewRoom = findViewById(R.id.roomPatientDetails);
        textViewBloodGroup = findViewById(R.id.bloodGroupPatientDetails);
        textViewAdmission = findViewById(R.id.admissionPatientDetails);
    }

    // set the tex
    public void setText(){
        textViewName.setText(patientEntity.getName());
        textViewAge.setText(String.valueOf(patientEntity.getAge()));
        textViewGender.setText(String.valueOf(patientEntity.getGender()));
        textViewRoom.setText(String.valueOf(patientEntity.getRoomNumber()));
        textViewBloodGroup.setText(patientEntity.getBloodGroup());
        textViewAdmission.setText(patientEntity.getReasonAdmission());
    }

    public void readFirebase(){

        Intent intentGetId = getIntent();
        idPatient = intentGetId.getStringExtra("idP");

        //Get the patient with AsyncGetPatient
        // patientEntity = new AsyncGetPatient(PatientModify.this, idPatient).execute().get();

        // get Medecine from firebase
        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .child(idPatient)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        patientEntity = dataSnapshot.getValue(PatientEntity.class);
                        //Set the id, text and navbar
                        setId();
                        setText();
                        //Press on the button Show Treatment
                        pressBtnShowTreatment();
                        pressBtnModifyPatient();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("Patient modify ", "getPatientForModify for modify: onCancelled", databaseError.toException());
                    }
                });


    }

    //When the user decide to display the treatment of a patient
    public void pressBtnShowTreatment(){

        //Find the id view for the button show the treatement of a patient
        btnShowTreatment = (Button) findViewById(R.id.btn_show_treatment);

        //Add a listener to access to the  Treatment details activity
        btnShowTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientDetails.this, TreatmentDetails.class);
                intent.putExtra("idP", patientEntity.getIdP());
                PatientDetails.this.startActivity(intent);
            }
        });

    }

    //When the user decide to modify the data of the patient
    public void pressBtnModifyPatient(){

            //Find the id view for the button modify the patient
            btnModifyPatient = (ImageButton) findViewById(R.id.btn_modify_Patient);

            //Add a listener to modify the patient
            btnModifyPatient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PatientDetails.this, PatientModify.class);
                    intent.putExtra("idP", patientEntity.getIdP());
                    PatientDetails.this.startActivity(intent);
                }
            });

    }
    // setup the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_patient);
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

    //setup navigation drawer
    //this method setup the navigation drawer and implement the button to go to the list
    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout_details_patient);

        NavigationView navigationView = findViewById(R.id.nav_view_details_patient);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(PatientDetails.this, ListOfPatientActivity.class);
                                PatientDetails.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(PatientDetails.this, ListOfMedecineActivity.class);
                                PatientDetails.this.startActivity(intentMed);
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
