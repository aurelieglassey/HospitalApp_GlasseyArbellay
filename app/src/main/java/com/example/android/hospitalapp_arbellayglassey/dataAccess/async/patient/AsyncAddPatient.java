package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;

public class AsyncAddPatient extends AsyncTask<PatientEntity, Void, Long> {



    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    private PatientEntity patientEntity;
    public AsyncAddPatient(Context c, PatientEntity patientEntity) {
        mContext = new WeakReference<>(c);
        this.patientEntity = patientEntity;
    }

    @Override
    protected Long doInBackground(PatientEntity... params) throws SQLiteConstraintException {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().patientDao().insertPatient(patientEntity);

    }
}
