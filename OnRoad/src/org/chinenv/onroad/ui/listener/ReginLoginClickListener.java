package org.chinenv.onroad.ui.listener;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ReginLoginClickListener implements OnClickListener {
	
	Activity 				activity;
	View					disView;
	
	public ReginLoginClickListener(Activity activity) {
		this.activity = activity;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.action_back:
			Intent intent = new Intent(activity, HomeActivity.class);
			activity.startActivity(intent);
			break;
		default:
			break;
		}

	}
	
	

}
