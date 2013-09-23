package com.redclay.fanaddict.activities;

import com.redclay.fanaddict.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	private static final int SPLASH_DELAY = 3000; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				Intent i = new Intent(SplashActivity.this, MainFeedActivity.class);
				startActivity(i); 
			}
			
			
		}, SPLASH_DELAY); 
	}

}
