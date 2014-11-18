package org.chinenv.onroad.ui.adapter;

import org.chinenv.onroad.R;
import org.chinenv.onroad.bean.GudidPageDatas;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GuidViewPagerAdapter extends PagerAdapter {

	Activity 					_activity;

	int 						xPsition =0;
	
	private LayoutInflater inflater;


	//初始化适配器数据	
	public GuidViewPagerAdapter(Activity activity){
		_activity = activity;
		inflater = activity.getLayoutInflater();

	}

	@Override
	public int getCount() { 
		return GudidPageDatas.getTitleId().length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
	}
	
	
	/**
	 *适配页面，并设置监听 
	 **/
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		View imageLayout = inflater.inflate(R.layout.guid_page_detail, container, false);	

		TextView title = (TextView)imageLayout.findViewById(R.id.guid_title_text);
		TextView content = (TextView)imageLayout.findViewById(R.id.guid_content_text);
		
		int titleId = (int) GudidPageDatas.getTitleId()[position];
		int contentId = (int) GudidPageDatas.getContentId()[position];		
		title.setText(titleId);
		content.setText(contentId);
		
		container.addView(imageLayout);
		
		
		return imageLayout;
	}
	
	@Override
	public Parcelable saveState() {
		return null;
	}
}
