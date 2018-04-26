package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.link;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;

import java.lang.ref.WeakReference;

public class AsyncAddLink extends AsyncTask<TreatmentMedecineLinkEntity, Void, Long> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    private TreatmentMedecineLinkEntity linkEntity;

    //constructor of the AsyncAddLink
    public AsyncAddLink(Context c, TreatmentMedecineLinkEntity linkEntity) {
        mContext = new WeakReference<>(c);
        this.linkEntity = linkEntity;
    }

    //Call the DB and insert a new link in the DB
    @Override
    protected Long doInBackground(TreatmentMedecineLinkEntity... patientEntities) throws SQLiteConstraintException {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().treatmentMedecineLinkDao().insertTreatmentMedecine(linkEntity);

    }
}
