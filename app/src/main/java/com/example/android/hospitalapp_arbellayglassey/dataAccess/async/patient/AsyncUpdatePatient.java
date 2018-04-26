package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient;

import android.content.Context;
import android.os.AsyncTask;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;

public class AsyncUpdatePatient extends AsyncTask<PatientEntity, Void, Void> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    //Constructor of AsyncUpdatePatient
    public AsyncUpdatePatient(Context context) {
        mContext = new WeakReference<>(context);
    }

    // update the patient in the db
    @Override
    protected Void doInBackground(PatientEntity... params) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        for (PatientEntity patientEntity : params)
            dbCreator.getDatabase().patientDao().updatePatient(patientEntity);
        return null;
    }
}
