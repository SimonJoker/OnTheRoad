package org.chinenv.onroad.ui.fragment;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.activity.GuidSplashActivity;
import org.chinenv.onroad.ui.activity.LikeUsActivity;
import org.chinenv.onroad.ui.activity.UserInfoActivity;
import org.chinenv.onroad.ui.adapter.HomeListAdapter;
import org.chinenv.onroad.ui.listener.HomeMenuItemFragmentClickListener;
import org.chinenv.onroad.util.LoginKeeperHelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class UserHomeFragment extends Fragment {
static public String TAG = "userhome";
	
	View									_view;
	LayoutInflater							_layoutInflater;	
	ListView								_mList;
	HomeListAdapter							mAdapter;
	
	private Activity						_activity;
	HomeMenuItemFragmentClickListener 		itemFragmentClikListener;
	
	LinearLayout							_lookUserInfo;
	LinearLayout							_likeThis;
	LinearLayout							_usage;
	LinearLayout							_serviceItem;
	LinearLayout							_privacyPolicy;
	
	LinearLayout							_logout;
	
	UserHomeClikListener					_listener;		
	HomeMenuItemFragmentClickListener		_logoutListener;
	
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
		_view = inflater.inflate(R.layout.user_home_fragment, container, false);	
		setContent();
		
		return _view;
	}

	
    private void setActionBar(){
    	android.app.ActionBar actionBar = _activity.getActionBar(); 
		((HomeActivity) _activity).getMenuDrawer().setSlideDrawable(R.drawable.icon_more);

	}
    
    
    private void bindViews(){
    	_lookUserInfo = (LinearLayout)_view.findViewById(R.id.look_user_info);
    	_likeThis = (LinearLayout)_view.findViewById(R.id.like_this);
    	_usage = (LinearLayout)_view.findViewById(R.id.how_use_this);
    	_serviceItem = (LinearLayout)_view.findViewById(R.id.service_item);
    	_privacyPolicy = (LinearLayout)_view.findViewById(R.id.privacy_policy);
    	_logout = (LinearLayout)_view.findViewById(R.id.logout);
    }
    
    private void setContent(){
    	bindViews();
    	setListener();
    	
    }
    
    private void setListener(){
    	_listener = new UserHomeClikListener(this.getActivity());
    	_lookUserInfo.setOnClickListener(_listener);
    	_likeThis.setOnClickListener(_listener);
    	_usage.setOnClickListener(_listener);
    	_serviceItem.setOnClickListener(_listener);
    	_privacyPolicy.setOnClickListener(_listener);
    	
    	_logoutListener = new HomeMenuItemFragmentClickListener(getActivity()
    			, ((HomeActivity) getActivity()).getMenuDrawer());
    	_logout.setOnClickListener(_logoutListener);
    	
    }
    
    
    
    private class UserHomeClikListener implements OnClickListener{
    	Activity	_activity;
    	
    	public UserHomeClikListener(Activity activity){
    		this._activity = activity;
    	}
    	
		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.look_user_info:
				intent = new Intent(_activity, UserInfoActivity.class);
				startActivity(intent);
				break;
			case R.id.like_this:
				intent = new Intent(_activity, LikeUsActivity.class);
				startActivity(intent);			
				break;
			case R.id.how_use_this:
				intent = new Intent(_activity, GuidSplashActivity.class);
				Bundle bundle = new Bundle();
				bundle.putBoolean("Useage", true);
				intent.putExtras(bundle);
				startActivity(intent);	
				break;
			case R.id.service_item:
				
				break;
			case R.id.privacy_policy:
				
				break;

			default:
				break;
			}
			
		}} 

}
