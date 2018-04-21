package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetMedecines extends AsyncTask<Void,Void, List<MedecineEntity>> {


// Weak references will still allow the Activity to be garbage-collected
private final WeakReference<Context> mContext;

public GetMedecines(Context c) {
        mContext = new WeakReference<>(c);
        }
@Override
protected List<MedecineEntity> doInBackground(Void... voids) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().medecineDao().getAllMedecine();
        }
}
