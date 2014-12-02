package org.chinenv.onroad.ui.fragment;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.adapter.HomeListAdapter;
import org.chinenv.onroad.ui.listener.HomeMenuItemFragmentClickListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HandpickFragment extends Fragment {
	static public String TAG = "handpick";
	
	View									_view;
	LayoutInflater							_layoutInflater;	
	ListView								_mList;
	HomeListAdapter							mAdapter;
	
	private Activity						_activity;
	HomeMenuItemFragmentClickListener 		itemFragmentClikListener;
	
	
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
		
		_view = inflater.inflate(R.layout.handpick_fragment_content, container, false);	
		setDrawerContent();
		return _view;
	}

	
    /**
     * 设置drawermenu 的content内容
     * */
    private void setDrawerContent(){
    	_mList = (ListView) _view.findViewById(R.id.home_content_list);
//    	mAdapter = new HomeListAdapter(this);
    	mAdapter = new HomeListAdapter(_activity);
    	_mList.setAdapter(mAdapter);
    	
    }

    private void setActionBar(){
    	Log.i(TAG, "---->setActionBar");
    	android.app.ActionBar actionBar = _activity.getActionBar(); 
		((HomeActivity) _activity).getMenuDrawer().setSlideDrawable(R.drawable.icon_more);	

	}
}
