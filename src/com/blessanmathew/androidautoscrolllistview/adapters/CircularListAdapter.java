package com.blessanmathew.androidautoscrolllistview.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class CircularListAdapter extends BaseAdapter {
    // Debug
    static final boolean DEBUG = false;
    static final String TAG = CircularListAdapter.class.getSimpleName();
    
    // Attributes
    private BaseAdapter mListAdapter;
    private int mListAdapterCount;
    
    public static final int HALF_MAX_VALUE = Integer.MAX_VALUE/2;
    public final int MIDDLE;
    
    /**
     * Constructs a {@linkplain CircularListAdapter}.
     * 
     * @param listAdapter A {@link ListAdapter} that has to behave circular.
     */
    public CircularListAdapter(BaseAdapter listAdapter) {
        if(listAdapter == null) {
            throw new IllegalArgumentException("listAdapter cannot be null.");
        }
        
        MIDDLE = HALF_MAX_VALUE - HALF_MAX_VALUE % listAdapter.getCount();
        
        this.mListAdapter = listAdapter;
        this.mListAdapterCount = listAdapter.getCount();
    }
    
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mListAdapter.getView(position % mListAdapterCount, convertView, parent);
    }

    @Override
    public Object getItem(int position) {
        return mListAdapter.getItem(position % mListAdapterCount);
    }

    @Override
    public long getItemId(int position) {
        return mListAdapter.getItemId(position % mListAdapterCount);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return mListAdapter.areAllItemsEnabled();
    }

    @Override
    public int getItemViewType(int position) {
        return mListAdapter.getItemViewType(position % mListAdapterCount);
    }

    @Override
    public int getViewTypeCount() {
        return mListAdapter.getViewTypeCount();
    }

    @Override
    public boolean isEmpty() {
        return mListAdapter.isEmpty();
    }

    @Override
    public boolean isEnabled(int position) {
        return mListAdapter.isEnabled(position % mListAdapterCount);
    }

    @Override
    public void notifyDataSetChanged() {
        mListAdapter.notifyDataSetChanged();
        mListAdapterCount = mListAdapter.getCount();
        
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        mListAdapter.notifyDataSetInvalidated();
        super.notifyDataSetInvalidated();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return mListAdapter.getDropDownView(position % mListAdapterCount, 
                convertView, parent);
    }

    @Override
    public boolean hasStableIds() {
        return mListAdapter.hasStableIds();
    }
    
}
