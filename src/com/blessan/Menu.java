package com.blessan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /** Horizontal Button Handler*/
    public void horizontalButtonHandler(View target){
    	Intent horizontalSlideshowIntent = new Intent(Menu.this,HorizonalSlideshow.class);
        startActivity(horizontalSlideshowIntent);
    }
    
    /** Vertical Button Handler*/
    public void verticalButtonHandler(View target){
    	Intent verticalSlideshowIntent = new Intent(Menu.this,VerticalSlideshow.class);
        startActivity(verticalSlideshowIntent);
    }
    
    public void onBackPressed(){
		super.onBackPressed();
		finish();
	}
}