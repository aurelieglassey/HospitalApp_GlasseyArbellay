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
        primaryKeys = {"idTreatment", "idMedecine"},
        indices = {@Index(value = {"idTreatment"})},
        //Lien de 2 foreign key des tables Treatment et medecine
        foreignKeys =
                //FK de Table Treatment
                {@ForeignKey(entity=TreatmentEntity.class, parentColumns = "id", childColumns = "idTreatment", onDelete = CASCADE) ,
                        //FK de Table Medecine
                        @ForeignKey(entity = MedecineEntity.class, parentColumns = "id", childColumns = "idMedecine", onDelete = CASCADE)})
public class TreatmentMedecineLinkEntity {

    private int idTreatment;

    private int idMedecine;

    @ColumnInfo(name = "quantity_per_day")
    private String quantityPerDay;

    public TreatmentMedecineLinkEntity(int idTreatment, int idMedecine, String quantityPerDay) {
        this.idTreatment = idTreatment;
        this.idMedecine = idMedecine;
        this.quantityPerDay = quantityPerDay;
    }



    //Getters and setters

    public int getIdTreatment() {
        return idTreatment;
    }

    public int getIdMedecine() {
        return idMedecine;
    }

    public String getQuantityPerDay() {
        return quantityPerDay;
    }
}
