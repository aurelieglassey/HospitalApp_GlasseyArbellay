package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.link;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;

import java.lang.ref.WeakReference;
import java.util.List;

public class AsyncGetLinks extends AsyncTask<Void,Void, List<TreatmentMedecineLinkEntity>> {


    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;
    private int idT;

    public AsyncGetLinks(Context c, int idT) {
        mContext = new WeakReference<>(c);
        this.idT = idT;
    }

    // get the links in the data base doing it in background
    @Override
    protected List<TreatmentMedecineLinkEntity> doInBackground(Void... voids) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().treatmentMedecineLinkDao().getAllTreatmentMedecineByIdTreatment(idT);
    }

}
