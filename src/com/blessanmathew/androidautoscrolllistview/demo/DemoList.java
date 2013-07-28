package com.blessanmathew.androidautoscrolllistview.demo;

import com.blessanmathew.androidautoscrolllistview.R;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DemoList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);
        
        String[] demos = { "Circular ListView", "Horizontal AutoScroller", "Vertical AutoScroller" };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, demos);
        setListAdapter(adapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Class<?> activityClass = null;
        
        switch(position) {
        	case 0: activityClass = CircularList.class;
            	    break;        	
        	case 1: activityClass = HorizontalAutoScroller.class;
            	    break;
        	case 2: activityClass = VerticalAutoScroller.class;
            	    break;        	
        }
        
        if(activityClass != null) {
            Intent intent = new Intent(this, activityClass);
            startActivity(intent);
        }
    }
}
