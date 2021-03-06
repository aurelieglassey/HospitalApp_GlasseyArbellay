package com.example.android.hospitalapp_arbellayglassey.patient;

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
import android.widget.EditText;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncGetPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncUpdatePatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;

import java.util.concurrent.ExecutionException;


public class PatientModify extends AppCompatActivity {


    private Button btnModifyPatient;
    private String messageError = "";
    private PatientEntity patientEntity;
    private int idPatient;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextGender;
    private EditText editTextRoom;
    private EditText editTextBlood;
    private EditText editTextAdmission;
    private DrawerLayout mDrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_modify);

        //add the error message
        messageError = this.getString(R.string.error_enter_field);


        try {
            readDB();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        //set id and text
        setId();
        setText();

        //Button to modify the Patient
        pressBtnModifyPatient();
    }


    //fin view by id of the Edit text
    public void setId() {
        editTextName = findViewById(R.id.namePatientModify);
        editTextAge = findViewById(R.id.agePatientModify);
        editTextGender = findViewById(R.id.genderPatientModify);
        editTextRoom = findViewById(R.id.ageRoomPatientModify);
        editTextBlood = findViewById(R.id.bloodGroupPatientModify);
        editTextAdmission = findViewById(R.id.admissionPatientModify);
    }

    //Set the text of the edit text
    public void setText() {
        editTextName.setText(patientEntity.getName());
        editTextAge.setText(String.valueOf(patientEntity.getAge()));
        editTextGender.setText(String.valueOf(patientEntity.getGender()));
        editTextRoom.setText(String.valueOf(patientEntity.getRoomNumber()));
        editTextBlood.setText(patientEntity.getBloodGroup());
        editTextAdmission.setText(patientEntity.getReasonAdmission());

    }


    public void readDB() throws ExecutionException, InterruptedException {

        //access to the databasecreator
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(PatientModify.this);

        Intent intentGetId = getIntent();
        idPatient = intentGetId.getIntExtra("idP", 0);

        //Get the patient with AsyncGetPatient
        patientEntity = new AsyncGetPatient(PatientModify.this, idPatient).execute().get();


    }

    // When the user decide to modify the data of a patient
    public void pressBtnModifyPatient() {

        //Find the id view for the button modify the data of a patient
        btnModifyPatient = (Button) findViewById(R.id.btn_ok_modify_patient);

        //Add a listener to modify the data of a patient
        btnModifyPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //Check if there is an error when the user fill the textview
                //or if the user doesn't fill anyting.
                int error = 0;

                //Check name
                if (editTextName.getText().toString().length() == 0  ){
                    //namePatient.setError(messageError);
                    editTextName.setError(messageError);
                    editTextName.requestFocus();
                    error = 1;
                }

                //Check age
                if(editTextAge.getText().toString().length() == 0){
                    editTextAge.setError(messageError);
                    editTextAge.requestFocus();
                    error = 1;
                }

                //Check Gender
                if (editTextGender.getText().toString().length() != 1){
                    editTextGender.setError(messageError);
                    editTextGender.requestFocus();
                    error = 1;
                }

                //Check Room
                if(editTextRoom.getText().toString().length() == 0){
                    editTextRoom.setError(messageError);
                    editTextRoom.requestFocus();
                    error = 1;
                }

                //Check blood Group
                if (editTextBlood.getText().toString().length() == 0  ){
                    editTextBlood.setError(messageError);
                    editTextBlood.requestFocus();
                    error = 1;
                }

                //Check reason of admission
                if (editTextAdmission.getText().toString().length() == 0  ){
                    editTextAdmission.setError(messageError);
                    editTextAdmission.requestFocus();
                    error = 1;
                }


                //if the error is 0, it means that the fields are correctly fill
                if (error == 0) {
                    patientEntity.setName(editTextName.getText().toString());
                    patientEntity.setAge(Integer.parseInt(editTextAge.getText().toString()));
                    patientEntity.setGender(editTextGender.getText().charAt(0));
                    patientEntity.setRoomNumber(Integer.parseInt(editTextRoom.getText().toString()));
                    patientEntity.setBloodGroup(editTextBlood.getText().toString());
                    patientEntity.setReasonAdmission(editTextAdmission.getText().toString());

                    //Update the patient
                    new AsyncUpdatePatient(PatientModify.this).execute(patientEntity);

                    finish();
                }
            }
        });

    }

    //set up the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_patient);
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
        mDrawerLayout = findViewById(R.id.drawer_layout_modify_patient);

        NavigationView navigationView = findViewById(R.id.nav_view_modify_patient);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(PatientModify.this, ListOfPatientActivity.class);
                                PatientModify.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(PatientModify.this, ListOfMedecineActivity.class);
                                PatientModify.this.startActivity(intentMed);
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

