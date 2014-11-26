package org.chinenv.onroad.ui.listener;

import net.simonvt.menudrawer.MenuDrawer;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.activity.GuidSplashActivity;
import org.chinenv.onroad.ui.activity.RegisterAndLoginActivity;
import org.chinenv.onroad.ui.fragment.DiscoverFragment;
import org.chinenv.onroad.ui.fragment.HandpickFragment;
import org.chinenv.onroad.ui.fragment.UserHomeFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeMenuItemFragmentClickListener implements OnClickListener {
	private static final int    DISCOVER_FRAGMENT = 11;
	private static final int    HANDPICK_FRAGMENT = 12;
	
	private Activity activity;
	private MenuDrawer mDrawer;
	
	public HomeMenuItemFragmentClickListener(Activity activity, MenuDrawer mDrawer) {
		this.activity = activity;
		this.mDrawer = mDrawer;
	}

	@Override
	public void onClick(View v) {
		FragmentManager fm = activity.getFragmentManager();
        Fragment discoverFragment = fm.findFragmentByTag(DiscoverFragment.TAG);
        Fragment handpickFragment = fm.findFragmentByTag(HandpickFragment.TAG);
        Fragment userhomeFragment = fm.findFragmentByTag(UserHomeFragment.TAG);
        
        FragmentTransaction ft = fm.beginTransaction();	         
        if(discoverFragment!=null)        	
            ft.hide(discoverFragment);

		if (handpickFragment != null)
			ft.hide(handpickFragment);
		
		if (userhomeFragment != null)
			ft.hide(userhomeFragment);

		switch (v.getId()) {
		case R.id.menu_seek:
			if (discoverFragment == null) {
    			ft.add(R.id.frameLayout_home_content, new DiscoverFragment(), DiscoverFragment.TAG);
    		} else {
    			ft.show(discoverFragment);
    		}
			setActionbar(v.getId());
			break;
		case R.id.menu_handpick:
			if (handpickFragment == null) {
    			ft.add(R.id.frameLayout_home_content, new HandpickFragment(), HandpickFragment.TAG);
    		} else {
    			ft.show(handpickFragment);
    		}
			setActionbar(v.getId());
			break;
		case R.id.action_discover_layout:
			if (discoverFragment == null) {
    			ft.add(R.id.frameLayout_home_content, new DiscoverFragment(), DiscoverFragment.TAG);
    		} else {
    			ft.show(discoverFragment);
    		}
			setActionbar(R.id.menu_seek);
			break;
		
		case R.id.has_logined:
			if (userhomeFragment == null) {
    			ft.add(R.id.frameLayout_home_content, new UserHomeFragment(), UserHomeFragment.TAG);
    		} else {
    			ft.show(userhomeFragment);
    		}
			setActionbar(R.id.has_logined);
			break;
		default:
			break;
		}
		ft.commit();
		mDrawer.setSlideDrawable(R.drawable.icon_more);
		mDrawer.closeMenu();

	}
	
	
	private void setActionbar(int viewID){
		android.app.ActionBar actionBar = activity.getActionBar(); 
    	actionBar.setCustomView(R.layout.custom_home_menu);
    	actionBar.setDisplayHomeAsUpEnabled(true);
    	actionBar.setDisplayOptions (ActionBar. DISPLAY_SHOW_CUSTOM );
    	
    	LinearLayout abDiscover = (LinearLayout)actionBar.getCustomView().findViewById(R.id.action_discover_layout);
    	LinearLayout discoverInput = (LinearLayout)actionBar.getCustomView().findViewById(R.id.discvoer_input_layout);
    	LinearLayout abTitlLayout = (LinearLayout)actionBar.getCustomView().findViewById(R.id.ab_title_layout);
    	TextView abTitle = (TextView)actionBar.getCustomView().findViewById(R.id.ab_title);
    	
		switch (viewID) {
		case R.id.menu_seek:
			abDiscover.setVisibility(View.GONE);
			abTitlLayout.setVisibility(View.GONE);
			discoverInput.setVisibility(View.VISIBLE);
			break;
			
		case R.id.menu_handpick:
			abTitlLayout.setVisibility(View.VISIBLE);
			abDiscover.setVisibility(View.VISIBLE);
			discoverInput.setVisibility(View.GONE);
			abTitle.setText(R.string.home_menu_discover);
			abDiscover.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((HomeActivity) activity).initMenuContent(DISCOVER_FRAGMENT);
				}
			});
			break;
			
		case R.id.has_logined:
			abTitlLayout.setVisibility(View.VISIBLE);
			abDiscover.setVisibility(View.GONE);
			discoverInput.setVisibility(View.GONE);
			abTitle.setText(R.string.home_user_home_ab_title);
			break;
		default:
			break;
		}
			
	}
}
