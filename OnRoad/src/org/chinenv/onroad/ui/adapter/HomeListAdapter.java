package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.StaticData;
import org.chinenv.onroad.ui.activity.ThemeContentActivity;
import org.chinenv.onroad.weight.MyLoopViewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeListAdapter extends BaseAdapter {
	
	Context mContext;
	
	public HomeListAdapter(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return StaticData.getDrawble().size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (StaticData.getDrawble().get(position).length >= 3) {
			convertView = LayoutInflater.from(mContext)
					.inflate(R.layout.handpick_fragment_content_list_item, parent, false);
			MyLoopViewpager loopPger = (MyLoopViewpager)convertView.findViewById(R.id.pager);
			HomeListPagerAdapter pagerAdapter = new HomeListPagerAdapter(position);
			loopPger.setAdapter(pagerAdapter);
			
		}else {
			convertView = LayoutInflater.from(mContext)
					.inflate(R.layout.handpick_fragment_content_pager_cell, parent, false);
			LinearLayout theme_layout = (LinearLayout)convertView.findViewById(R.id.theme_layout);
			ImageView image = (ImageView)convertView.findViewById(R.id.image);
			TextView title = (TextView)convertView.findViewById(R.id.title);
			TextView description = (TextView)convertView.findViewById(R.id.description);
			image.setImageResource((StaticData.getDrawble().get(position))[0]);
			
			title.setText(StaticData.getTitle()[position]);
			description.setText(mContext.getResources().getString(R.string.home_activity_handpick_description_1)
								+ StaticData.getDrawble().get(position).length
								+ mContext.getResources().getString(R.string.home_activity_handpick_description_2));
			
			theme_layout.setOnClickListener(new ThemeListClickListener(position));
		}

		return convertView;
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
