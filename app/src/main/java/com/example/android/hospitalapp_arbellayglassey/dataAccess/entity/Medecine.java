package com.example.android.hospitalapp_arbellayglassey.dataAccess.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Medecine")
public class Medecine {

    //test
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "active_ingredient")
    private String activeIngredient;

    @ColumnInfo(name = "manufacturers")
    private String manufacturers;

    @ColumnInfo(name = "application")
    private String application;

    @ColumnInfo(name = "side_effects")
    private String sideEffects;

    @ColumnInfo(name = "max_per_day")
    private int maxPerDay;

}
