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

public class GuidViewPagerAdapter extends PagerAdapter {

	Activity 					_activity;
	ViewPager 		bannerPager;
	Handler						handler;
	
	int xPsition =0;
	
	private LayoutInflater inflater;
	private ArrayList<HashMap<String, Object>> _Data = new ArrayList<HashMap<String,Object>>();

	//初始化适配器数据	
	public GuidViewPagerAdapter(Activity activity,ViewPager guidViewPager,Handler handler){
		_activity = activity;
		inflater = activity.getLayoutInflater();
		this.bannerPager = guidViewPager;
		this.handler = handler;
		intData();
	}
	
	//载入引导图片
	public void intData(){
		for(int i=0;i<=2;i++){
			switch (i) {
			case 0:
				HashMap<String, Object> bannerObject = new HashMap<String, Object>();
				bannerObject.put("image", R.drawable.img_guide_first);
				//bannerObject.put("title", Title[i]);
				_Data.add(bannerObject);
				break;
			case 1:
				HashMap<String, Object> bannerObject1 = new HashMap<String, Object>();
				bannerObject1.put("image", R.drawable.img_guide_second);
				//bannerObject1.put("title", Title[i]);
				_Data.add(bannerObject1);
				break;
			case 2:
				HashMap<String, Object> bannerObject2 = new HashMap<String, Object>();
				bannerObject2.put("image", R.drawable.img_guide_third);
				//bannerObject2.put("title", Title[i]);
				_Data.add(bannerObject2);
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
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.guid_image_view);
		final int imageDrawable = (Integer) _Data.get(position).get("image");
		imageView.setImageResource(imageDrawable);
		
		RelativeLayout btn_parent_layout = (RelativeLayout)imageLayout.findViewById(R.id.image_framelayout);
		Button start_login = new Button(_activity);
		start_login.setBackgroundResource(R.drawable.ic_guide_btn_selector);
		start_login.setGravity(Gravity.CENTER);
		start_login.setText(R.string.guide_btn_text);
		start_login.setTextSize(18);
		start_login.setTextColor(_activity.getResources().getColor(R.color.guid_btn_text_color));
		
		start_login.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT));
		
		if (position == 2) {
			btn_parent_layout.addView(start_login);
			start_login.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {					
					Message msg = handler.obtainMessage();
					msg.what = GuidSplashActivity.COMPLETE;
					handler.sendMessage(msg);
				}
			});
		}
		container.addView(imageLayout);
		
		return imageLayout;
	}
	
	@Override
	public Parcelable saveState() {
		return null;
	}
}
