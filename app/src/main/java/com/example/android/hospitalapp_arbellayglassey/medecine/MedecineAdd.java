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

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncAddMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;

import java.util.concurrent.ExecutionException;

public class MedecineAdd extends AppCompatActivity {

    //Button to add a new Medecine
    private Button btnAddNewMedecine;
    private MedecineEntity medecineEntity;
    private String messageError = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add);
        messageError = this.getString(R.string.error_enter_field);

        //Button add new medecine
        pressBtnOKAddNewMedecine();
    }

    //When the user want to add a new medecine and the data are ok to be added
    //button ok
    public void pressBtnOKAddNewMedecine(){

        //find the Id of the button ok
        btnAddNewMedecine = (Button) findViewById(R.id.buttonOKNewMedecine);

        //new listener
        btnAddNewMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(MedecineAdd.this, ListOfMedecineActivity.class);

                //Get all (id) data of a madecine of the texte view
                EditText nameMedecine = (EditText)findViewById(R.id.editNameMedecineAdd);
                EditText typeMedecine = (EditText)findViewById(R.id.editTypeMedecineAdd);
                EditText ingredientMedecine = (EditText)findViewById(R.id.editActiveIngredientMedecineAdd);
                EditText manufacturerMedecine = (EditText)findViewById(R.id.editManufacturerMedecineAdd);
                EditText sideEffectMedecine = (EditText)findViewById(R.id.editSideEffect);
                EditText maxDayMedecine = (EditText)findViewById(R.id.editMaxDayMedecineAdd);
                EditText applicationMedecine = (EditText)findViewById(R.id.editApplicationMedecineAdd);

                //check if the user has correctly fiel the fields
                int error = 0;
                //Check name
                if (nameMedecine.getText().toString().length() == 0 ){
                    nameMedecine.setError(messageError);
                    nameMedecine.requestFocus();
                    error = 1;
                }
                //Check type
                if (typeMedecine.getText().toString().length() == 0 ){
                    typeMedecine.setError(messageError);
                    typeMedecine.requestFocus();
                    error = 1;
                }
                //Check ingredient
                if (ingredientMedecine.getText().toString().length() == 0 ){
                    ingredientMedecine.setError(messageError);
                    ingredientMedecine.requestFocus();
                    error = 1;
                }
                //check manufacturer
                if (manufacturerMedecine.getText().toString().length() == 0 ){
                    manufacturerMedecine.setError(messageError);
                    manufacturerMedecine.requestFocus();
                    error = 1;
                }
                //check side effects
                if (sideEffectMedecine.getText().toString().length() == 0 ){
                    sideEffectMedecine.setError(messageError);
                    sideEffectMedecine.requestFocus();
                    error = 1;
                }

                //check max day of this medecine
                if(maxDayMedecine.getText().toString().length() == 0){
                    maxDayMedecine.setError(messageError);
                    maxDayMedecine.requestFocus();
                    error = 1;
                }
                //check application
                if (applicationMedecine.getText().toString().length() == 0 ){
                    applicationMedecine.setError(messageError);
                    applicationMedecine.requestFocus();
                    error = 1;
                }

                //if error == 0 it means that the user has correctly fill all the fields
                if (error == 0){
                    medecineEntity = new MedecineEntity();
                    medecineEntity.setName(nameMedecine.getText().toString());
                    medecineEntity.setType(typeMedecine.getText().toString());
                    medecineEntity.setActiveIngredient(ingredientMedecine.getText().toString());
                    medecineEntity.setManufacturers(manufacturerMedecine.getText().toString());
                    medecineEntity.setSideEffects(sideEffectMedecine.getText().toString());
                    medecineEntity.setMaxPerDay(Integer.parseInt(maxDayMedecine.getText().toString()));
                    medecineEntity.setApplication(applicationMedecine.getText().toString());

                    //Call the method add Medecine
                    addMedecine(medecineEntity);
                    //MedecineAdd.this.startActivity(intent);
                    finish();
                }

            }
        });
    }

    //Add a new medecine with the class AsyncAddMedecine
    public void addMedecine(MedecineEntity medecineEntity){
        try {
            Long id = new AsyncAddMedecine(MedecineAdd.this, medecineEntity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_new_medecine);
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
