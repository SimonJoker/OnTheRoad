package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.bean.StaticData;
import org.chinenv.onroad.loopview.PagerAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HomeListPagerAdapter extends PagerAdapter {

	int num;
	
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
		Context mContext = container.getContext();
		View view = LayoutInflater.from(mContext)
				.inflate(R.layout.handpick_fragment_content_pager_cell, null);
		ImageView image = (ImageView)view.findViewById(R.id.image);
		Log.i("MyAdapter", "------>(StaticData.getDrawble().get(num))[position]"+(StaticData.getDrawble().get(num))[position]);
		image.setImageResource((StaticData.getDrawble().get(num))[position]);
		container.addView(view);
		
		return view;
	}

}
