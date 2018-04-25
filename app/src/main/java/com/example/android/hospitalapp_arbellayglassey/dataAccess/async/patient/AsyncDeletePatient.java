package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;

public class AsyncDeletePatient extends AsyncTask<PatientEntity, Void, Void> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    private PatientEntity pe;

    //constructor of the AsyncAddMedecine
    public AsyncDeletePatient(Context c, PatientEntity pe) {
        mContext = new WeakReference<>(c);
        this.pe = pe;

    }
    //delete the patient in the db
    @Override
    protected Void doInBackground(PatientEntity... patientEntities) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        dbCreator.getDatabase().patientDao().deletePatient(pe);
        return null;
    }
}
