package com.blessan;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HorizonalSlideshow extends Activity {
	private LinearLayout  horizontalOuterLayout;
	private HorizontalScrollView horizontalScrollview;
	private TextView horizontalTextView;
	private int scrollMax;
	private int scrollPos =	0;
	private TimerTask clickSchedule;
	private TimerTask scrollerSchedule;
	private TimerTask faceAnimationSchedule;
	private Button clickedButton	=	null;
	private Timer scrollTimer		=	null;
	private Timer clickTimer		=	null;
	private Timer faceTimer         =   null;
	private Boolean isFaceDown      =   true;
	private String[] nameArray = {"Apple", "Banana", "Grapes", "Orange", "Strawberry","Apple", "Banana"};
	private String[] imageNameArray = {"apple", "banana", "grapes", "orange", "strawberry","apple", "banana"};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_layout);
        horizontalScrollview  = (HorizontalScrollView) findViewById(R.id.horiztonal_scrollview_id);
        horizontalOuterLayout =	(LinearLayout)findViewById(R.id.horiztonal_outer_layout_id);
        horizontalTextView    = (TextView)findViewById(R.id.horizontal_textview_id);
        
        horizontalScrollview.setHorizontalScrollBarEnabled(false);
        addImagesToView();
        
		ViewTreeObserver vto 		=	horizontalOuterLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
            	horizontalOuterLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            	getScrollMaxAmount();
            	startAutoScrolling();            	          	
            }
        });
    }
    
    public void getScrollMaxAmount(){
    	int actualWidth = (horizontalOuterLayout.getMeasuredWidth()-512);
    	scrollMax   = actualWidth;
    }
    
    public void startAutoScrolling(){
		if (scrollTimer == null) {
			scrollTimer					=	new Timer();
			final Runnable Timer_Tick 	= 	new Runnable() {
			    public void run() {
			    	moveScrollView();
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
			
			scrollTimer.schedule(scrollerSchedule, 30, 30);
		}
	}
    
    public void moveScrollView(){
    	scrollPos							= 	(int) (horizontalScrollview.getScrollX() + 1.0);
		if(scrollPos >= scrollMax){
			scrollPos						=	0;
		}
		horizontalScrollview.scrollTo(scrollPos, 0);
				
	}
    
    /** Adds the images to view. */
    public void addImagesToView(){
		for (int i=0;i<imageNameArray.length;i++){
			final Button imageButton =	new Button(this);
			int imageResourceId		 =	getResources().getIdentifier(imageNameArray[i], "drawable",getPackageName());
		    Drawable image 			 =	this.getResources().getDrawable(imageResourceId);
		    imageButton.setBackgroundDrawable(image);
		    imageButton.setTag(i);
		    imageButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					if(isFaceDown){
						if(clickTimer!= null){
							clickTimer.cancel();
							clickTimer			=	null;
						}
						clickedButton			=	(Button)arg0;
						stopAutoScrolling();
						clickedButton.startAnimation(scaleFaceUpAnimation());
						clickedButton.setSelected(true);
						clickTimer				=	new Timer();
						
						if(clickSchedule != null) {
							clickSchedule.cancel();
							clickSchedule = null;
						}
						
						clickSchedule = new TimerTask(){
				        	public void run() { 
				        		startAutoScrolling();   
				            }
				        };
						
						clickTimer.schedule( clickSchedule, 1500);
					}
				}
			});
			
			LinearLayout.LayoutParams params 	=	new LinearLayout.LayoutParams(256,256);
			params.setMargins(0, 25, 0, 25);
			imageButton.setLayoutParams(params);
			horizontalOuterLayout.addView(imageButton);
		}
	}
    
    public Animation scaleFaceUpAnimation(){
		Animation scaleFace = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		scaleFace.setDuration(500);
		scaleFace.setFillAfter(true);
		scaleFace.setInterpolator(new AccelerateInterpolator());
		Animation.AnimationListener	scaleFaceAnimationListener = new Animation.AnimationListener() {			
			@Override
			public void onAnimationStart(Animation arg0) {
				horizontalTextView.setText(nameArray[(Integer) clickedButton.getTag()]);
				isFaceDown = false;
			}			
			@Override
			public void onAnimationRepeat(Animation arg0) {}			
			@Override
			public void onAnimationEnd(Animation arg0) {
				if(faceTimer != null){
					faceTimer.cancel();
					faceTimer = null;
				}
				
				faceTimer = new Timer();
				if(faceAnimationSchedule != null){
					faceAnimationSchedule.cancel();
					faceAnimationSchedule = null;
				}
				faceAnimationSchedule = new TimerTask() {					
					@Override
					public void run() {
						faceScaleHandler.sendEmptyMessage(0); 										
					}
				};
				
				faceTimer.schedule(faceAnimationSchedule, 750);				
			}
		}; 
		scaleFace.setAnimationListener(scaleFaceAnimationListener);
		return scaleFace;
	}
    
    private Handler faceScaleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	if(clickedButton.isSelected() == true)
        		clickedButton.startAnimation(scaleFaceDownAnimation(500));		
        }
	};
	
	public Animation scaleFaceDownAnimation(int duration){
		Animation scaleFace = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		scaleFace.setDuration(duration);
		scaleFace.setFillAfter(true);
		scaleFace.setInterpolator(new AccelerateInterpolator());
		Animation.AnimationListener	scaleFaceAnimationListener = new Animation.AnimationListener() {			
			@Override
			public void onAnimationStart(Animation arg0) {}			
			@Override
			public void onAnimationRepeat(Animation arg0) {}			
			@Override
			public void onAnimationEnd(Animation arg0) {
				horizontalTextView.setText("");
				isFaceDown = true;				
			}
		}; 
		scaleFace.setAnimationListener(scaleFaceAnimationListener);
		return scaleFace;
	}
    
    public void stopAutoScrolling(){
		if (scrollTimer != null) {
			scrollTimer.cancel();
			scrollTimer	=	null;
		}
	}
}
