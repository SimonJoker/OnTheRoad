package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.StaticData;
import org.chinenv.onroad.ui.activity.ActivityContentAcvitity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivityContentPagerAdapter extends PagerAdapter {
	int 				num;
	Context 			mContext;
	
	
	public ActivityContentPagerAdapter(int num){
		this.num = num;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return StaticData.getDrawble().get(num).length;
	}

	
	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == object;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View v = (View) object;
		container.removeView(v);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		
		mContext = container.getContext();
		View view = LayoutInflater.from(mContext)
				.inflate(R.layout.activity_content_activity_pager_cell, null);
		ImageView image = (ImageView)view.findViewById(R.id.activity_images);
		image.setImageResource(StaticData.getThemeData().get(num)[position]);

		container.addView(view);
		return view;
	}
	
	
}
