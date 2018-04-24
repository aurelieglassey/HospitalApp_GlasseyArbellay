package com.example.android.hospitalapp_arbellayglassey.medecine;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_modify);

        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBtnModifyMedecine();

        editTextName = findViewById(R.id.editTextNameMedecineModify);
        editTextType = findViewById(R.id.editTextTypeMedecineModify);
        editTextIngredient = findViewById(R.id.editTextActiveIngredientModify);
        editTextManufacturer = findViewById(R.id.editTextManufacturerMedecineModify);
        editTextIngredient = findViewById(R.id.editTextActiveIngredientModify);
        editTextEffects = findViewById(R.id.editTextSideEffectMedecineModify);
        editTextMaxDay = findViewById(R.id.editTextMaxDayMedecineModify);
        editTextApplication = findViewById(R.id.editTextApplicationMedecineModify);

        editTextName.setText(medecineEntity.getName());
        editTextType.setText(medecineEntity.getType());
        editTextIngredient.setText(medecineEntity.getActiveIngredient());
        editTextManufacturer.setText(medecineEntity.getManufacturers());
        editTextIngredient.setText(medecineEntity.getActiveIngredient());
        editTextEffects.setText(medecineEntity.getSideEffects());
        editTextMaxDay.setText(String.valueOf(medecineEntity.getMaxPerDay()));
        editTextApplication.setText(medecineEntity.getApplication());
    }


    //Read the db and get the medecine
    public void readDB() throws ExecutionException, InterruptedException {

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(MedecineModify.this);
        Intent intentGetId = getIntent();
        idMedecine = intentGetId.getIntExtra("idM", 0);
        medecineEntity = new AsyncGetMedecine(MedecineModify.this, idMedecine).execute().get();

    }

    // When the user decide to modify the data of a medecine
    public void pressBtnModifyMedecine(){

        //Find the id view for the button modify the data of a medecine
        btnModifyMedecine = (Button) findViewById(R.id.buttonOkModifyMedecine);

        //Add a listener to modify the data of a medecine
        btnModifyMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                medecineEntity.setName(editTextName.getText().toString());
                medecineEntity.setType(editTextType.getText().toString());
                medecineEntity.setActiveIngredient(editTextIngredient.getText().toString());
                medecineEntity.setManufacturers(editTextManufacturer.getText().toString());
                medecineEntity.setActiveIngredient(editTextIngredient.getText().toString());
                medecineEntity.setSideEffects(editTextEffects.getText().toString());
                medecineEntity.setMaxPerDay(Integer.parseInt(editTextMaxDay.getText().toString()));
                medecineEntity.setApplication(editTextApplication.getText().toString());

                new AsyncUpdateMedecine(MedecineModify.this).execute(medecineEntity);
                //Intent intent = new Intent(MedecineModify.this, MedecineDetails.class);
                //intent.putExtra("idM", medecineEntity.getIdM());
                //MedecineModify.this.startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_medecine);
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
}
