package org.chinenv.onroad.ui.activity;

import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.listener.ReginLoginClickListener;
import org.chinenv.onroad.util.ToastHelper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class RegisterAndLoginActivity extends Activity {
	
	
	
	ReginLoginClickListener   				clickListener = new ReginLoginClickListener(this);
	
	Context									mContext = this;
	private PopupWindow 					mPopupWindow; 
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionbar();
		setScreenState();
		setContentView(R.layout.regin_login);
	}
	
	
	
	private void setActionbar(){
		ActionBar actionbar = this.getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setDisplayOptions (ActionBar. DISPLAY_SHOW_CUSTOM);
		actionbar.setCustomView(R.layout.regin_actionbar_custom_menu);
		LinearLayout btn_back = (LinearLayout)actionbar.getCustomView().findViewById(R.id.action_back);
		btn_back.setOnClickListener(clickListener);
		
		final LinearLayout btn_more = (LinearLayout)actionbar.getCustomView().findViewById(R.id.action_more);
		btn_more.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getPopupWindowInstance(); 
	            mPopupWindow.showAsDropDown(v, btn_more.getWidth(), -btn_more.getHeight());// X、Y方向各偏移按钮的宽度
			}
		});
    	
		
		
	}
	
	/**
	 * 设置状态栏
	 * */
	private void setScreenState(){
		if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}
	
	
	
	/*
     * 获取PopupWindow实例
     */ 
    private void getPopupWindowInstance() { 
        if (null != mPopupWindow) { 
            mPopupWindow.dismiss(); 
            return; 
        } else { 
            initPopuptWindow(); 
        } 
    } 
	
    /*
     * 创建PopupWindow
     */ 
    private void initPopuptWindow() { 
        LayoutInflater layoutInflater = LayoutInflater.from(this); 
        View popupWindow = layoutInflater.inflate(R.layout.regin_action_popuwindow, null); 
        
 
        // 创建一个PopupWindow 
        // 参数1：contentView 指定PopupWindow的内容 
        // 参数2：width 指定PopupWindow的width 
        // 参数3：height 指定PopupWindow的height 
        mPopupWindow = new PopupWindow(popupWindow
        							, ViewGroup.LayoutParams.WRAP_CONTENT
        							, ViewGroup.LayoutParams.WRAP_CONTENT); 
        mPopupWindow.setOutsideTouchable(true);
        LinearLayout useage = (LinearLayout)popupWindow.findViewById(R.id.how_to_use);
        useage.setOnClickListener(new PopuwindowItemClickListener());
        LinearLayout serviceTerms = (LinearLayout)popupWindow.findViewById(R.id.service_terms);
        serviceTerms.setOnClickListener(new PopuwindowItemClickListener());
        LinearLayout privacyPolicy = (LinearLayout)popupWindow.findViewById(R.id.privacy_policy);
        privacyPolicy.setOnClickListener(new PopuwindowItemClickListener());
        LinearLayout popuHelp = (LinearLayout)popupWindow.findViewById(R.id.popu_help);
        popuHelp.setOnClickListener(new PopuwindowItemClickListener());
        
        
        
    } 
	
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if (mPopupWindow != null && mPopupWindow.isShowing()) { 
    		mPopupWindow.dismiss(); 
    		mPopupWindow = null; 
        } 
        return super.onTouchEvent(event);
    }
	
    
    private class PopuwindowItemClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.how_to_use:
				ToastHelper.showShort(mContext, "how to use");
				break;
			case R.id.service_terms:
				ToastHelper.showShort(mContext, "service terms");
				break;
			case R.id.privacy_policy:
				ToastHelper.showShort(mContext, "privacy policy");
				break;
			case R.id.popu_help:
				ToastHelper.showShort(mContext, "popu help");
				break;
			default:
				break;
			}
			
		}};
    
}
