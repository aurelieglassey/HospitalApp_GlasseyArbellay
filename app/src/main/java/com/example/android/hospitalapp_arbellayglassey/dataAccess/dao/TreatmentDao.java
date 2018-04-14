package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.Patient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.Treatment;

public interface TreatmentDao {

    @Insert
    void insertTreatmentt(Treatment treatment);

    @Delete
    void deleteTreatmentt(Treatment treatment);

    @Update
    void updatetTreatmentt(Treatment treatment);

    //Get a treatment by idPatient
    @Query("SELECT * FROM Treatment WHERE id = :idPatient")
    public Treatment getTreatmentByPatientId(int idPatient);

}
