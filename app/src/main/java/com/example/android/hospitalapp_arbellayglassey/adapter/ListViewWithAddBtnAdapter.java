package com.example.android.hospitalapp_arbellayglassey.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.link.AsyncAddLink;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentEntity;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.TreatmentMedecineLinkEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListViewWithAddBtnAdapter extends BaseAdapter implements ListAdapter {

    //variable of the adapter
    private int layout ;
    private int listViewLayout;

    private Context context;
    private Intent intent;
    List<MedecineEntity> Entities;
    private int idT;
    private int idP;
    int idAddButton;

    // constructor to get all the necessary variables

    public ListViewWithAddBtnAdapter(List<MedecineEntity> Entities, int idT, int idP, Context context,  int layout, int idListViewLayout, int idAddButton) {

        this.context = context;
        this.intent = intent;
        this.layout = layout;
        this.listViewLayout = idListViewLayout;
        this.idAddButton = idAddButton;
        this.Entities = Entities;
        this.idT = idT;
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
                linkEn.setIdMedecine(Entities.get(position).getIdM());
                linkEn.setIdTreatment(idT);

                //db access
                try {
                    new AsyncAddLink(context, linkEn).execute().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                ((Activity)context).finish();

            }
        });


        return view;
    }
}
