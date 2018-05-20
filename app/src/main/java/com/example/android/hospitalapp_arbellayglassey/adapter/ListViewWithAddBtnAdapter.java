package com.example.android.hospitalapp_arbellayglassey.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ListViewWithAddBtnAdapter extends BaseAdapter implements ListAdapter {

    //variable of the adapter
    private int layout ;
    private int listViewLayout;

    private Context context;
    private Intent intent;
    List<MedecineEntity> Entities;
   // private String idT;
    private String idP;
    int idAddButton;

    // constructor to get all the necessary variables

    public ListViewWithAddBtnAdapter(List<MedecineEntity> Entities,  String idP, Context context,  int layout, int idListViewLayout, int idAddButton) {

        this.context = context;
        this.intent = intent;
        this.layout = layout;
        this.listViewLayout = idListViewLayout;
        this.idAddButton = idAddButton;
        this.Entities = Entities;
        this.idP = idP;
    }

    @Override
    public int getCount() {
        return Entities.size();
    }

    @Override
    public Object getItem(int pos) {
        return Entities.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return  0; // list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        // create the view if null
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
        }

        //Handle TextView and display string from your list
        TextView txtView = (TextView)view.findViewById(listViewLayout);
        txtView.setText(Entities.get(position).getName());



        //Handle buttons and add onClickListeners
        ImageButton addBtn= (ImageButton)view.findViewById(idAddButton);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // make a toast
                Toast.makeText(context, "Object to add: "+ Entities.get(position).getName(), Toast.LENGTH_LONG).show();
                TreatmentMedecineLinkEntity linkEn = new TreatmentMedecineLinkEntity();
                // set the id to the link

               // linkEn.setIdMedecine(Entities.get(position).getIdM());
                //linkEn.setIdTreatment(idT);
                addLinkInFirebase(linkEn,position);

            }
        });

        return view;
    }

    private void addLinkInFirebase(TreatmentMedecineLinkEntity entity, int pos) {
        entity.setIdL(UUID.randomUUID().toString());
        entity.setIdM(Entities.get(pos).getIdM());
        FirebaseDatabase.getInstance()
                .getReference("Patients")
                .child(idP)
                .child("treatment")
                .child("links")
                .child(entity.getIdL())
                .setValue(entity, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d("addLinkAdapter", "Firebase DB Insert failure!", databaseError.toException());
                            ((Activity)context).finish();

                        } else {
                            Log.d("addLinkAdapter", "Firebase DB Insert successful!");
                            ((Activity)context).finish();
                        }
                    }
                });
    }





    //get new value to refresh the list
    public void refreshEvents(List<MedecineEntity> medecineEntities) {
        this.Entities.clear();
        this.Entities.addAll(medecineEntities);
        notifyDataSetChanged();
    }


}
