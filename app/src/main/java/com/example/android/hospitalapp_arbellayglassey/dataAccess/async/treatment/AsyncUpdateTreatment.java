package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;

import java.lang.ref.WeakReference;


public class AsyncUpdateTreatment  extends AsyncTask<TreatmentEntity, Void, Void> {

    private final WeakReference<Context> mContext;

    public AsyncUpdateTreatment(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    protected Void doInBackground(TreatmentEntity... params) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        for (TreatmentEntity treatmentEntity : params)
            dbCreator.getDatabase().treatmentDao().updatetTreatment(treatmentEntity);
        return null;
    }
}
