package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.Medecine;

public interface MedecineDao {

    @Insert
    void insertMedecine(Medecine medecine);

    @Delete
    void deleteMedecine(Medecine medecine);

    @Update
    void updateMedecine(Medecine medecine);

    //Get a list of all Medecine
    @Query("SELECT * FROM Medecine")
    public Medecine[] getAllMedecine();

    //Get a medecine from ID
    @Query("SELECT * FROM Medecine WHERE id = :idsearch")
    public Medecine getOneMedecineById(int idsearch);
}
