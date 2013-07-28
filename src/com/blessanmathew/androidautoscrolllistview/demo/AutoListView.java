package com.blessanmathew.androidautoscrolllistview.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class AutoListView extends ListView {
	int itemheight = 96;
	
	public AutoListView(Context context) {
		super(context);
	}
	
	public AutoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void scrollToY(int position) {
	    int item=(int)Math.floor(position/itemheight);
	    int scroll=(int) ((item*itemheight)-position);
	    this.setSelectionFromTop(item, scroll);// Important
	}
	
	public void scrollByY(int position)	{
	    position+=getListScrollY();
	    int item=(int)Math.floor(position/itemheight);
	    int scroll=(int) ((item*itemheight)-position);
	    this.setSelectionFromTop(item, scroll);// Important
	}
	public int getListScrollY()	{
	    try{
	    //int tempscroll=this.getFirstVisiblePosition()*itemheight;// Important
	    View v=this.getChildAt(0);
	    int tempscroll=(this.getFirstVisiblePosition()*itemheight)-v.getTop();// Important
	    return tempscroll;
	    }catch(Exception e){}
	    return 0;
	}
}
