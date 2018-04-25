package com.example.android.hospitalapp_arbellayglassey.dataAccess;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.dao.MedecineDao;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.dao.PatientDao;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.dao.TreatmentDao;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.dao.TreatmentMedecineLinkDao;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;


@Database(entities = {PatientEntity.class, MedecineEntity.class, TreatmentEntity.class, TreatmentMedecineLinkEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    // the db link to the differnet entity

    static final String DATABASE_NAME = "hospital_database";


    //DAO
    public abstract PatientDao patientDao();
    public abstract MedecineDao medecineDao();
    public abstract TreatmentDao treatmentDao();
    public abstract TreatmentMedecineLinkDao treatmentMedecineLinkDao();


}
