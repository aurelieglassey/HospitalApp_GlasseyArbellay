package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;

import java.util.List;

@Dao
public interface MedecineDao {

    @Insert
    void insertMedecine(MedecineEntity medecine);

    @Delete
    void deleteMedecine(MedecineEntity medecine);

    @Update
    void updateMedecine(MedecineEntity medecine);

    //Get a list of all Medecine
    @Query("SELECT * FROM Medecine")
    public List<MedecineEntity> getAllMedecine();

    //Get a medecine from ID
    @Query("SELECT * FROM Medecine WHERE id = :idsearch")
    public MedecineEntity getOneMedecineById(int idsearch);
}
