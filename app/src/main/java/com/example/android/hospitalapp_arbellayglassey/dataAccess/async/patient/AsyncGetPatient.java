package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient;

import android.content.Context;
import android.os.AsyncTask;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;

public class AsyncGetPatient extends AsyncTask<Integer, Void, PatientEntity>

    {

        // Weak references will still allow the Activity to be garbage-collected
        private final WeakReference<Context> mContext;

        private int id;


        //Constructor
        public AsyncGetPatient(Context context, int id ) {
        mContext = new WeakReference<>(context);
        this.id = id;
    }


    //call the db and edit a patient
        @Override
        protected PatientEntity doInBackground(Integer... integers) {
            DatabaseCreator dbCreator = DatabaseCreator.getInstance(mContext.get().getApplicationContext());
            return dbCreator.getDatabase().patientDao().getByIdSync(id);
        }
    }
