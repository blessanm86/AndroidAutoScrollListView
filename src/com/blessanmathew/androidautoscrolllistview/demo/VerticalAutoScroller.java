package com.blessanmathew.androidautoscrolllistview.demo;

import java.util.Timer;
import java.util.TimerTask;

import com.blessanmathew.androidautoscrolllistview.R;
import com.blessanmathew.androidautoscrolllistview.adapters.CircularListAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;

public class VerticalAutoScroller extends ListActivity {
	private int scrollStartPositon = 0;
	private Timer scrollTimer =	null;
	private TimerTask scrollerSchedule;
	private int scrollPos = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_autoscroller);
        
        String[] movieNames = {"Despicable Me 2", "Killing Season", "Only God Forgives", "Pacific Rim",
        		"Red 2", "R.I.P.D", "The Lone Ranger", "The Wolverine"};
        String[] posterNames = {"despicable_me_2", "killing_season", "only_god_forgives", "pacific_rim",
        		"red_2", "ripd", "the_lone_ranger", "wolverine"};
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, movieNames);
        
        final CircularListAdapter circularListAdapter = new CircularListAdapter(adapter);
        setListAdapter(circularListAdapter);
        
        getListView().setSelectionFromTop(circularListAdapter.MIDDLE, 0);
        
        ViewTreeObserver vto 		=	getListView().getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
            	getListView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
            	scrollStartPositon = getListView().getFirstVisiblePosition();
            	
            	//getListView().scrollTo(0, 100);
            	//startAutoScrolling();
            	//getListView().smoothScrollToPosition(5);
            	getListView().setSelectionFromTop(Integer.MAX_VALUE/2, -1000);
            	
            	View v = getListView().getChildAt(0);
            	int top = v.getMeasuredHeight();
            	Log.e("PosTop", Integer.toString(top));
            }
        });
        
        getListView().setOnTouchListener(new OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				stopAutoScrolling();
				View view = getListView().getChildAt((Integer.MAX_VALUE/2)%Integer.MAX_VALUE);
				Log.e("View Pos", Integer.toString(view.getTop()));
				Log.e("Scroll Pos", Integer.toString(scrollPos));
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					stopAutoScrolling();
				} else if(event.getAction() == MotionEvent.ACTION_UP){
					startAutoScrolling();
				}
				return false;
			}
		});
    }
	
	public void startAutoScrolling(){
		if (scrollTimer == null) {
			scrollTimer	= new Timer();
			final Runnable Timer_Tick 	= 	new Runnable() {
			    public void run() {
			    	//Log.e("Pos", Integer.toString(scrollPos));
			    	//getListView().smoothScrollToPosition(++scrollStartPositon);
			    	//getListView().setSelection(++scrollStartPositon);
			    	//getListView().scrollTo(0, scrollPos++);
			    	//((AutoListView)getListView()).scrollToY(++scrollStartPositon);
			    	getListView().setSelectionFromTop(Integer.MAX_VALUE/2, --scrollPos);
			    }
			};

			if(scrollerSchedule != null){
				scrollerSchedule.cancel();
				scrollerSchedule = null;
			}
			scrollerSchedule = new TimerTask(){
				@Override
				public void run(){
					runOnUiThread(Timer_Tick);
				}
			};

			scrollTimer.schedule(scrollerSchedule, 30, 20);
		}
	}
	
	 public void stopAutoScrolling(){
			if (scrollTimer != null) {
				clearTimerTaks(scrollerSchedule);
				clearTimers(scrollTimer);
				scrollerSchedule      = null;
				scrollTimer	=	null;
			}
	}
	
	public void onBackPressed(){
		super.onBackPressed();
		finish();
	}

	public void onPause() {
		super.onPause();
		finish();
	}

	public void onDestroy(){
		//clearTimerTaks(clickSchedule);
		clearTimerTaks(scrollerSchedule);
		//clearTimerTaks(faceAnimationSchedule);		
		clearTimers(scrollTimer);
		//clearTimers(clickTimer);
		//clearTimers(faceTimer);

		//clickSchedule         = null;
		scrollerSchedule      = null;
		//faceAnimationSchedule = null;
		scrollTimer           = null;
		//clickTimer            = null;
		//faceTimer             = null;

		super.onDestroy();
	}

	private void clearTimers(Timer timer){
	    if(timer != null) {
		    timer.cancel();
	        timer = null;
	    }
	}

	private void clearTimerTaks(TimerTask timerTask){
		if(timerTask != null) {
			timerTask.cancel();
			timerTask = null;
		}
	}
	
//	private ArrayList<Movie> generateData() {
//		ArrayList<Movie> data = new ArrayList<Movie>();
//		
//		data.add(new Movie("Despicable Me 2", "despicable_me_2"));
//		data.add(new Movie("Killing Season", "killing_season"));
//		data.add(new Movie("Only God Forgives", "only_god_forgives"));
//		data.add(new Movie("Pacific Rim", "pacific_rim"));
//		data.add(new Movie("Red 2", "red_2"));
//		data.add(new Movie("R.I.P.D", "ripd"));
//		data.add(new Movie("The Lone Ranger", "the_lone_ranger"));
//		data.add(new Movie("The Wolverine", "wolverine"));
//		
//		return data;
//	}
    
//	private class Movie {
//		String name;
//		String image;
//		
//		public Movie(String name, String image) {
//			this.name = name;
//			this.image = image;
//		}
//	}
}
