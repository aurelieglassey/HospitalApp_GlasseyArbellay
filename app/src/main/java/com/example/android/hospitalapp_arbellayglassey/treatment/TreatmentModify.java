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
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ExecutionException;

public class TreatmentModify extends AppCompatActivity {

    //Variables
    private Button btnModifyTreatmentOk;
    private TreatmentEntity treatmentEntity;
    private PatientEntity patientEntity;
    private String idTreatment;
    private String idPatient;
    private TextView textViewAdmission;
    private EditText editTextName;
    private EditText editTextQuantity;
    private DrawerLayout mDrawerLayout;
    private String messageError = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_modify);

        //add the error message
        messageError = this.getString(R.string.error_enter_field);

        readFirebase();

        //Btn modify the treatment
        pressBtnModifyTreatment();



    }
    //find view by id
    public void setid(){
        textViewAdmission = findViewById(R.id.AdmissionTreatmentModify);
        editTextName = findViewById(R.id.nameTreatmentModify);
        editTextQuantity = findViewById(R.id.quantityTreatmentModify);

    }

    //set text
    public void setText(){
        textViewAdmission.setText(patientEntity.getReasonAdmission());
        editTextName.setText(treatmentEntity.getName());
        editTextQuantity.setText(String.valueOf(treatmentEntity.getMaxQuantity()));

    }


    //When the user want to apply the changes he has made for the treatment
    public void pressBtnModifyTreatment() {

        //find the button from his id
        btnModifyTreatmentOk = (Button) findViewById(R.id.btn_modify_treatment);

        btnModifyTreatmentOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if the user has correctly fiel the fields
                int error = 0;
                //Check name
                if (editTextName.getText().toString().length() == 0) {
                    editTextName.setError(messageError);
                    editTextName.requestFocus();
                    error = 1;
                }
                //Check quantity
                if (editTextQuantity.getText().toString().length() == 0) {
                    editTextQuantity.setError(messageError);
                    editTextQuantity.requestFocus();
                    error = 1;
                }

                //if error == 0 it means that the user has correctly fill all the fields
                if (error == 0) {

                     //Edit the Edit TExt

                    treatmentEntity.setName(editTextName.getText().toString());
                    treatmentEntity.setMaxQuantity(Integer.parseInt(editTextQuantity.getText().toString()));

                    //new AsyncUpdateTreatment(TreatmentModify.this).execute(treatmentEntity);

                    updateTreatment(treatmentEntity);
                    finish();
                    }

                }
            });
    }

    //Update the Treatment
    private void updateTreatment(final TreatmentEntity treatmentEntity) {
        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .child(idPatient)
                .child("treatment")
                .updateChildren(treatmentEntity.toMap(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d("treatmentModify", "Firebase DB update failure!");
                        } else {
                            Log.d("treatmentModify", "Firebase DB update successful!");


                        }
                    }
                });
    }

    //Read the db and get the treatement
    public void readFirebase()  {

        Intent intentGetId = getIntent();

        idTreatment = intentGetId.getStringExtra("idT");
        //treatmentEntity = new AsyncGetTreatment(TreatmentModify.this, idTreatment).execute().get();

        idPatient = intentGetId.getStringExtra("idP");
        //patientEntity = new AsyncGetPatient(TreatmentModify.this, idPatient).execute().get();

        // get patient
        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .child(idPatient)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        patientEntity = dataSnapshot.getValue(PatientEntity.class);

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
                        //set id and text
                        setid();
                        setText();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("treat details", "getTreatment: onCancelled", databaseError.toException());
                    }
                });


    }

    // set up the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(patientEntity.getName());
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

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        } else {
            finish();
        }

        return true;
    }


    //setup navigation drawer
    //this method setup the navigation drawer and implement the button to go to the list
    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout_modify_treatment);

        NavigationView navigationView = findViewById(R.id.nav_view_treatment_modify);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(TreatmentModify.this, ListOfPatientActivity.class);
                                TreatmentModify.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(TreatmentModify.this, ListOfMedecineActivity.class);
                                TreatmentModify.this.startActivity(intentMed);
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
