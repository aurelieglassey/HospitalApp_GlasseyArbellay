package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.link;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;

import java.lang.ref.WeakReference;

public class AsyncDeleteLink extends AsyncTask<TreatmentMedecineLinkEntity, Void, Void> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    private TreatmentMedecineLinkEntity link;

    //constructor of the AsyncAddMedecine
    public AsyncDeleteLink(Context c, TreatmentMedecineLinkEntity link) {
        mContext = new WeakReference<>(c);
        this.link = link;

    }
    // delete the link
    @Override
    protected Void doInBackground(TreatmentMedecineLinkEntity... linkEntities) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        dbCreator.getDatabase().treatmentMedecineLinkDao().deleteTreatmentMedecine(link);
        return null;
    }
}
