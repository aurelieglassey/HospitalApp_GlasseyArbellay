package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

//Table de jointure entre Treatment et Medecine
@Entity(tableName = "TreatmentMedecineLink",
        //Les indices sont un lien unique entre les tables Treatment et Medecine par lequel on ajoute les propriétés d'un index
        indices = {@Index("name"), @Index(value = {"idTreatment", "idMedecine"})},
        //Lien de 2 foreign key des tables Treatment et medecine
        foreignKeys =
                //FK de Table Treatment
                {@ForeignKey(entity=Treatment.class, parentColumns = "id", childColumns = "idTreatment", onDelete = CASCADE) ,
                        //FK de Table Medecine
                        @ForeignKey(entity = Medecine.class, parentColumns = "id", childColumns = "idPatient", onDelete = CASCADE)})

public class TreatmentMedecineLink {

    @PrimaryKey(autoGenerate = true)
    private int idTreatment;

    @PrimaryKey(autoGenerate = true)
    private int idMedecine;

    @ColumnInfo(name = "quantity_per_day")
    private String quantityPerDay;


    //Getters and setters
    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public int getIdMedecine() {
        return idMedecine;
    }

    public void setIdMedecine(int idMedecine) {
        this.idMedecine = idMedecine;
    }

    public String getQuantityPerDay() {
        return quantityPerDay;
    }

    public void setQuantityPerDay(String quantityPerDay) {
        this.quantityPerDay = quantityPerDay;
    }
}
