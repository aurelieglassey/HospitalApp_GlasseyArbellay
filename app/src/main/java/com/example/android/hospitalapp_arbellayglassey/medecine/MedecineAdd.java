package com.example.android.hospitalapp_arbellayglassey.medecine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncAddMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;

import java.util.concurrent.ExecutionException;

public class MedecineAdd extends AppCompatActivity {

    //Button to add a new Medecine
    private Button btnAddNewMedecine;
    private MedecineEntity medecineEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add);

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

                medecineEntity = new MedecineEntity();
                medecineEntity.setName(nameMedecine.getText().toString());
                medecineEntity.setType(typeMedecine.getText().toString());
                medecineEntity.setActiveIngredient(ingredientMedecine.getText().toString());
                medecineEntity.setManufacturers(manufacturerMedecine.getText().toString());
                medecineEntity.setSideEffects(sideEffectMedecine.getText().toString());
                medecineEntity.setMaxPerDay(Integer.parseInt(maxDayMedecine.getText().toString()));
                medecineEntity.setApplication(applicationMedecine.getText().toString());

                //check if the user has correctly fiel the fields
                int error = 0;
                if (nameMedecine.getText().toString().length() == 0 ){
                    nameMedecine.setError("Please enter a name");
                    nameMedecine.requestFocus();
                    error = 1;
                }
                if (typeMedecine.getText().toString().length() == 0 ){
                    typeMedecine.setError("Please enter a type");
                    typeMedecine.requestFocus();
                    error = 1;
                }
                if (ingredientMedecine.getText().toString().length() == 0 ){
                    ingredientMedecine.setError("Please enter a ingredient");
                    ingredientMedecine.requestFocus();
                    error = 1;
                }
                if (manufacturerMedecine.getText().toString().length() == 0 ){
                    manufacturerMedecine.setError("Please enter a manufacturer");
                    manufacturerMedecine.requestFocus();
                    error = 1;
                }
                if (sideEffectMedecine.getText().toString().length() == 0 ){
                    sideEffectMedecine.setError("Please enter side effects");
                    sideEffectMedecine.requestFocus();
                    error = 1;
                }
               /* if (maxDayMedecine.getText().toString().length() == 0 ){
                    maxDayMedecine.setError("Please enter max day");
                    maxDayMedecine.requestFocus();
                    error = 1;
                }
                */
                if (applicationMedecine.getText().toString().length() == 0 ){
                    applicationMedecine.setError("Please enter an application");
                    applicationMedecine.requestFocus();
                    error = 1;
                }

                if (error == 0){

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
}
