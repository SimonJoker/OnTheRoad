package org.chinenv.onroad.ui.listener;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;
import org.chinenv.onroad.util.SinaAuthHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

public class ReginLoginClickListener implements OnClickListener {
	
	Activity 				activity;
	View					disView;
	Handler					handler;
	
	public ReginLoginClickListener(Activity activity, Handler handler) {
		this.activity = activity;
		this.handler = handler;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.action_back:
			Intent intent = new Intent(activity, HomeActivity.class);
			activity.startActivity(intent);
			break;
		case R.id.sina_login:
			SinaAuthHelper sinaHelp = new SinaAuthHelper(activity,handler);
			sinaHelp.getSinaAuthToken();
			break;
		default:
			break;
		}

	}
	
	
	

}
