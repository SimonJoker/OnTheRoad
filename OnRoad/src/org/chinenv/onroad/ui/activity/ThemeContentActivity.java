package org.chinenv.onroad.ui.activity;

import org.chinenv.onroad.R;
import org.chinenv.onroad.ui.adapter.ThemeContentListAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ThemeContentActivity extends Activity {
	private static final String TAG = "ThemeContentActivity";
	
	ListView					_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theme_activity);
		setActionbar();
		setContent();

	}
	
	private void bindViews(){
		_list = (ListView)findViewById(R.id.theme_list);
	}
	
	
	private void setContent(){
		bindViews();
		ThemeContentListAdapter themeListAdapter = new ThemeContentListAdapter(this);
		_list.setAdapter(themeListAdapter);
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
	
}
