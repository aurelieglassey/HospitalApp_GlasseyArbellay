package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;

import java.lang.ref.WeakReference;


public class AsyncUpdateTreatment  extends AsyncTask<TreatmentEntity, Void, Void> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    public AsyncUpdateTreatment(Context context) {
        mContext = new WeakReference<>(context);
    }

    // update the treatments in the db in background
    @Override
    protected Void doInBackground(TreatmentEntity... params) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        for (TreatmentEntity treatmentEntity : params)
            dbCreator.getDatabase().treatmentDao().updatetTreatment(treatmentEntity);
        return null;
    }
}
