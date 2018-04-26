package com.example.android.hospitalapp_arbellayglassey.medecine;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.android.hospitalapp_arbellayglassey.R;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.DatabaseCreator;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.async.medecine.AsyncGetMedecines;
import com.example.android.hospitalapp_arbellayglassey.dataAccess.entity.MedecineEntity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfMedecineActivity;
import com.example.android.hospitalapp_arbellayglassey.listActivity.ListOfPatientActivity;
import com.example.android.hospitalapp_arbellayglassey.settings.Settings;
import com.example.android.hospitalapp_arbellayglassey.adapter.ListViewWithAddBtnAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MedecineAddSearchList extends AppCompatActivity {

    // variables
    private DrawerLayout mDrawerLayout;
    private List<MedecineEntity> MedecineEntities;
    private ArrayList<String> medecines;
    private int idT;
    private int idP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_add_search_list);

        // adding list
        ListView list;

        // read the db
        try {
            readDB();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //get the intent to know the id to create the link
        Intent intentGetID = getIntent();
        idT = intentGetID.getIntExtra("idT",0);
        idP = intentGetID.getIntExtra("idP", 0);
        list = (ListView) findViewById(R.id.listofmedecinesearchlist);

        //one funny adapter
        list.setAdapter(new ListViewWithAddBtnAdapter(MedecineEntities, idT,idP,MedecineAddSearchList.this, R.layout.listmedecineaddsearchlist_layout, R.id.listview_listofmedecineaddsearchlist, R.id.addMedecineForTreatmentButton));
    }

    //Read the db from our application
    public void readDB() throws ExecutionException, InterruptedException {

        medecines = new ArrayList<String>();

        //access to the database creator
        DatabaseCreator dbCreator = DatabaseCreator.getInstance(MedecineAddSearchList.this);
        //Get all medecines form our db
        MedecineEntities = new AsyncGetMedecines(MedecineAddSearchList.this).execute().get();

        //Add all the medecine in the list to display it
        for (MedecineEntity p : MedecineEntities){
            medecines.add(p.getName());
        }

    }
    // on create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        setTitle(R.string.title_add_medecine);
        setupActionBar();
        setupNavBar();
        return true;

    }
    // set up the action bar
    private void setupActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // setup the button of the action bar
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        else{
            finish();
        }

        return true;
    }

    //setup navigation drawer
    //this method setup the navigation drawer and implement the button to go to the list
    public void setupNavBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout_add_medecine_search);

        NavigationView navigationView = findViewById(R.id.nav_view_add_medecine_search);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()) {
                            case R.id.nav_list_of_patient:
                                Intent intentPatient = new Intent(MedecineAddSearchList.this, ListOfPatientActivity.class);
                                MedecineAddSearchList.this.startActivity(intentPatient);
                                finish();
                                break;
                            case R.id.nav_list_of_medicine:
                                Intent intentMed = new Intent(MedecineAddSearchList.this, ListOfMedecineActivity.class);
                                MedecineAddSearchList.this.startActivity(intentMed);
                                finish();
                                break;
                            default:
                                break;
                        }
                        mDrawerLayout.closeDrawers();


                        return true;
                    }
                });
    }

}
