package com.example.android.hospitalapp_arbellayglassey.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

//Table de jointure entre Treatment et Medecine
@Entity(tableName = "TreatmentMedecineLink",
        //Les indices sont un lien unique entre les tables Treatment et Medecine par lequel on ajoute les propriétés d'un index
        indices = {@Index("name"), @Index(value = {"idTreatment", "idMedecine"})},
        //Lien de 2 foreign key des tables Treatment et medecine
        foreignKeys =
                //FK de Table Treatment
                {@ForeignKey(entity=Treatment.class, parentColumns = "id", childColumns = "idTreatment") ,
                        //FK de Table Medecine
                        @ForeignKey(entity = Medecine.class, parentColumns = "id", childColumns = "idPatient")})

public class TreatmentMedecineLink {

    @PrimaryKey(autoGenerate = true)
    private int idTreatment;

    @PrimaryKey(autoGenerate = true)
    private int idMedecine;

    @ColumnInfo(name = "quantity_per_day")
    private String quantityPerDay;


}
