package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

//Table de jointure entre Treatment et Medecine
@Entity(tableName = "TreatmentMedecineLink",
        //Les indices sont un lien unique entre les tables Treatment et Medecine par lequel on ajoute les propriétés d'un index
        primaryKeys = {"idTreatment", "idMedecine"},
        indices = {@Index(value = {"idTreatment"})},
        //Lien de 2 foreign key des tables Treatment et medecine
        foreignKeys =
                //FK de Table Treatment
                {@ForeignKey(entity=TreatmentEntity.class, parentColumns = "idT", childColumns = "idTreatment", onDelete = CASCADE) ,
                        //FK de Table Medecine
                        @ForeignKey(entity = MedecineEntity.class, parentColumns = "idM", childColumns = "idMedecine", onDelete = CASCADE)})
public class TreatmentMedecineLinkEntity {

    @ColumnInfo(name = "idTreatment")
    @Nullable
    private int idTreatment;

    @ColumnInfo(name = "idMedecine")
    @Nullable
    private int idMedecine;

//    @ColumnInfo(name = "quantity_per_day")//   private String quantityPerDay;

    public TreatmentMedecineLinkEntity(int idTreatment, int idMedecine, String quantityPerDay) {
        this.idTreatment = idTreatment;
        this.idMedecine = idMedecine;
        //this.quantityPerDay = quantityPerDay;
    }

    public TreatmentMedecineLinkEntity() {

    }

    //Getters and setters

    public int getIdTreatment() {
        return idTreatment;
    }

    public int getIdMedecine() {
        return idMedecine;
    }

    //public String getQuantityPerDay() {return quantityPerDay;}

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    public void setIdMedecine(int idMedecine) {
        this.idMedecine = idMedecine;
    }

    //public void setQuantityPerDay(String quantityPerDay) {this.quantityPerDay = quantityPerDay;}
}
