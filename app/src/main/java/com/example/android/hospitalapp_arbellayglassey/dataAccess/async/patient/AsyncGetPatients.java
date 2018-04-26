package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;
import java.util.List;

public class AsyncGetPatients extends AsyncTask<Void,Void, List<PatientEntity>> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    //constructor AsyncGetPatients
    public AsyncGetPatients(Context c) {
        mContext = new WeakReference<>(c);
    }

    // get a list of patient from the db
    @Override
    protected List<PatientEntity> doInBackground(Void... voids) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().patientDao().getAllPatient();
    }
}
