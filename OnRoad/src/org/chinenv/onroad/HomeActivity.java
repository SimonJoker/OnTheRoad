package org.chinenv.onroad;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;

import org.chinenv.onroad.ui.fragment.DiscoverFragment;
import org.chinenv.onroad.ui.fragment.HandpickFragment;
import org.chinenv.onroad.ui.listener.HomeMenuItemFragmentClickListener;
import org.chinenv.onroad.ui.listener.HomeMenuItenIntentClikListener;
import org.chinenv.onroad.util.ImageLoaderHelper;
import org.chinenv.onroad.util.LoginKeeperHelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends Activity {

	private static final String TAG = "MainActivity";

	private static final int    DISCOVER_FRAGMENT = 11;
	private static final int    HANDPICK_FRAGMENT = 12;

	
    public MenuDrawer 						mDrawer;
    private ImageView  						actionDiscover;
    private boolean          				direction;
    
   
    Context									mContext = this;
    Activity								_activity =this;
    View 									menuView;
    View 									contentView;
    
    private LinearLayout					_menuSeek;
    private LinearLayout					_mennuHandpick;
    private LinearLayout					_menuWishList;

    private LinearLayout					_menuSetting;
    private LinearLayout					_menuLogin;
    private LinearLayout					_menuHasLogined;
    
    private boolean							isLogined = false;

    View 									view;
    
    
    
    private ImageLoadingListener 			animateFirstListener 
    												= new AnimateFirstDisplayListener();
    HomeMenuItenIntentClikListener			menuItemListener;
    HomeMenuItemFragmentClickListener		menuFragmentListener;
  
    
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
        initImageLoader(this);
        ShareSDK.initSDK(this);
        
        
        mDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY);

        menuView = LayoutInflater.from(this)
        		.inflate(R.layout.home_activity_menu, null, false);
        contentView = LayoutInflater.from(this)
        		.inflate(R.layout.home, null, false);
        actionDiscover = (ImageView) findViewById(R.id.action_discover);        
        setActionBar();
        
        mDrawer.setMenuView(menuView);
        setLoginUserInfo();
        
        //设置主页面
        initMenuContent(HANDPICK_FRAGMENT);
        mDrawer.setContentView(contentView);
        mDrawer.setSlideDrawable(R.drawable.icon_more);
        mDrawer.setDrawerIndicatorEnabled(true);
        mDrawer.peekDrawer(1000, 0);
        mDrawer.closeMenu();
        
        setLisenerOfDrawer();
        bindViews();
        
    }
    
    //根据登陆状态改变登陆view
    public void setLoginUserInfo(){
    	isLogined = LoginKeeperHelper.readLoginStatus(this);
    	
    	_menuWishList = (LinearLayout)menuView.findViewById(R.id.menu_wish_list);
    	
    	_menuLogin = (LinearLayout)findViewById(R.id.login_layout);
    	_menuHasLogined = (LinearLayout)findViewById(R.id.has_logined);

    	CircleImageView userIcon = (CircleImageView)findViewById(R.id.user_icon);
    	TextView userName = (TextView)findViewById(R.id.user_name);

    	if (isLogined) {
    		_menuWishList.setVisibility(View.VISIBLE);
    		_menuWishList.setClickable(true);
    		setLoginView(userIcon, userName);
		}else {
			_menuWishList.setVisibility(View.GONE);
			_menuWishList.setClickable(false);
			_menuLogin.setVisibility(View.VISIBLE);
			_menuHasLogined.setVisibility(View.GONE);
		}
    	
    }
    
    
    private void setLoginView(CircleImageView userIcon, TextView userName){
    	String part = LoginKeeperHelper.readLoginPartName(this);
		Platform partPlatform = ShareSDK.getPlatform(mContext, part);
		String iconURl = partPlatform.getDb().getUserIcon();
		ImageLoaderHelper iconOption = new ImageLoaderHelper(mContext);
		_menuLogin.setVisibility(View.GONE);
		_menuHasLogined.setVisibility(View.VISIBLE);
		ImageLoader.getInstance().displayImage(iconURl, userIcon
						, iconOption.getUserIconOptions(), animateFirstListener);
		userName.setText(partPlatform.getDb().getUserName());
    }
    
    
    
    private void bindViews(){
    	//fragment跳转
    	menuFragmentListener = new HomeMenuItemFragmentClickListener(this, mDrawer);
    	_menuSeek = (LinearLayout)menuView.findViewById(R.id.menu_seek);
    	_menuSeek.setOnClickListener(menuFragmentListener);
    	
    	_mennuHandpick = (LinearLayout)menuView.findViewById(R.id.menu_handpick);
    	_mennuHandpick.setOnClickListener(menuFragmentListener);
    	
    	//activity跳转
    	menuItemListener = new HomeMenuItenIntentClikListener(this);

    	_menuWishList.setOnClickListener(menuFragmentListener);

    	_menuSetting = (LinearLayout)menuView.findViewById(R.id.menu_setting);
    	_menuSetting.setOnClickListener(menuItemListener);
    	
    	
    	_menuLogin = (LinearLayout)menuView.findViewById(R.id.login_layout);
    	_menuLogin.setOnClickListener(menuItemListener);
    	
    	_menuHasLogined.setOnClickListener(menuFragmentListener);
    }
    


    private void setActionBar(){
    	android.app.ActionBar actionBar = this.getActionBar(); 
    	actionBar.setCustomView(R.layout.custom_home_menu);
    	
    	actionBar.setDisplayHomeAsUpEnabled(true);
    	actionBar.setDisplayOptions (ActionBar. DISPLAY_SHOW_CUSTOM );

		LinearLayout abDiscover = (LinearLayout)actionBar.getCustomView().findViewById(R.id.action_discover_layout);
		abDiscover.setVisibility(View.VISIBLE);
		
		LinearLayout discoverInput = (LinearLayout)actionBar.getCustomView().findViewById(R.id.discvoer_input_layout);
		discoverInput.setVisibility(View.GONE);

    	TextView abTitle = (TextView)actionBar.getCustomView().findViewById(R.id.ab_title);
    	abTitle.setText(R.string.home_menu_discover);
    	
		abDiscover.setOnClickListener(new HomeMenuItemFragmentClickListener(_activity, mDrawer));
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

    //初始化imageloader
    public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024) // 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
    
    
    
    //加载图片监听
    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

    //初始fragment使用“精选”
    public void initMenuContent(int fragment){
    	FragmentManager fm = getFragmentManager();
    	Fragment handpickFragment = fm.findFragmentByTag(HandpickFragment.TAG);
    	Fragment discoverFragment = fm.findFragmentByTag(DiscoverFragment.TAG);
    	
    	FragmentTransaction ft = fm.beginTransaction();	
    	if (handpickFragment != null)
			ft.hide(handpickFragment);
    	
    	if(discoverFragment!=null)        	
            ft.hide(discoverFragment);
    	
    	switch (fragment) {
		case DISCOVER_FRAGMENT:
			if (discoverFragment == null) {
    			ft.add(R.id.frameLayout_home_content, new DiscoverFragment(), DiscoverFragment.TAG);
    		} else {
    			ft.show(discoverFragment);
    		}
			break;
		case HANDPICK_FRAGMENT:
			if (handpickFragment == null) {
    			ft.add(R.id.frameLayout_home_content, new HandpickFragment(), HandpickFragment.TAG);
    		} else {
    			ft.show(handpickFragment);
    		}
			break;
		default:
			break;
    	}
    	
    	ft.commit();
    }
    
    public MenuDrawer getMenuDrawer(){
    	return mDrawer;
    }
}
