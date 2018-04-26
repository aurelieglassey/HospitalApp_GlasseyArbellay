package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;

import java.lang.ref.WeakReference;

public class AsyncGetMedecine extends AsyncTask<Integer, Void, MedecineEntity> {

    private final WeakReference<Context> mContext;

    private int id;

    //Constructor
    public AsyncGetMedecine(Context c, int id){
        mContext = new WeakReference<>(c);;
        this.id = id;
    }


    //Get a medecine form his ID
    @Override
    protected MedecineEntity doInBackground(Integer... integers) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
        return dbCreator.getDatabase().medecineDao().getByIdSync(id);
    }
}
