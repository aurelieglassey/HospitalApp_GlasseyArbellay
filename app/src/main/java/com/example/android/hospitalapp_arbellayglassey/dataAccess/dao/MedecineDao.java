package com.example.android.hospitalapp_arbellayglassey.dataAccess.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.medecine.MedecineAdd;

import java.util.List;

@Dao
public interface MedecineDao {

    //insert medecine
    @Insert
    long insertMedecine(MedecineEntity medecine);
    //delete medecine
    @Delete
    void deleteMedecine(MedecineEntity medecine);
    //update medecine
    @Update
    void updateMedecine(MedecineEntity medecine);

    //Get a list of all Medecine
    @Query("SELECT * FROM Medecine ORDER BY name")
    public List<MedecineEntity> getAllMedecine();

    //Get a medecine from ID
    @Query("SELECT * FROM Medecine WHERE idM = :idsearch")
    public MedecineEntity getOneMedecineById(int idsearch);

    //Get a medecine from his id
    @Query("SELECT * FROM Medecine WHERE idM = :id")
    MedecineEntity getByIdSync(int id);

    //Insert list inside the database
    @Insert(onConflict = OnConflictStrategy.REPLACE) //replace the old data and continue the transaction
    void insertAllMedecine(List<MedecineEntity> listMedecine);


}
