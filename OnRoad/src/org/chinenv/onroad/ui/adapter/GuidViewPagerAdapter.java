package org.chinenv.onroad.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;






import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.activity.GuidSplashActivity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GuidViewPagerAdapter extends PagerAdapter {

	Activity 					_activity;
	ViewPager 					bannerPager;
	Handler						handler;
	
	ImageView					image;
	
	int xPsition =0;
	
	private LayoutInflater inflater;
	private ArrayList<HashMap<String, Object>> _Data = new ArrayList<HashMap<String,Object>>();

	//初始化适配器数据	
	public GuidViewPagerAdapter(Activity activity, ViewPager guidViewPager, ImageView imageView, Handler handler){
		_activity = activity;
		inflater = activity.getLayoutInflater();
		this.bannerPager = guidViewPager;
		this.handler = handler;
		this.image = imageView;
		intData();
	}
	
	//载入引导图片
	public void intData(){
		for(int i=0;i<=3;i++){
			switch (i) {
			case 0:
				HashMap<String, Object> bannerObject = new HashMap<String, Object>();
				bannerObject.put("image", R.drawable.appintro1);
				bannerObject.put("title", R.string.guide_title_first);
				bannerObject.put("content", R.string.guide_content_first);
				_Data.add(bannerObject);
				break;
			case 1:
				HashMap<String, Object> bannerObject1 = new HashMap<String, Object>();
				bannerObject1.put("image", R.drawable.appintro2);
				bannerObject1.put("title", R.string.guide_title_second);
				bannerObject1.put("content", R.string.guide_content_second);
				_Data.add(bannerObject1);
				break;
			case 2:
				HashMap<String, Object> bannerObject2 = new HashMap<String, Object>();
				bannerObject2.put("image", R.drawable.appintro3);
				bannerObject2.put("title", R.string.guide_title_third);
				bannerObject2.put("content", R.string.guide_content_third);
				_Data.add(bannerObject2);
				break;
			case 3:
				HashMap<String, Object> bannerObject3 = new HashMap<String, Object>();
				bannerObject3.put("image", R.drawable.appintro4);
				bannerObject3.put("title", R.string.guide_title_fourth);
				bannerObject3.put("content", R.string.guide_content_fourth);
				_Data.add(bannerObject3);
				break;
			default:
				break;
			}
			
		}
		
	}

	@Override
	public int getCount() { 
		return _Data.size();
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
		
		final int imageDrawable = (Integer) _Data.get(position).get("image");
		image.setImageResource(imageDrawable);
		TextView title = (TextView)imageLayout.findViewById(R.id.guid_title_text);
		int titleId = (int) _Data.get(position).get("title");
		title.setText(titleId);
		TextView content = (TextView)imageLayout.findViewById(R.id.guid_content_text);
		int contentId = (int) _Data.get(position).get("content");
		content.setText(contentId);
		
		container.addView(imageLayout);
		
		
		return imageLayout;
	}
	
	@Override
	public Parcelable saveState() {
		return null;
	}
}
