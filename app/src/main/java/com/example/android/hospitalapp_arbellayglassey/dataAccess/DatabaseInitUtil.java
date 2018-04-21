package com.example.android.hospitalapp_arbellayglassey.dataAccess;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInitUtil {

   protected static List<MedecineEntity> lm;
   protected static List<PatientEntity> lp;
   protected static List<TreatmentEntity> lt;
   protected static List<TreatmentMedecineLinkEntity> ll;
    //Initalize the database
    static void initializeDb (AppDatabase db){

        //Create lists
       lm = new ArrayList<MedecineEntity>();
        lp = new ArrayList<PatientEntity>();
        lt = new ArrayList<TreatmentEntity>();
        ll = new ArrayList<TreatmentMedecineLinkEntity>();

        //Generate some data and insert them inside a database
        generateData();
        insertData(db);
    }

    //Create some data for the 2 lists
    private static void generateData(){

        //Create medecine
        MedecineEntity m1 = new MedecineEntity();
        m1.setIdM(0);
        m1.setName("Dafalgan");
        m1.setType("Analgesic");
        m1.setActiveIngredient("Paracetamol");
        m1.setApplication("Dilute in a glass of water");
        m1.setManufacturers("Brisol-myers");
        m1.setSideEffects("Vomi");
        m1.setMaxPerDay(3);

        MedecineEntity m2 = new MedecineEntity();
        m2.setIdM(1);
        m2.setName("Neocitran");
        m2.setType("Chloryhdrate de pseudoéphédrine");
        m2.setActiveIngredient("Paracetamol");
        m2.setApplication("Dilute in a glass of water");
        m2.setManufacturers("Sun Store");
        m2.setSideEffects("Asthme");
        m2.setMaxPerDay(2);

        //Add the Medecine created in the list of medecine lm
        lm.add(m1);
        lm.add(m2);


        TreatmentEntity t1 = new TreatmentEntity();
        t1.setIdT(0);
        t1.setName("Aurelie_Treatment");
        t1.setIdPatient(0);

        TreatmentEntity t2 = new TreatmentEntity();
        t2.setIdT(1);
        t2.setName("Olivier_Treatment");
        t2.setIdPatient(1);

        TreatmentEntity t3 = new TreatmentEntity();
        t3.setIdT(2);
        t3.setName("Maud_Treatment");
        t3.setIdPatient(2);

        lt.add(t1);
        lt.add(t2);
        lt.add(t3);

        TreatmentMedecineLinkEntity tmtl = new TreatmentMedecineLinkEntity();
        tmtl.setIdMedecine(1);
        tmtl.setIdTreatment(1);
        tmtl.setQuantityPerDay("2");

        TreatmentMedecineLinkEntity tmt2 = new TreatmentMedecineLinkEntity();
        tmt2.setIdMedecine(2);
        tmt2.setIdTreatment(3);
        tmt2.setQuantityPerDay("2");

        ll.add(tmtl);
        ll.add(tmt2);

        //Create patients
        PatientEntity p1 = new PatientEntity();
        p1.setIdP(1);
        p1.setName("Aurélie Glassey");
        p1.setGender('F');
        p1.setRoomNumber(302);
        p1.setBloodGroup("A");
        p1.setAge(21);
        p1.setReasonAdmission("blabla");

        PatientEntity p2 = new PatientEntity();
        p2.setIdP(2);
        p2.setName("Olivier Arbellay");
        p2.setGender('M');
        p2.setRoomNumber(303);
        p2.setBloodGroup("O");
        p2.setAge(22);
        p2.setReasonAdmission("blabla");

        PatientEntity p3 = new PatientEntity();
        p3.setIdP(3);
        p3.setName("Maud Rouvinez");
        p3.setGender('F');
        p3.setRoomNumber(304);
        p3.setBloodGroup("B+");
        p3.setAge(21);
        p3.setReasonAdmission("blabla");

        //Add the patients created in the list of patient lp
        lp.add(p1);
        lp.add(p2);
        lp.add(p3);



    }

    //Inserts the list inside the database
    private static void insertData(AppDatabase db) {

        db.beginTransaction();

        try {
            db.medecineDao().insertAllMedecine(lm);
           db.patientDao().insertAllPatient(lp);
            //db.treatmentMedecineLinkDao().insertAllLink(ll);
            //db.treatmentDao().insertAllTreatment(lt);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


}
