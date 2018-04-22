package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.treatment;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;

import java.lang.ref.WeakReference;

public class AsyncAddTreatment extends AsyncTask<TreatmentEntity, Void, Long> {

    private final WeakReference<Context> mContext;

    private TreatmentEntity treatmentEntity;

    //constructor
    public AsyncAddTreatment(Context context, TreatmentEntity treatmentEntity) {
        this.mContext = new WeakReference<>(context);
        this.treatmentEntity = treatmentEntity;
    }

    //Call the db and insert a treatment inside
    @Override
    protected Long doInBackground(TreatmentEntity... treatmentEntities) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().treatmentDao().insertTreatment(treatmentEntity);
    }
}
