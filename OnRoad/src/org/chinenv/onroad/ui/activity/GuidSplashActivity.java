package org.chinenv.onroad.ui.activity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;
import org.chinenv.onroad.bean.GudidPageDatas;
import org.chinenv.onroad.ui.adapter.GuidViewPagerAdapter;
import org.chinenv.onroad.util.JudgeIsFirstUseUtil;

import android.R.layout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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
	public static final int 	REGIN_LOGGIN = 0x002;
	public static final int 	SKIP_GUID = 0x003;
	
	private int 				lastPosition = 0;

	private Context 			mContext = this;

	private Handler 			handler;
	LayoutInflater 				layoutInflater;
	View 						guidView;
	
	ViewPager					guidViewPager;
	CirclePageIndicator			guidPageIndicator;	
	PagerAdapter 				guidAdapter;
	ImageView					imageView;
	ImageView					imageBg;
	LinearLayout				btnLayout;
	
	LinearLayout				regin_btn;
	LinearLayout				login_btn;
	
	boolean						useage_flag = false;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置状态栏透明
		setScreenState();
		//设置handler来出来动作监听结果的跳转
		setHandler();
		if (this.getIntent().getExtras() != null) {
			useage_flag = this.getIntent().getExtras().getBoolean("Useage");
		}
		
		final Boolean isFirstUse = JudgeIsFirstUseUtil.isFirstUse(mContext);
		Message msg = Message.obtain();
		if (isFirstUse || useage_flag) {
			msg.what = ISNEW;
		}else {
			msg.what = SKIP_GUID;
		}
		handler.sendMessage(msg);

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
					case REGIN_LOGGIN:
						Log.i(TAG, "handleMessage----REGIN_LOGGIN");
						Intent intent = new Intent(mContext, RegisterAndLoginActivity.class);
						startActivity(intent);
						((Activity) mContext).finish();
						break;
					case SKIP_GUID:
						Log.i(TAG, "handleMessage----SKIP_GUID");
						Intent intent2 = new Intent(mContext, HomeActivity.class);
						startActivity(intent2);
						((Activity) mContext).finish();
						break;
					default:
						break;
					}
				}
			};
	}
	
	
	
	/**
	 * 设置actionbar
	 * */
	private void setActionBar(){
    	android.app.ActionBar actionBar = this.getActionBar(); 
    	actionBar.setCustomView(R.layout.splash_actionbar_custom_menu);
    	actionBar.setDisplayHomeAsUpEnabled(true);
    	actionBar.setDisplayOptions (ActionBar. DISPLAY_SHOW_CUSTOM);
    	
    	LinearLayout btn_back = (LinearLayout)actionBar.getCustomView().findViewById(R.id.btn_back);
    	Message msg = Message.obtain();
    	btn_back.setOnClickListener(new AbcBtnClickListener());

    	LinearLayout btn_skip = (LinearLayout)actionBar.getCustomView().findViewById(R.id.action_skip);
    	btn_skip.setOnClickListener(new AbcBtnClickListener());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
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
		
		//设置actionbar
		setActionBar();
		//绑定views
		binViews();
		//设置导航
		setViewPagerIndicator();
		//设置登录、注册按钮
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
	
	//监听注册、登录
	private class BtnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Message msg = handler.obtainMessage();
			msg.what = GuidSplashActivity.REGIN_LOGGIN;
			handler.sendMessage(msg);

		}};
		
		//监听actionbar 
	private class AbcBtnClickListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				Message msg = handler.obtainMessage();
				msg.what = GuidSplashActivity.SKIP_GUID;
				handler.sendMessage(msg);

	}};
		
	
	
		
}
