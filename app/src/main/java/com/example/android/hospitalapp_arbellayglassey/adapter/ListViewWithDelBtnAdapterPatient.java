package com.example.android.hospitalapp_arbellayglassey.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.patient.AsyncDeletePatient;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.PatientEntity;
import com.example.android.hospitalapp_arbellayglassey.patient.PatientDetails;

import java.util.ArrayList;
import java.util.List;

public class ListViewWithDelBtnAdapterPatient extends BaseAdapter implements ListAdapter {

    private int layout ;
    private int listViewLayout;

    private Context context;
    private Intent intent;
    List<PatientEntity> Entities;
    int idDelButton;


    // constructor to get all the necessary variables

    public ListViewWithDelBtnAdapterPatient( List<PatientEntity> Entities, Context context, Intent intent, int layout, int idListViewLayout, int idDelButton ) {

        this.context = context;
        this.intent = intent;
        this.layout = layout;
        this.listViewLayout = idListViewLayout;
        this.idDelButton = idDelButton;
        this.Entities = Entities;
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
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View view = convertView;

        // create the view if null
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
        }

        //Handle TextView and display string from your list
        TextView txtView = (TextView)view.findViewById(listViewLayout);
        txtView.setText(Entities.get(position).getName());


        //On click listener to get the correct details
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("idP", Entities.get(position).getIdP());
                context.startActivity(intent);
                Toast.makeText(context, "Object to see details: "+ Entities.get(position).getName(), Toast.LENGTH_LONG).show();


            }
        });

        //Handle buttons and add onClickListeners
        ImageButton delbtn= (ImageButton)view.findViewById(idDelButton);
        delbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // add a pop up when delete button is activated
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Do you really want to delete this patient ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(context, "Object to vanish: "+ Entities.get(position).getName(), Toast.LENGTH_SHORT).show();
                                intent.putExtra("idP", Entities.get(position).getIdP());
                                new AsyncDeletePatient(context, Entities.get(position)).execute();
                                Entities.remove(position);
                                notifyDataSetChanged();
                                // pour delete et rerfresh la liste en live, del ici la liste d'entity à la position puis refresh

                            }})
                        .setNegativeButton(android.R.string.no, null).show();


            }
        });


        return view;
    }
    public void refreshEvents(List<PatientEntity> patientEntities) {
        this.Entities.clear();
        this.Entities.addAll(patientEntities);
        notifyDataSetChanged();
    }
}
