package com.example.android.hospitalapp_arbellayglassey.treatment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithDelBtnAdapterMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.link.AsyncGetLinks;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecine;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncGetPatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment.AsyncGetTreatment;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineAddSearchList;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineDetails;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TreatmentDetails extends AppCompatActivity {


    //Button to add a medecine to a treatment
    private Button btnAddMedecineToTreatment;
    //Button to modify a treatment
    private ImageButton btnModifyTreatment;
    private TreatmentEntity treatmentEntity;
    private PatientEntity patientEntity;
    private List<TreatmentMedecineLinkEntity>  listLinkEntity;
    private int idPatient;
    private TextView textViewAdmission;
    private TextView textViewName;
    ArrayList<String> medecines;
    //private TextView textViewAdmission;


    List<MedecineEntity> medecineEntityList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_details);

        textViewAdmission = findViewById(R.id.AdmissionTreatmentDetails);
        textViewName = findViewById(R.id.nameTreatmentDetails);

        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textViewName.setText(treatmentEntity.getName());
        textViewAdmission.setText(patientEntity.getReasonAdmission());

        //Add a medecine to the treatment of a patient
        pressAddMedecineToTreatment();
        pressBtnModifyTreatment();

        // adding list
        //final ArrayList<String> medecine = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.medecine_array)));
        ListView list;


        Intent intent = new Intent(TreatmentDetails.this, MedecineDetails.class);
        list = (ListView) findViewById(R.id.listofmedicinefortreatment);
        list.setAdapter(new ListViewWithDelBtnAdapterMedecine( medecineEntityList,TreatmentDetails.this, intent, R.layout.listofmedicinefortreatment_layout, R.id.listview_listofmedecinefortreatment, R.id.deleteMedecineForTreatmentButton));
    }


    //When the user decide to add medecine to a treatment
    public void pressAddMedecineToTreatment(){
        //Find the id view for the button add a medecine to a treatment
        btnAddMedecineToTreatment = (Button) findViewById(R.id.btn_add_medicine);

        //Add a listener to access to the medecine add search activity
        btnAddMedecineToTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentDetails.this, MedecineAddSearchList.class);
                intent.putExtra("idT", treatmentEntity.getIdT());
                intent.putExtra("idP", idPatient);
                TreatmentDetails.this.startActivity(intent);
            }
        });
    }

    //When the user decide to modify the treatment of a patient
    public void pressBtnModifyTreatment(){

        //Find the id view for the button modify the treatment
        btnModifyTreatment = (ImageButton) findViewById(R.id.btn_modify_treatment);

        //Add a listener to modify the treatment of a patient
        btnModifyTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreatmentDetails.this, TreatmentModify.class);
                TreatmentDetails.this.startActivity(intent);
            }
        });

    }

    public void readDB() throws ExecutionException, InterruptedException {



        Intent intentGetId = getIntent();
        idPatient = intentGetId.getIntExtra("idP", 0);
        treatmentEntity = new AsyncGetTreatment(TreatmentDetails.this, idPatient).execute().get();
        patientEntity = new AsyncGetPatient(TreatmentDetails.this, idPatient).execute().get();
        listLinkEntity = new AsyncGetLinks(TreatmentDetails.this, treatmentEntity.getIdT()).execute().get();
        medecines = new ArrayList<>();
        for (TreatmentMedecineLinkEntity linkEntity: listLinkEntity) {
            medecines.add(new AsyncGetMedecine(TreatmentDetails.this,linkEntity.getIdMedecine()).execute().get().getName() );
        }


    }

}
