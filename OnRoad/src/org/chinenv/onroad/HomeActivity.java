package org.chinenv.onroad;

import net.simonvt.menudrawer.MenuDrawer;

import org.chinenv.onroad.ui.adapter.HomeListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class HomeActivity extends Activity {

private static final String TAG = "MainActivity";


	
    private MenuDrawer 						mDrawer;
    private ImageView  						actionDiscover;
    private boolean          				direction;
    
   
    
    View 									menuView;
    View 									contentView;
    
    View view;
    
    ListView								_mList;
    HomeListAdapter								mAdapter;
    
    /**
     * 根据offset来改变icon的alpha值
     * */
    Handler									mHandler = new Handler(){
    	@SuppressWarnings("deprecation")
		public void handleMessage(android.os.Message msg) {
    		actionDiscover = (ImageView) findViewById(R.id.action_discover);
    		LinearLayout discoverLayout = (LinearLayout)findViewById(R.id.action_discover_layout);
    		int offset = msg.getData().getInt("offset");        		
    		int alpha = (480-offset)*17/32;
    		Log.i(TAG, "------>alpha:"+alpha);
    		if (alpha >= 247) {
    			discoverLayout.setClickable(true);
			}else {
				discoverLayout.setClickable(false);
			}
    		actionDiscover.setAlpha(alpha);
    		
    	};
    };
    
    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);               
        mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY);

        menuView = LayoutInflater.from(this)
        		.inflate(R.layout.home_activity_menu, null, false);
        contentView = LayoutInflater.from(this)
        		.inflate(R.layout.home_activity_content, null, false);
        
        
        actionDiscover = (ImageView) findViewById(R.id.action_discover);
        
        setActionBar();
        mDrawer.setMenuView(menuView);
        
        setDrawerContent();
        mDrawer.setContentView(contentView);
        mDrawer.setSlideDrawable(R.drawable.icon_more);
        mDrawer.setDrawerIndicatorEnabled(true);
        mDrawer.peekDrawer(1000, 0);
        
        setLisenerOfDrawer();
        
    }
    
    /**
     * 设置drawermenu 的content内容
     * */
    private void setDrawerContent(){
    	_mList = (ListView) contentView.findViewById(R.id.home_content_list);
//    	mAdapter = new HomeListAdapter(this);
    	mAdapter = new HomeListAdapter(this);
    	_mList.setAdapter(mAdapter);
    	
//    	LoopViewPager pager = (LoopViewPager)view.findViewById(R.id.pager);
//    	HomeListPagerAdapter pageradapter = new HomeListPagerAdapter(2);
//    	pager.setAdapter(pageradapter);
    }
    
        
    private void setActionBar(){
    	android.app.ActionBar actionBar = this.getActionBar(); 
    	actionBar.setCustomView(R.layout.custom_home_menu);
    	
    	actionBar.setDisplayHomeAsUpEnabled(true);
    	actionBar.setDisplayOptions (ActionBar. DISPLAY_SHOW_CUSTOM 
                | ActionBar.DISPLAY_SHOW_TITLE
                );

		int title = R.string.home_menu_discover;
		this.getActionBar().setTitle(title);	
	}
    
    private void setLisenerOfDrawer(){
    	
    	mDrawer.setOnDrawerStateChangeListener(new MenuDrawer.OnDrawerStateChangeListener() {
			
			@Override
			public void onDrawerStateChange(int oldState, int newState) {
				if (oldState < newState) {
					direction = true;
				}else {
					direction = false;
				}

			}
			
			@Override
			public void onDrawerSlide(float openRatio, int offsetPixels) {
				int lastOffset = 0;
				if (Math.abs(lastOffset-offsetPixels) > 2) {
					Message msg = mHandler.obtainMessage();
					Bundle bundle = new Bundle();
					bundle.putInt("offset", offsetPixels);
					msg.setData(bundle);
					mHandler.sendMessage(msg);  
					lastOffset = offsetPixels;
				}	
				
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.toggleMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
