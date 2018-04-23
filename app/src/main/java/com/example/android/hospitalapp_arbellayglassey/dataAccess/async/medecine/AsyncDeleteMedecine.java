package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;

import java.lang.ref.WeakReference;

public class AsyncDeleteMedecine extends AsyncTask<MedecineEntity, Void, Void> {

    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<Context> mContext;

    private MedecineEntity me;

    //constructor of the AsyncAddMedecine
    public AsyncDeleteMedecine(Context c, MedecineEntity me) {
        mContext = new WeakReference<>(c);
        this.me = me;

    }

    @Override
    protected Void doInBackground(MedecineEntity... medecineEntities) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        dbCreator.getDatabase().medecineDao().deleteMedecine(me);
        return null;
    }
}
