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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;

import java.util.concurrent.ExecutionException;

public class MedecineDetails extends AppCompatActivity {

    //Variables
    private ImageButton btnModifyMedecine;
    private DrawerLayout mDrawerLayout;
    private int idMedecine;
    private MedecineEntity medecineEntity;
    private TextView textViewName;
    private TextView textViewType;
    private TextView textViewIngredient;
    private TextView textViewManufacturer;
    private TextView textViewEffects;
    private TextView textViewMaxDay;
    private TextView textViewApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_details);

        //Read the db
        try {
            readDB();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        //id , text of the textview
        setId();
        setText();

        //Button to modify a medecine
        pressBtnModifyMedecine();
    }

    //When the user decide to modify the data of a medecine
    public void pressBtnModifyMedecine(){

        //Find the id view for the button modify the data of a medecine
        btnModifyMedecine = (ImageButton) findViewById(R.id.btn_modify_MedecineDetails);

        //Add a listener to modify the data of a medecine
        btnModifyMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MedecineDetails.this, MedecineModify.class);
                intent.putExtra("idM", idMedecine);
                MedecineDetails.this.startActivity(intent);
            }
        });
    }

    // on restart to set the text with the new value
    @Override
    public void onRestart() {
        super.onRestart();

        try {
            readDB();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        setText();
    }

     //Set the id of the textview
    public void setId(){
        textViewName = findViewById(R.id.textNameMedecineDetails);
        textViewType = findViewById(R.id.textTypeMedecineDetails);
        textViewIngredient = findViewById(R.id.textActiveIngredientMedecineDetails);
        textViewManufacturer = findViewById(R.id.textManufacturerMedecineDetails);
        textViewEffects = findViewById(R.id.textSideEffectMedecineDetails);
        textViewMaxDay = findViewById(R.id.textMaxDayMedecineDetails);
        textViewApplication = findViewById(R.id.textApplicationMedecineDetails);
    }
    // set the text of the textview
    public void setText(){
        textViewName.setText(medecineEntity.getName());
        textViewType.setText(medecineEntity.getType());
        textViewIngredient.setText(medecineEntity.getActiveIngredient());
        textViewManufacturer.setText(medecineEntity.getManufacturers());
        textViewEffects.setText(medecineEntity.getSideEffects());
        textViewMaxDay.setText(String.valueOf(medecineEntity.getMaxPerDay()));
        textViewApplication.setText(medecineEntity.getApplication());

    }
    //Read the db and get the medecine
    public void readDB() throws ExecutionException, InterruptedException {

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(MedecineDetails.this);

        Intent intentGetId = getIntent();
        idMedecine = intentGetId.getIntExtra("idM", 0);

        medecineEntity = new AsyncGetMedecine(MedecineDetails.this, idMedecine).execute().get();

    }

    // set up the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_medecine);
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
        mDrawerLayout = findViewById(R.id.drawer_layout_details_medecine);

        NavigationView navigationView = findViewById(R.id.nav_view_details_medecine);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(MedecineDetails.this, ListOfPatientActivity.class);
                                MedecineDetails.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(MedecineDetails.this, ListOfMedecineActivity.class);
                                MedecineDetails.this.startActivity(intentMed);
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
