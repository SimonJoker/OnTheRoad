package org.chinenv.onroad.ui.activity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import org.chinenv.onroad.R;
import org.chinenv.onroad.bean.GudidPageDatas;
import org.chinenv.onroad.ui.adapter.GuidViewPagerAdapter;
import org.chinenv.onroad.util.JudgeIsFirstUseUtil;

import android.R.layout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * 
 * @author way
 * 
 */
public class GuidSplashActivity extends Activity {
	
	private static final String TAG = "LoginActivity";
	
	private static final int 	ISNEW = 0x001;
	public static final int 	COMPLETE = 0x002;
	
	private int 				lastPosition = 0;
	
	
	private static final Boolean IS_FIRST_USE = true;
	private Context 		mContext = this;

	private Handler 		handler;
	LayoutInflater 			layoutInflater;
	View 					guidView;
	
	ViewPager				guidViewPager;
	CirclePageIndicator		guidPageIndicator;	
	PagerAdapter 			guidAdapter;
	ImageView				imageView;
	ImageView				imageBg;
	LinearLayout			btnLayout;
	
	LinearLayout			regin_btn;
	LinearLayout			login_btn;
	
	File 					cacheDir;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setHandler();
//		final Boolean isFirstUse = JudgeIsFirstUseUtil.isFirstUse(mContext);
		final Boolean isFirstUse = true;
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
	
	private void binViews(){
		layoutInflater = LayoutInflater.from(mContext);
		guidView = layoutInflater.inflate(R.layout.guid_page, null);
		
		imageBg = (ImageView)guidView.findViewById(R.id.guid_bg);
		imageView = (ImageView)guidView.findViewById(R.id.guid_image_view);
		btnLayout =(LinearLayout)guidView.findViewById(R.id.login_bg);
		
		guidViewPager = (ViewPager)guidView.findViewById(R.id.view_pager);	
		guidPageIndicator = (CirclePageIndicator)guidView.findViewById(R.id.dot_marks);	
		
		regin_btn = (LinearLayout)guidView.findViewById(R.id.regin_btn);
		login_btn = (LinearLayout)guidView.findViewById(R.id.login_btn);
	}
	
	
	private void setContent(){
		binViews();
		setViewPagerIndicator();
		setBtnView();
		
		this.setContentView(guidView);
	}
	
	
	
	private void setBtnView(){
		regin_btn.setOnClickListener(new BtnClickListener());
		login_btn.setOnClickListener(new BtnClickListener());
	}
	
	
	private void setViewPagerIndicator(){
		guidPageIndicator.setRadius(10.0f);
		guidAdapter = new GuidViewPagerAdapter(this);
		guidViewPager.setAdapter(guidAdapter);
		guidPageIndicator.setViewPager(guidViewPager);
		guidPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				setImageViewAnimationOut(imageBg, btnLayout, lastPosition);
				setImageviewAnimationIn(imageView, btnLayout, position);
				lastPosition = position;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {				
			}
		});
	}
	
	
	/**
	 * 设置淡入淡出
	 * */
	private void setImageViewAnimationOut(ImageView imagView, LinearLayout layout, int position){
		int rscId = GudidPageDatas.getImageid()[position];
		int bgColorID = GudidPageDatas.getBtnbgid()[position];
		AlphaAnimation animation = new AlphaAnimation(1.0f, 0.1f);
		animation.setDuration(1000);
		AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.9f);
		animation2.setDuration(1000);
		imagView.startAnimation(animation);
		imagView.setImageResource(rscId);
		layout.startAnimation(animation2);
		layout.setBackgroundResource(bgColorID);
	}
	
	private void setImageviewAnimationIn(ImageView imagView, LinearLayout layout,int position){
		int rscId = GudidPageDatas.getImageid()[position];
		int bgColorID = GudidPageDatas.getBtnbgid()[position];
		AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
		animation.setDuration(1000);
		AlphaAnimation animation2 = new AlphaAnimation(0.9f, 1.0f);
		animation2.setDuration(1000);
		imagView.startAnimation(animation);
		imagView.setImageResource(rscId);
		layout.startAnimation(animation2);
		layout.setBackgroundResource(bgColorID);

	}
	
	
	private class BtnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(mContext, RegisterAndLoginActivity.class);
			startActivity(intent);
		}};
}
