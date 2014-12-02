package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.StaticData;
import org.chinenv.onroad.ui.activity.ActivityContentAcvitity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ThemeContentPagerAdapter extends PagerAdapter {
	int num;
	Context mContext;
	
	
	public ThemeContentPagerAdapter(int num){
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
				.inflate(R.layout.theme_activity_list_pager_cell, null);
		LinearLayout viewLayout = (LinearLayout)view.findViewById(R.id.pager_layout);
		viewLayout.setOnClickListener(new ThemeListListener(mContext, num));
		
		ImageView image = (ImageView)view.findViewById(R.id.user_image);
		image.setImageResource(StaticData.getThemeData().get(num)[position]);
		
		container.addView(view);
		return view;
	}
	
	private class ThemeListListener implements OnClickListener{
		Context mContext;
		int 	pos;
		
		public ThemeListListener(Context mContext, int position) {
			this.mContext = mContext;
			this.pos = position;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.pager_layout:
				Log.i("themeList", "----->pager_layout");
				Intent intent = new Intent(mContext, ActivityContentAcvitity.class);
				Bundle data = new Bundle();
				data.putInt("INDEX", pos);
				intent.putExtras(data);
				mContext.startActivity(intent);
				break;
			default:
				break;
			}
		}}
	
	
}
