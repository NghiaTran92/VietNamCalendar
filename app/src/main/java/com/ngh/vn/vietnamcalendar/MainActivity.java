package com.ngh.vn.vietnamcalendar;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lunar.calendar.Relation;


public class MainActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] option=getResources().getStringArray(R.array.list_option);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i;
        switch (position)
        {
            case 1:
                i=new Intent(this,DetailHumanActivity.class);
                break;
            case 2:
                i=new Intent(this,GoodDayActivity.class);
                break;
            case 3:
                i=new Intent(this,RelationActivity.class);
                break;
            default:
                i=new Intent(this,ConvertSolar2LunarActivity.class);
                break;
        }

        startActivity(i);
    }
}
