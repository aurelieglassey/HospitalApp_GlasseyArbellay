package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;

public class AsyncAddMedecine extends AsyncTask<MedecineEntity, Void, Long> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    private MedecineEntity medecineEntity;

    //constructor of the AsyncAddMedecine
    public AsyncAddMedecine(Context c, MedecineEntity medecineEntity) {
        mContext = new WeakReference<>(c);
        this.medecineEntity = medecineEntity;
    }

    //Call the DB and insert a new medecine in the DB
    @Override
    protected Long doInBackground(MedecineEntity... medecineEntities) throws SQLiteConstraintException {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().medecineDao().insertMedecine(medecineEntity);

    }
}
