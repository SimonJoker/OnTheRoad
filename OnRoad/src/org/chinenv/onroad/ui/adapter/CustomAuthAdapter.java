package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.AuthorizeAdapter;

public class CustomAuthAdapter extends AuthorizeAdapter {
	@Override
	public void onCreate() {
		super.onCreate();
		TitleLayout llTitle = getTitleLayout();
		llTitle.setBackgroundColor(Color.WHITE);
		
		//返回箭头设置
		ImageView backIcon = (ImageView)llTitle.getChildAt(0);		
		backIcon.setImageResource(R.drawable.abc_ab_back_black);
		backIcon.setPadding(12, 20, 12, 20);
		
		//去掉返回返回箭头后面的divider
		ImageView backIconDivider = (ImageView)llTitle.getChildAt(1);		
		backIconDivider.setVisibility(View.GONE);
        
		
		//设置默认右边logo消失，设置actionbar的title
        hideShareSDKLogo();
        disablePopUpAnimation();
        TextView abTitle = (TextView)getTitleLayout().getTvTitle();
        abTitle.setText(R.string.regin_login_atuth_title);
        abTitle.setTextColor(getActivity()
        		.getResources().getColor(R.color.sina_auth_title_text_color));

	}
	
	 public void onDestroy() {
         System.out.println("--> onRoad ----->  ShareSDKUIShell will be destroyed.");
 }
	 
}
