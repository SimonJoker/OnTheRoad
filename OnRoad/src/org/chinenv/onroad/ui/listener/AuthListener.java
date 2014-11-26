//package org.chinenv.onroad.ui.listener;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//import android.widget.Toast;
//
//import com.sina.weibo.sdk.auth.WeiboAuthListener;
//import com.sina.weibo.sdk.exception.WeiboException;
//import com.sina.weibo.sdk.utils.UIUtils;
//
//public class AuthListener implements WeiboAuthListener {
//	
//	Activity activity;
//	
//	private String mCode; 
//	private Handler handler;
//	
//	public AuthListener(Activity activity, Handler handler) {
//		this.activity = activity;
//		this.handler = handler;
//	}
//	
//
//	@Override
//	public void onCancel() {
//		Toast.makeText(activity, 
//        		"取消授权", Toast.LENGTH_SHORT).show();
//	}
//
//	@Override
//	public void onComplete(Bundle values) {
//		if (null == values) {
//			Toast.makeText(activity, 
//                    "获取 Code 失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        
//        String code = values.getString("code");
//        if (TextUtils.isEmpty(code)) {
//            Toast.makeText(activity, 
//                    "获取 Code 失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        
//        mCode = code;
//        Message msg = handler.obtainMessage();
//        msg.what = 1;
//        Bundle data = new Bundle();
//        data.putString("CODE", mCode);
//        msg.setData(data);
//        handler.sendMessage(msg);
//        
//        Toast.makeText(activity, 
//        		"获取 Code 成功", Toast.LENGTH_SHORT).show();
//
//	}
//
//	@Override
//	public void onWeiboException(WeiboException e) {
//		UIUtils.showToast(activity, 
//                "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG);
//	}
//
//}
