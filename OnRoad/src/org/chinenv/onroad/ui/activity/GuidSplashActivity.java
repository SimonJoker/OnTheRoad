package org.chinenv.onroad.ui.activity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;






import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.adapter.GuidViewPagerAdapter;
import org.chinenv.onroad.util.JudgeIsFirstUseUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * 
 * @author way
 * 
 */
public class GuidSplashActivity extends Activity {
	
	private static final String TAG = "LoginActivity";
	
	private static final int ISNEW = 0x001;
	public static final int COMPLETE = 0x002;
	private static final Boolean IS_FIRST_USE = true;
	private Context mContext = this;

	private Handler handler;
	
	ViewPager				guidViewPager;
	CirclePageIndicator		guidPageIndicator;	
	PagerAdapter 			guidAdapter;
	ImageView				imageView;
	
	File cacheDir;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setHandler();
		final Boolean isFirstUse = JudgeIsFirstUseUtil.isFirstUse(mContext);
		if (isFirstUse) {
			Message msg = Message.obtain();
			msg.what = ISNEW;
			handler.sendMessage(msg);
		}else {
			setContentView(R.layout.guid_login);
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					Message msg = Message.obtain();
					msg.what = COMPLETE;
					handler.sendMessage(msg);
				}
			}, 1000);
		}

	}

	
	private void setHandler(){
		
		 handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					case ISNEW:
						Log.i(TAG, "handleMessage----ISNEW");
						setContent();
						JudgeIsFirstUseUtil.saveFirstUseFlag(GuidSplashActivity.this);
						break;
					case COMPLETE:
						Log.i(TAG, "handleMessage----COMPLETE");
						Intent intent = new Intent(mContext, org.chinenv.onroad.HomeActivity.class);
						startActivity(intent);
						((Activity) mContext).finish();
						break;
					default:
						break;
					}
				}
			};
	}
	
	
	private void setContent(){
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		View guidView = layoutInflater.inflate(R.layout.guid_page, null);
		
		imageView = (ImageView)guidView.findViewById(R.id.guid_image_view);
		
		guidViewPager = (ViewPager)guidView.findViewById(R.id.view_pager);	
		
		guidPageIndicator = (CirclePageIndicator)guidView.findViewById(R.id.dot_marks);		
		guidAdapter = new GuidViewPagerAdapter(this, guidViewPager, imageView, handler);
		guidViewPager.setAdapter(guidAdapter);
		guidPageIndicator.setViewPager(guidViewPager);
		
		this.setContentView(guidView);
	}
	
	
}
