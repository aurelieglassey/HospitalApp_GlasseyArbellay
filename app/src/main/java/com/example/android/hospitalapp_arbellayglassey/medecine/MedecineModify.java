package com.example.android.hospitalapp_arbellayglassey.medecine;

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
import android.widget.ImageButton;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncUpdateMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;

import java.util.concurrent.ExecutionException;

public class MedecineModify extends AppCompatActivity {

    //Variables
    private Button btnModifyMedecine;
    private int idMedecine;
    private MedecineEntity medecineEntity;
    private EditText editTextName;
    private EditText editTextType;
    private EditText editTextIngredient;
    private EditText editTextManufacturer;
    private EditText editTextEffects;
    private EditText editTextMaxDay;
    private EditText editTextApplication;
    private DrawerLayout mDrawerLayout;
    private String messageError = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_modify);

        //add the error message
        messageError = this.getString(R.string.error_enter_field);

        // read the db
        try {
            readDB();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        //When the user press on the button modify
        pressBtnModifyMedecine();

        //Set id and text
        setId();
        setText();

    }


    //Read the db and get the medecine
    public void readDB() throws ExecutionException, InterruptedException {

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(MedecineModify.this);
        Intent intentGetId = getIntent();
        idMedecine = intentGetId.getIntExtra("idM", 0);
        medecineEntity = new AsyncGetMedecine(MedecineModify.this, idMedecine).execute().get();

    }

    //Set the id of the edittext
    public void setId (){
        editTextName = findViewById(R.id.editTextNameMedecineModify);
        editTextType = findViewById(R.id.editTextTypeMedecineModify);
        editTextIngredient = findViewById(R.id.editTextActiveIngredientModify);
        editTextManufacturer = findViewById(R.id.editTextManufacturerMedecineModify);
        editTextIngredient = findViewById(R.id.editTextActiveIngredientModify);
        editTextEffects = findViewById(R.id.editTextSideEffectMedecineModify);
        editTextMaxDay = findViewById(R.id.editTextMaxDayMedecineModify);
        editTextApplication = findViewById(R.id.editTextApplicationMedecineModify);
    }

    //Set the text of the edittext
    public void setText(){
        editTextName.setText(medecineEntity.getName());
        editTextType.setText(medecineEntity.getType());
        editTextIngredient.setText(medecineEntity.getActiveIngredient());
        editTextManufacturer.setText(medecineEntity.getManufacturers());
        editTextIngredient.setText(medecineEntity.getActiveIngredient());
        editTextEffects.setText(medecineEntity.getSideEffects());
        editTextMaxDay.setText(String.valueOf(medecineEntity.getMaxPerDay()));
        editTextApplication.setText(medecineEntity.getApplication());
    }



    // When the user decide to modify the data of a medecine
    public void pressBtnModifyMedecine(){

        //Find the id view for the button modify the data of a medecine
        btnModifyMedecine = (Button) findViewById(R.id.buttonOkModifyMedecine);

        //Add a listener to modify the data of a medecine
        btnModifyMedecine.setOnClickListener(new View.OnClickListener() {
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
                //Check type
                if (editTextType.getText().toString().length() == 0) {
                    editTextType.setError(messageError);
                    editTextType.requestFocus();
                    error = 1;
                }
                //Check ingredient
                if (editTextIngredient.getText().toString().length() == 0) {
                    editTextIngredient.setError(messageError);
                    editTextIngredient.requestFocus();
                    error = 1;
                }
                //check manufacturer
                if (editTextManufacturer.getText().toString().length() == 0) {
                    editTextManufacturer.setError(messageError);
                    editTextManufacturer.requestFocus();
                    error = 1;
                }
                //check side effects
                if (editTextEffects.getText().toString().length() == 0) {
                    editTextEffects.setError(messageError);
                    editTextEffects.requestFocus();
                    error = 1;
                }

                //check max day of this medecine
                if (editTextMaxDay.getText().toString().length() == 0) {
                    editTextMaxDay.setError(messageError);
                    editTextMaxDay.requestFocus();
                    error = 1;
                }
                //check application
                if (editTextApplication.getText().toString().length() == 0) {
                    editTextApplication.setError(messageError);
                    editTextApplication.requestFocus();
                    error = 1;
                }

                //if error == 0 it means that the user has correctly fill all the fields
                if (error == 0) {

                     //Edit the Edit TExt
                    medecineEntity.setName(editTextName.getText().toString());
                    medecineEntity.setType(editTextType.getText().toString());
                    medecineEntity.setActiveIngredient(editTextIngredient.getText().toString());
                    medecineEntity.setManufacturers(editTextManufacturer.getText().toString());
                    medecineEntity.setActiveIngredient(editTextIngredient.getText().toString());
                    medecineEntity.setSideEffects(editTextEffects.getText().toString());
                    medecineEntity.setMaxPerDay(Integer.parseInt(editTextMaxDay.getText().toString()));
                    medecineEntity.setApplication(editTextApplication.getText().toString());

                    //Update the medecine
                    new AsyncUpdateMedecine(MedecineModify.this).execute(medecineEntity);

                    finish();
                }
            }

        });

    }

    // setup the option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_medecine);
        setupActionBar();
        setupNavBar();
        return true;

    }
    // set up the action bar
    private void setupActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    // implement button
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
        mDrawerLayout = findViewById(R.id.drawer_layout_details_medecine);

        NavigationView navigationView = findViewById(R.id.nav_view_modify_medecine);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(MedecineModify.this, ListOfPatientActivity.class);
                                MedecineModify.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(MedecineModify.this, ListOfMedecineActivity.class);
                                MedecineModify.this.startActivity(intentMed);
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
