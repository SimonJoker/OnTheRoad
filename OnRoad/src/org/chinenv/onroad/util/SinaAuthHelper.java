package org.chinenv.onroad.util;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SinaAuthHelper {
	
	private static final String TAG = "SinaAuthHelper";
	
    
    private  String 				mCode;   
    public  Activity 				activity;
    Platform 						sinaAuth;
    Handler							handler;
    
    public SinaAuthHelper(Activity activity, Handler handler) {
		this.activity  = activity;
		this.handler = handler;
		initSina();
	}
    
    public void initSina(){
    	sinaAuth = ShareSDK.getPlatform(activity, SinaWeibo.NAME);
    	sinaAuth.setPlatformActionListener(new myPlatformAcitionListener());
    }
    
    public  void getSinaAuthToken(){
    	sinaAuth.authorize();
    }
    

    private class myPlatformAcitionListener implements PlatformActionListener{

		@Override
		public void onCancel(Platform platform, int action) {
			Log.i("main", "-------->onCancel()");
			Message msg = handler.obtainMessage();
			msg.what = 2;
			msg.arg1 = action;
			msg.obj = platform;
			handler.sendMessage(msg);
			
		}

		@Override
		public void onComplete(Platform platform
				, int action, HashMap<String, Object> res) {
			String accessToken = platform.getDb().getToken(); // 获取授权token

			Log.i("main", "--->onComplete() accessToken:"+accessToken);
			Log.i("main", "--->onComplete() UserIco:"+platform.getDb().getUserIcon());
			Log.i("main", "--->onComplete() getName:"+platform.getName());
			LoginKeeperHelper.writeLoginPartName(activity, platform.getName());
			
			Message msg = handler.obtainMessage();
			msg.what = 1;
			msg.arg1 = action;
			msg.obj = platform;
			handler.sendMessage(msg);
			
		}

		@Override
		public void onError(Platform platform, int action, Throwable t) {
			Log.i("main", "-------->onError()");
			Message msg = handler.obtainMessage();
			msg.what = 3;
			msg.arg1 = action;
			msg.obj = platform;
			handler.sendMessage(msg);			
		}};

}
