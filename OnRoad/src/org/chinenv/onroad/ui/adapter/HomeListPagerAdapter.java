package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.loopview.PagerAdapter;
import org.chinenv.onroad.statictestdata.StaticData;
import org.chinenv.onroad.ui.activity.ThemeContentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeListPagerAdapter extends PagerAdapter {

	int num;
	Context mContext;
	
	
	public HomeListPagerAdapter(int num){
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
				.inflate(R.layout.handpick_fragment_content_pager_cell, null);
		ImageView image = (ImageView)view.findViewById(R.id.image);
		TextView title = (TextView)view.findViewById(R.id.title);
		TextView description = (TextView)view.findViewById(R.id.description);
		LinearLayout theme_layout = (LinearLayout)view.findViewById(R.id.theme_layout);
		
		Log.i("pagerAdapter", "------->getDrawble:"+(StaticData.getDrawble().get(num))[position]);
		image.setImageResource((StaticData.getDrawble().get(num))[position]);
		
		title.setText(StaticData.getTitle()[num]);
		description.setText(mContext.getResources().getString(R.string.home_activity_handpick_description_1)
							+ StaticData.getDrawble().get(num).length
							+ mContext.getResources().getString(R.string.home_activity_handpick_description_2));
		
		theme_layout.setOnClickListener(new ThemeListClickListener(num));
		
		container.addView(view);
		return view;
	}
	
	private class ThemeListClickListener implements OnClickListener{
		private int position = 0;
		
		public ThemeListClickListener(int position) {
			this.position = position;
		}
		@Override
		public void onClick(View v) {
			Log.i("ThemeListener", "------>ThemeListClickListener");
			Intent intent = new Intent(mContext, ThemeContentActivity.class);
			Bundle data = new Bundle();
			data.putInt("POSITION", position);
			intent.putExtras(data);
			mContext.startActivity(intent);
		}
		
	}

}
