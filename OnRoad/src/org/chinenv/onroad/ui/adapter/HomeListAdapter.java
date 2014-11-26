package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.bean.StaticData;


import org.chinenv.onroad.loopview.LoopViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
		convertView = LayoutInflater.from(mContext)
				.inflate(R.layout.handpick_fragment_content_list_item, parent, false);
		LoopViewPager loopPger = (LoopViewPager)convertView.findViewById(R.id.pager);
		HomeListPagerAdapter pagerAdapter = new HomeListPagerAdapter(position);
		loopPger.setAdapter(pagerAdapter);

		return convertView;
	}

}
