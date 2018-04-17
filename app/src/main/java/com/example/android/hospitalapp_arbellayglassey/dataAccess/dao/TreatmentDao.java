package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;

@Dao
public interface TreatmentDao {

    @Insert
    void insertTreatmentt(TreatmentEntity treatment);

    @Delete
    void deleteTreatmentt(TreatmentEntity treatment);

    @Update
    void updatetTreatmentt(TreatmentEntity treatment);

    //Get a treatment by idPatient
    @Query("SELECT * FROM Treatment WHERE id = :idPatient")
    public TreatmentEntity getTreatmentByPatientId(int idPatient);

}
