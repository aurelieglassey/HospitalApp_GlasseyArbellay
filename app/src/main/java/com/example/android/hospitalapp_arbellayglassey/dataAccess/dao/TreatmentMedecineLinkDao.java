package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;

import java.util.List;

@Dao
public interface TreatmentMedecineLinkDao {

    //Get all treatmentMedecine by an id treatment
    @Query("SELECT * FROM treatmentmedecinelink WHERE idTreatment = :idTreamtentSearch")
    public List<TreatmentMedecineLinkEntity> getAllTreatmentMedecineByIdTreatment(int idTreamtentSearch);

    @Insert
    public long insertTreatmentMedecine (TreatmentMedecineLinkEntity treatmentMedecineLink);

    @Delete
    public void deleteTreatmentMedecine (TreatmentMedecineLinkEntity treatmentMedecineLink);

    //insert lists inside the database
    @Insert(onConflict = OnConflictStrategy.REPLACE) //replace the old data and continue the transaction
    void insertAllLink(List<TreatmentMedecineLinkEntity> listLink);
}
