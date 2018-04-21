package com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient;

import android.os.AsyncTask;
import android.view.View;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetPatients extends AsyncTask<Void,Void, List<PatientEntity>> {


    // Weak references will still allow the Activity to be garbage-collected
    private final WeakReference<View> mView;

    public GetPatients(View view) {
        mView = new WeakReference<>(view);
    }
    @Override
    protected List<PatientEntity> doInBackground(Void... voids) {
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(mView.get().getContext());
        return dbCreator.getDatabase().patientDao().getAllPatient();
    }
}
