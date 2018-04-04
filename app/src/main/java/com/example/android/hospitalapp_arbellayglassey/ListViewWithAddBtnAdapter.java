package com.example.android.hospitalapp_arbellayglassey;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewWithAddBtnAdapter extends BaseAdapter implements ListAdapter {
    private int layout ;
    private int listViewLayout;
    private ArrayList<String> list ;
    private Context context;
    private Intent intent;
    int idAddButton;

    // constructor to get all the necessary variables

    public ListViewWithAddBtnAdapter(ArrayList<String> list, Context context, Intent intent, int layout, int idListViewLayout, int idAddButton) {
        this.list = list;
        this.context = context;
        this.intent = intent;
        this.layout = layout;
        this.listViewLayout = idListViewLayout;
        this.idAddButton = idAddButton;
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



        //Handle buttons and add onClickListeners
        ImageButton addBtn= (ImageButton)view.findViewById(idAddButton);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Object to add: "+ list.get(position).toString(), Toast.LENGTH_LONG).show();
                context.startActivity(intent);

            }
        });


        return view;
    }
}
