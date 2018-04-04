package com.example.android.hospitalapp_arbellayglassey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MedecineAddSearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add_search_list);


        final String [] medecineAddSearchList = getResources().getStringArray(R.array.medecine_array);
        ListView list;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listmedecineaddsearchlist_layout, medecineAddSearchList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                //If View doesn't exist create a new view
                if (convertView == null) {
                    // Create the Layout
                    LayoutInflater inflater = getLayoutInflater();
                    view = inflater.inflate(R.layout.listmedecineaddsearchlist_layout, parent, false);
                } else {
                    view = convertView;
                }

                //Add Text to the layout
                TextView textView1 = (TextView) view.findViewById(R.id.listview_listofmedecinefortreatment);
                textView1.setText(medecineAddSearchList[position]);

                return view;
            }
        };

        list = (ListView) findViewById(R.id.listofmedecinesearchlist);
        list.setAdapter(adapter);








    }
}