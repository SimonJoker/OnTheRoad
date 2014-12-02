package org.chinenv.onroad.ui.activity;

import org.chinenv.onroad.R;
import org.chinenv.onroad.bean.Constants;
import org.chinenv.onroad.statictestdata.StaticData;
import org.chinenv.onroad.ui.adapter.ActivityContentPagerAdapter;
import org.chinenv.onroad.util.ToastHelper;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.AMap.OnMarkerDragListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

public class ActivityContentAcvitity extends Activity implements OnMarkerClickListener,
	OnInfoWindowClickListener,  OnMapLoadedListener,OnMarkerDragListener,
	InfoWindowAdapter{
	private static final String TAG = "ActivityContentAcvitity";
	
	ViewPager				_pager;
	ImageView				_wishImage;
	TextView				_activityPrice;
	TextView				_totalPhotos;
	TextView				_currentPhotos;
	TextView				_evaluteText;
	TextView				_descriptionText;
	
	ActivityContentPagerAdapter _pagerAdapter;
	
	private int 			index = 0;
	private MapView 		mapView;
	private AMap 			aMap;
	private Marker 			marker2;
	
	private MarkerOptions 	markerOption;
	
	private boolean			infoWindowOpen = false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_activity);
		setActionbar();
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState); // 此方法必须重写
		
		int index = this.getIntent().getExtras().getInt("INDEX");
		Log.i(TAG, "------->index:"+index);
		setContent();
		
	}

	private void bindViews(){
		_pager = (ViewPager)findViewById(R.id.activity_content_pager);
		_wishImage = (ImageView)findViewById(R.id.activity_wish_list);
		_activityPrice = (TextView)findViewById(R.id.activity_price);
		_currentPhotos = (TextView)findViewById(R.id.current_position);
		_totalPhotos = (TextView)findViewById(R.id.photo_total);
		_evaluteText = (TextView)findViewById(R.id.evaluate_content);
		_descriptionText = (TextView)findViewById(R.id.activity_description);
		
	}
	
	
	private void setContent(){
		bindViews();
		_currentPhotos.setText("1");
		_pagerAdapter = new ActivityContentPagerAdapter(index);
		_pager.setAdapter(_pagerAdapter);
		setPagerListener();
		setWishList();
		setTextViewDisplay();
		_activityPrice.setText(StaticData.getPrice()[index]+"");
		_totalPhotos.setText(StaticData.getDrawble().get(index).length+"");
		initMap();
		
	}
	
	
	private void setTextViewDisplay(){
		TextViewDisplayListener textViewListener = new TextViewDisplayListener();
		_evaluteText.setOnClickListener(textViewListener);
		_descriptionText.setOnClickListener(textViewListener);
	}
	
	
	private void setPagerListener(){
		_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				_currentPhotos.setText((position+1)+"");
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	
	
	
	
	/**
	 * 设置心愿单记录
	 * */
	private void setWishList(){
		if (StaticData.getFlag()[index]) {
			_wishImage.setImageResource(R.drawable.wishlist_heart_selected);
		}else {
			_wishImage.setImageResource(R.drawable.wishlist_heart_unselected);
		}
		
		_wishImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (StaticData.getFlag()[index]) {
					_wishImage.setImageResource(R.drawable.wishlist_heart_unselected);
					StaticData.setFlag(index, false);
				}else {
					_wishImage.setImageResource(R.drawable.wishlist_heart_selected);
					StaticData.setFlag(index, true);
				}
			}
		});
	}
	

	/**
	 * 设置actionbar，自定义actionbar的布局
	 * */
	private void setActionbar(){

		MemuClickListener menuListener = new MemuClickListener();
		ActionBar actionbar = this.getActionBar();
		actionbar.show();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setDisplayOptions (ActionBar. DISPLAY_SHOW_CUSTOM);
		actionbar.setCustomView(R.layout.theme_list_activity_custom_actionbar);
		LinearLayout btn_back = (LinearLayout)actionbar.getCustomView().findViewById(R.id.theme_action_back);
		btn_back.setOnClickListener(menuListener);
		
		ImageView share_btn = (ImageView)actionbar.getCustomView().findViewById(R.id.theme_overflow);
		share_btn.setImageResource(R.drawable.icon_share);
		
		final LinearLayout btn_more = (LinearLayout)actionbar.getCustomView().findViewById(R.id.theme_action);
		
		btn_more.setOnClickListener(menuListener);

	}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}
	
	
	
	
	private class MemuClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.theme_action_back:
				onBackPressed();
				break;

			case R.id.theme_action:
				
				break;
			default:
				break;
			}
		}}
	
	private void initMap() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
	}

	private void setUpMap() {
//		aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
		aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
		addMarkersToMap();// 往地图上添加marker
	}
	
	
	/**
	 * 在地图上添加marker
	 */
	private void addMarkersToMap() {

		markerOption = new MarkerOptions();
		markerOption.position(Constants.BEIJING_TUSHUGUAN);
		markerOption.title("北京朝阳区东三环南路88号").snippet("电话：010-67358114");
		markerOption.draggable(true);
//		markerOption.icon(BitmapDescriptorFactory
//				.fromResource(R.drawable.ico_notice_location));//自定义定位图片
		marker2 = aMap.addMarker(markerOption);

	}
	
		
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public View getInfoContents(Marker marker) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		return null;
	}

	@Override
	public void onMapLoaded() {
		// 设置所有maker显示在当前可视区域地图中
				LatLngBounds bounds = new LatLngBounds.Builder()
						.include(Constants.BEIJING_TUSHUGUAN)
						.build();
				aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.BEIJING_TUSHUGUAN, 18));
				
//				aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
//						39.910695, 116.372830), 19));
	}



	@Override
	public void onInfoWindowClick(Marker marker) {
		ToastHelper.showShort(this, "你点击了infoWindow窗口" + marker.getTitle());
		
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		if (infoWindowOpen) {
			marker.showInfoWindow();
		}else {
			marker.hideInfoWindow();
		}
		
		return false;
	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}	
	
	
	//设置textView收缩和展开
	private class TextViewDisplayListener implements OnClickListener{
		Boolean flag = true;

		@Override
		public void onClick(View v) {
			if(flag){
			    
			     flag = false;
			     ((TextView) v).setEllipsize(null); // 展开
			     ((TextView) v).setSingleLine(flag);
			    }else{
			     flag = true;
			     ((TextView) v).setLines(4);
			     ((TextView) v).setEllipsize(TextUtils.TruncateAt.END); // 收缩
			    }
		}}
	
}
