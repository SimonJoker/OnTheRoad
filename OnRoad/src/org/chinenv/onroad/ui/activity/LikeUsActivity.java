package org.chinenv.onroad.ui.activity;

import org.chinenv.onroad.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.WindowManager;

public class LikeUsActivity extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.like_us_activity);
		setActionbar();
		setScreenState();
		
		
		
	}
	
	private void setActionbar(){
		ActionBar actionbar = this.getActionBar();
		actionbar.hide();
	}
	
	
	/**
	 * 设置状态栏
	 * */
	private void setScreenState(){
		if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}
	
	
	
}
