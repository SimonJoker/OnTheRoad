package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.DiscoverSearchTestData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DiscoverSearchListAdapter extends BaseAdapter {
	
	Context mContext;
	
	public DiscoverSearchListAdapter(Context mContext) {
		this.mContext = mContext;
		Log.i("adapter", "DiscoverSearchListAdapter----->mContext:"+mContext);
	}

	@Override
	public int getCount() {
		return DiscoverSearchTestData.getSearchPlaces().size();
	}

	@Override
	public Object getItem(int position) {
		return DiscoverSearchTestData.getSearchPlaces().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			return convertView;
		}
		Log.i("adapter", "------->context:"+mContext);
		convertView = (View)LayoutInflater.from(mContext)
				.inflate(R.layout.discover_fragment_search_list_cell, null, false);
		TextView place = (TextView)convertView.findViewById(R.id.place_name);
		place.setText(DiscoverSearchTestData.getSearchPlaces().get(position));
		
		return convertView;
	}

}
