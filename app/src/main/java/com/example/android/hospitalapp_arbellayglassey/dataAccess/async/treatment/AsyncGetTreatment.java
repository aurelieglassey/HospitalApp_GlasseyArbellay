package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;

import java.lang.ref.WeakReference;

public class AsyncGetTreatment extends AsyncTask<Integer, Void, TreatmentEntity>{

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    private int id;

    //constructor of AsyncGetTreatment
    public AsyncGetTreatment(Context view, int id ) {
        mContext = new WeakReference<>(view);
        this.id = id;
    }

  // get a tratment by id in background
    @Override
    protected TreatmentEntity doInBackground(Integer... integers) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().treatmentDao().getTreatmentByPatientId(id);
    }
}
