package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient;

import android.content.Context;
import android.os.AsyncTask;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;

public class GetPatient extends AsyncTask<Integer, Void, PatientEntity>

    {

        // Weak references will still allow the Activity to be garbage-collected
        private final WeakReference<Context> mContext;

        int id;

        public GetPatient(Context view, int id ) {
        mContext = new WeakReference<>(view);
        this.id = id;
    }


        @Override
        protected PatientEntity doInBackground(Integer... integers) {
            DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
            return dbCreator.getDatabase().patientDao().getByIdSync(id);
        }
    }
