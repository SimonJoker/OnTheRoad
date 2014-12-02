package org.chinenv.onroad.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.StaticData;
import org.chinenv.onroad.ui.adapter.HomeListAdapter;
import org.chinenv.onroad.ui.adapter.WishListTimelineAdapter;
import org.chinenv.onroad.ui.listener.HomeMenuItemFragmentClickListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class UserWishListFragment extends Fragment {
	static public String TAG = "wish_list";
	
	View									_view;
	LayoutInflater							_layoutInflater;	
	
	LinearLayout							_noWishList;
	LinearLayout							_hasWishList;
	
	ListView								_mList;
	WishListTimelineAdapter							mAdapter;
	
	ImageView								_wishListBanner;
	
	private Activity						_activity;
	HomeMenuItemFragmentClickListener 		itemFragmentClikListener;
	
	boolean									_hasData = false;
	private List<Integer>					index = new ArrayList<Integer>();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_activity = this.getActivity();
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "---->onCreateView");
		setActionBar();
		if (_view != null) {
			ViewGroup parent = (ViewGroup) _view.getParent();
			if (parent != null) {
				parent.removeView(_view);
			}
			return _view;
		}
		
		_view = inflater.inflate(R.layout.wish_list_fragment, container, false);	
		judgeHasData();
		setContent();
		
		return _view;
	}
	
	/**
	 * 判断拉取的数据中是否有标记“心愿单”的记录
	 * */
	private void judgeHasData(){
		if (!StaticData.getFlag().equals(null)) {
			for (int i = 0; i < StaticData.getFlag().length; i++) {
				if (StaticData.getFlag()[i]) {
					index.add(i);
				}else {
				}			
			}
			if (!index.equals(null) 
					&& index.size() > 0) {
				_hasData = true;
			}else {
				_hasData = false;
			}
		}
	}


	private void bindView(){
		_noWishList = (LinearLayout)_view.findViewById(R.id.no_wish_data);
		_hasWishList = (LinearLayout)_view.findViewById(R.id.has_wish_data);
		
		_wishListBanner = (ImageView)_view.findViewById(R.id.banner_image);
		_mList = (ListView)_view.findViewById(R.id.wish_time_line);
		
	}
	
	
	private void setContent(){
		bindView();
		setViewDisplay();
		bindListView();
		
	}
	
	private void bindListView(){
		mAdapter = new WishListTimelineAdapter(this.getActivity(), index);
		_mList.setAdapter(mAdapter);
	}
	
	
	/**  根据是否有心愿单，改变视图  **/
	private void setViewDisplay(){
		if (_hasData) {
			_noWishList.setVisibility(View.GONE);
			_hasWishList.setVisibility(View.VISIBLE);
		}else {
			_noWishList.setVisibility(View.VISIBLE);
			_hasWishList.setVisibility(View.GONE);
		}
	}
	
	
    private void setActionBar(){
    	android.app.ActionBar actionBar = _activity.getActionBar(); 
		((HomeActivity) _activity).getMenuDrawer().setSlideDrawable(R.drawable.icon_more);	

	}
}
