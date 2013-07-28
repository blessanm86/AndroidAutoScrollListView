package com.blessanmathew.androidautoscrolllistview.demo;


import com.blessanmathew.androidautoscrolllistview.R;
import com.blessanmathew.androidautoscrolllistview.adapters.CircularListAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class CircularList extends ListActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);
        
        String[] companies = { "Apple", "Google", "Microsoft", "Nokia", "Motorola", "Samsung", "LG" };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, companies);
        
        final CircularListAdapter circularListAdapter = new CircularListAdapter(adapter);
        setListAdapter(circularListAdapter);
        
        getListView().setSelectionFromTop(circularListAdapter.MIDDLE, 0);
    }
}
