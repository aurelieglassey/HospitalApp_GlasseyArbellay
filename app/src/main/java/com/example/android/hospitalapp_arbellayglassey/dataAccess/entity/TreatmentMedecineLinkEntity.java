package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

//LINK TABLE
@Entity(tableName = "TreatmentMedecineLink",

        //CANNOT ADD TWO PK
        //primaryKeys = {"idTreatment", "idMedecine"},
        indices = {@Index(value = {"idTreatment"})},
        //Link 2 foreign key from Treatment et medecine
        foreignKeys =
                //FK de Table Treatment
                {@ForeignKey(entity=TreatmentEntity.class, parentColumns = "idT", childColumns = "idTreatment", onDelete = CASCADE) ,
                        //FK de Table Medecine
                        @ForeignKey(entity = MedecineEntity.class, parentColumns = "idM", childColumns = "idMedecine", onDelete = CASCADE)})
public class TreatmentMedecineLinkEntity {

    // we need to add a link id to be sure, because when creating a link with the same pk it crash
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idL")
    private int idL;


    @ColumnInfo(name = "idTreatment")
    @Nullable
    private int idTreatment;

    @ColumnInfo(name = "idMedecine")
    @Nullable
    private int idMedecine;


    /*
    public TreatmentMedecineLinkEntity(int idTreatment, int idMedecine, String quantityPerDay) {
        this.idTreatment = idTreatment;
        this.idMedecine = idMedecine;

    }
    */
    // constructor
    public TreatmentMedecineLinkEntity() {

    }

    //Getters and setters

    public int getIdTreatment() {
        return idTreatment;
    }

    public int getIdMedecine() {
        return idMedecine;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public void setIdMedecine(int idMedecine) {
        this.idMedecine = idMedecine;
    }

    public int getIdL() {
        return idL;
    }

    public void setIdL(int idL) {
        this.idL = idL;
    }



}
