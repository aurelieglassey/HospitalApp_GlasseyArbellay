package com.example.android.hospitalapp_arbellayglassey.patient;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncGetPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.treatment.TreatmentDetails;

import java.util.concurrent.ExecutionException;

public class PatientDetails extends AppCompatActivity {

    //Variables
    private Button btnShowTreatment;
    private ImageButton btnModifyPatient;
    private PatientEntity patientEntity;
    private int idPatient;
    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewGender;
    private TextView textViewRoom;
    private TextView textViewBloodGroup;
    private TextView textViewAdmission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        //Read the db
        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //find the textview by his id
        setId();
        setText();



        //Press on the button Show Treatment
        pressBtnShowTreatment();
        pressBtnModifyPatient();


    }
    @Override
    public void onRestart() {
        super.onRestart();

        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setText();
    }

    public void setId(){
        textViewName = findViewById(R.id.namePatientDetails);
        textViewAge = findViewById(R.id.agePatientDetails);
        textViewGender = findViewById(R.id.genderPatientDetails);
        textViewRoom = findViewById(R.id.roomPatientDetails);
        textViewBloodGroup = findViewById(R.id.bloodGroupPatientDetails);
        textViewAdmission = findViewById(R.id.admissionPatientDetails);
    }
    public void setText(){
        textViewName.setText(patientEntity.getName());
        textViewAge.setText(String.valueOf(patientEntity.getAge()));
        textViewGender.setText(String.valueOf(patientEntity.getGender()));
        textViewRoom.setText(String.valueOf(patientEntity.getRoomNumber()));
        textViewBloodGroup.setText(patientEntity.getBloodGroup());
        textViewAdmission.setText(patientEntity.getReasonAdmission());
    }



    //Read the db
    public void readDB() throws ExecutionException, InterruptedException {

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(PatientDetails.this);

        Intent intentGetId = getIntent();
        idPatient = intentGetId.getIntExtra("idP", 0);

        patientEntity = new AsyncGetPatient(PatientDetails.this, idPatient).execute().get();


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
               // intent.putExtra("idT", idMedecine);
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
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    /*
    public void setActionBarTitle(String title) {
        setTitle(title);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
    */
}
