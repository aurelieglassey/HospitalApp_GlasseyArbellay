package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;

import java.lang.ref.WeakReference;

public class AsyncUpdateMedecine extends AsyncTask<MedecineEntity, Void, Void> {

    private final WeakReference<Context> mContext;

    public AsyncUpdateMedecine (Context context){
        this.mContext = new WeakReference<>(context);
    }


    @Override
    protected Void doInBackground(MedecineEntity... params) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        for (MedecineEntity medecineEntity : params)
            dbCreator.getDatabase().medecineDao().updateMedecine(medecineEntity);
        return null;
    }
}
