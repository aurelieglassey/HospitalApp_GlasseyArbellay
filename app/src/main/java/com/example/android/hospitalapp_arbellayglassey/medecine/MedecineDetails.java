package com.example.android.hospitalapp_arbellayglassey.medecine;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;

import java.util.concurrent.ExecutionException;

public class MedecineDetails extends AppCompatActivity {

    //Variables
    private ImageButton btnModifyMedecine;
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
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //find the textview by his id
        textViewName = findViewById(R.id.textNameMedecineDetails);
        textViewType = findViewById(R.id.textTypeMedecineDetails);
        textViewIngredient = findViewById(R.id.textActiveIngredientMedecineDetails);
        textViewManufacturer = findViewById(R.id.textManufacturerMedecineDetails);
        textViewEffects = findViewById(R.id.textSideEffectMedecineDetails);
        textViewMaxDay = findViewById(R.id.textMaxDayMedecineDetails);
        textViewApplication = findViewById(R.id.textApplicationMedecineDetails);


        textViewName.setText(medecineEntity.getName());
        textViewType.setText(medecineEntity.getType());
        textViewIngredient.setText(medecineEntity.getActiveIngredient());
        textViewManufacturer.setText(medecineEntity.getManufacturers());
        textViewEffects.setText(medecineEntity.getSideEffects());
        textViewMaxDay.setText(String.valueOf(medecineEntity.getMaxPerDay()));
        textViewApplication.setText(medecineEntity.getApplication());



        pressBtnModifyTreatment();
    }


    //When the user decide to modify the data of a medecine
    public void pressBtnModifyTreatment(){

        //Find the id view for the button modify the data of a medecine
        btnModifyMedecine = (ImageButton) findViewById(R.id.btn_modify_MedecineDetails);

        //Add a listener to modify the data of a medecine
        btnModifyMedecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedecineDetails.this, MedecineModify.class);
                MedecineDetails.this.startActivity(intent);
            }
        });

    }

    //Read the db
    public void readDB() throws ExecutionException, InterruptedException {

        DatabaseCreator dbCreator = DatabaseCreator.getInstance(MedecineDetails.this);

        Intent intentGetId = getIntent();
        idMedecine = intentGetId.getIntExtra("idM", 0);

        medecineEntity = new AsyncGetMedecine(MedecineDetails.this, idMedecine).execute().get();


    }
}
