package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.StaticData;
import org.chinenv.onroad.ui.activity.ActivityContentAcvitity;
import org.chinenv.onroad.weight.MyThemeListViewPager;

import de.hdodenhof.circleimageview.CircleImageView;
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

public class ThemeContentListAdapter extends BaseAdapter {
	
	Context mContext;
	
	public ThemeContentListAdapter(Context mContext) {
		this.mContext = mContext;
	}
	

	@Override
	public int getCount() {
		return StaticData.getThemeData().size();
	}

	@Override
	public Object getItem(int position) {
		return StaticData.getThemeData().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext)
				.inflate(R.layout.theme_activity_list_item, parent, false);
		MyThemeListViewPager themePager = (MyThemeListViewPager)convertView.findViewById(R.id.theme_list_pager);
		ThemeContentPagerAdapter themePagerAdapter = new ThemeContentPagerAdapter(position);
		themePager.setAdapter(themePagerAdapter);
		
		ImageView wishList = (ImageView)convertView.findViewById(R.id.wish_list);
		if (StaticData.getFlag()[position]) {
			wishList.setImageResource(R.drawable.wishlist_heart_selected);
		}else {
			wishList.setImageResource(R.drawable.wishlist_heart_unselected);
		}
		wishList.setOnClickListener(new ThemeListListener(mContext, position));
		TextView price = (TextView)convertView.findViewById(R.id.price);
		CircleImageView userIcon = (CircleImageView)convertView.findViewById(R.id.theme_list_user_icon);
		TextView description = (TextView)convertView.findViewById(R.id.description);
		TextView evaluate = (TextView)convertView.findViewById(R.id.evaluate);
		
		price.setText(StaticData.getPrice()[position]+"");
		userIcon.setImageResource(StaticData.getTouxiangIcon()[position]);
		description.setText(R.string.theme_list_description_text);
		evaluate.setText(R.string.theme_list_evaluate_text);

		
		return convertView;
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
			case R.id.wish_list:
				if (StaticData.getFlag()[pos]) {
					((ImageView)v).setImageResource(R.drawable.wishlist_heart_unselected);
					StaticData.setFlag(pos,false);
				}else {
					((ImageView)v).setImageResource(R.drawable.wishlist_heart_selected);
					StaticData.setFlag(pos,true);
				}
				break;		
			default:
				break;
			}
		}}
}
