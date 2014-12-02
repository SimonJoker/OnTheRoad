package org.chinenv.onroad.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.StaticData;

import de.hdodenhof.circleimageview.CircleImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WishListTimelineAdapter extends BaseAdapter {
	
	List<Integer> 	index = new ArrayList<Integer>();
	Context			mContext;
	
	public WishListTimelineAdapter(Context mContext, List<Integer> data){
		this.index = data;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return index.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
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
		convertView = LayoutInflater.from(mContext)
				.inflate(R.layout.wish_list_time_line_list_cell, parent, false);
		TextView addTime = (TextView)convertView.findViewById(R.id.add_time);
		CircleImageView activityPhoto = (CircleImageView)convertView.findViewById(R.id.user_home_user_icon);
		TextView activityTitle = (TextView)convertView.findViewById(R.id.activity_title);
		TextView addTimeTitle = (TextView)convertView.findViewById(R.id.activity_add_time);
		activityPhoto.setImageResource(StaticData.getDrawble().get(index.get(position))[0]);
		
		return convertView;
	}

}
