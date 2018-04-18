package com.example.android.hospitalapp_arbellayglassey.dataAccess;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInitUtil {

    //test
    static void initalizeDB (AppDatabase db){
        List<MedecineEntity> listMedecine = new ArrayList<MedecineEntity>();
        List<PatientEntity> listPatient = new ArrayList<PatientEntity>();

        generateData();
        insertData(db, listMedecine, listPatient);
    }

    private static void generateData(){

        PatientEntity p1 = new PatientEntity();
        p1.setName("Aurélie Glassey");
        p1.setGender('F');
        p1.setRoomNumber(302);
        p1.setBloodGroup("A");
        p1.setAge(21);
        p1.setReasonAdmission("blabla");
        p1.setIdTreatment(2);

        PatientEntity p2 = new PatientEntity();
        p2.setName("Olivier Arbellay");
        p2.setGender('M');
        p2.setRoomNumber(303);
        p2.setBloodGroup("O");
        p2.setAge(22);
        p2.setReasonAdmission("blabla");
        p2.setIdTreatment(4);

        PatientEntity p3 = new PatientEntity();
        p3.setName("Maud Rouvinez");
        p3.setGender('F');
        p3.setRoomNumber(304);
        p3.setBloodGroup("B+");
        p3.setAge(21);
        p3.setReasonAdmission("blabla");
        p3.setIdTreatment(5);

        MedecineEntity m1 = new MedecineEntity();
        m1.setName("Dafalgan");
        m1.setType("Analgesic");
        m1.setActiveIngredient("Paracetamol");
        m1.setApplication("Dilute in a glass of water");
        m1.setManufacturers("Brisol-myers");
        m1.setSideEffects("Vomi");
        m1.setMaxPerDay(3);

        MedecineEntity m2 = new MedecineEntity();
        m2.setName("Neocitran");
        m2.setType("Chloryhdrate de pseudoéphédrine");
        m2.setActiveIngredient("Paracetamol");
        m2.setApplication("Dilute in a glass of water");
        m2.setManufacturers("Sun Store");
        m2.setSideEffects("Asthme");
        m2.setMaxPerDay(2);

    }

    private static void insertData(AppDatabase db, List<MedecineEntity> lm, List<PatientEntity> lp){

        db.beginTransaction();

           //pas finit

    }


}
