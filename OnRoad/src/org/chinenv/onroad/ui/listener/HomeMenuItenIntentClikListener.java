package org.chinenv.onroad.ui.listener;

import net.simonvt.menudrawer.MenuDrawer;

import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.activity.GuidSplashActivity;
import org.chinenv.onroad.ui.activity.RegisterAndLoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeMenuItenIntentClikListener implements OnClickListener {
	private Activity activity;
	private MenuDrawer mDrawer;
	
	public HomeMenuItenIntentClikListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		
		case R.id.menu_usage:
			intent = new Intent(activity, GuidSplashActivity.class);
			Bundle bundle = new Bundle();
			bundle.putBoolean("Useage", true);
			intent.putExtras(bundle);
			activity.startActivity(intent);
			break;
		case R.id.menu_pub:
			
			break;
		case R.id.menu_why_pub:
			
			break;
		case R.id.menu_setting:
			
			break;
		case R.id.menu_help:
			
			break;
		case R.id.login_layout:
			intent = new Intent(activity, RegisterAndLoginActivity.class);
			activity.startActivity(intent);
			break;
		default:
			break;
		}
	}

}
