package com.example.android.hospitalapp_arbellayglassey;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewWithDelBtnAdapter extends BaseAdapter implements ListAdapter {

    private int layout ;
    private int listViewLayout;
    private ArrayList<String> list ;
    private Context context;
    private Intent intent;

    // constructor to get all the necessary variables

    public ListViewWithDelBtnAdapter(ArrayList<String> list, Context context, Intent intent, int layout, int idListViewLayout) {
        this.list = list;
        this.context = context;
        this.intent = intent;
        this.layout = layout;
        this.listViewLayout = idListViewLayout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
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
        txtView.setText(list.get(position));

        //On click listener to get the correct details
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
                Toast.makeText(context, "Object to see details: "+ list.get(position).toString(), Toast.LENGTH_LONG).show();

            }
        });

        //Handle buttons and add onClickListeners
        ImageButton delbtn= (ImageButton)view.findViewById(R.id.deletePatientButton);
        delbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Object to vanish: "+ list.get(position).toString(), Toast.LENGTH_LONG).show();

            }
        });


        return view;
    }
}