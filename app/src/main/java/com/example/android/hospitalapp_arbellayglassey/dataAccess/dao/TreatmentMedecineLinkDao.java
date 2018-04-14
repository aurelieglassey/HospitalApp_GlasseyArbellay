package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.Patient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLink;

public interface TreatmentMedecineLinkDao {

    //Get all treatmentMedecine by an id treatment
    @Query("SELECT * FROM treatmentmedecinelink WHERE idTreatment = :idTreamtentSearch")
    public TreatmentMedecineLink getAllTreatmentMedecineByIdTreatment(int idTreamtentSearch);

    @Insert
    public void insertTreatmentMedecine (TreatmentMedecineLink treatmentMedecineLink);

    @Delete
    public void deleteTreatmentMedecine (TreatmentMedecineLink treatmentMedecineLink);
}
