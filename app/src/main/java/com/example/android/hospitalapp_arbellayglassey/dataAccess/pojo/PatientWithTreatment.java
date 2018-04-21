package com.example.android.hospitalapp_arbellayglassey.dataAccess.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;

import java.util.List;

public class PatientWithTreatment {

    @Embedded
    public PatientEntity patient;


    @Relation(parentColumn = "idT", entityColumn = "idTreatment", entity = TreatmentEntity.class)
            public List<TreatmentEntity> treatment;
}
