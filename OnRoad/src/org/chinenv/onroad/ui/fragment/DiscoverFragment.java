package org.chinenv.onroad.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.chinenv.onroad.HomeActivity;
import org.chinenv.onroad.R;
import org.chinenv.onroad.statictestdata.DiscoverSearchTestData;
import org.chinenv.onroad.ui.adapter.DiscoverSearchListAdapter;
import org.chinenv.onroad.ui.listener.SwipeDismissListViewTouchListener;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DiscoverFragment extends Fragment {
	static public String TAG = "discover";
	
	Context 			mContext = this.getActivity();
	
	
	View				_view;
	LayoutInflater		_layoutInflater;	
	
	EditText 			searchInput;
	
	LinearLayout		_noSearchLayout;
	LinearLayout		_searchListLyaout;
	
	Activity			_activity;
	ListView			_list;
	
	
	boolean				_haseSearch = false;
	
	List<String> 		searchData = new ArrayList<String>();
	DiscoverSearchListAdapter searchListAdapter ;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_activity = this.getActivity();
		searchListAdapter = new DiscoverSearchListAdapter(this.getActivity());
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setActionbar();
		if (_view != null) {
			ViewGroup parent = (ViewGroup) _view.getParent();
			if (parent != null) {
				parent.removeView(_view);
			}
			return _view;
		}
		_view = inflater.inflate(R.layout.discover_fragment_content, container, false);		
		
		setContent();
		return _view;
	}
	
	//设置actionbar图标
	private void setActionbar(){
		android.app.ActionBar actionBar = _activity.getActionBar(); 
		((HomeActivity) _activity).getMenuDrawer().setSlideDrawable(R.drawable.icon_more);

		searchInput = (EditText) actionBar.getCustomView().findViewById(R.id.discover_inputt);
		
	}
	
	private void bindView(){
		_list = (ListView)_view.findViewById(R.id.search_list);
		
		_noSearchLayout = (LinearLayout)_view.findViewById(R.id.no_search);
		_searchListLyaout = (LinearLayout)_view.findViewById(R.id.search_list_layout);
	}
	
	private void setContent(){
		bindView();
		setViewDisplay();

		searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId==EditorInfo.IME_ACTION_SEARCH 
						||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
					Log.i(TAG, "--------->searchInput.getText():"+searchInput.getText());
					if ("".equals(searchInput.getText().toString().trim())
							|| searchInput.getText().toString().length() == 0 ) {
						Log.i(TAG, "------>searchInput.getText().toString().length():"+searchInput.getText().toString().length());
					}else {
						String data= searchInput.getText().toString();
						DiscoverSearchTestData.setSearchPlaces(data);
						Log.i(TAG, "------>data.siza:"+DiscoverSearchTestData.getSearchPlaces().size());
						
						if (DiscoverSearchTestData.getSearchPlaces().size() > 1) {
							searchListAdapter.notifyDataSetChanged();
						}else {
							setViewDisplay();
						}						
					}
					searchInput.setText("");
					return true;
				}
				return false;
			}
		});

		SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        _list,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                	DiscoverSearchTestData.removeItem(position);
                                	if (DiscoverSearchTestData.getSearchPlaces().size() == 0) {
                                		Message msg = handler.obtainMessage();
                                		msg.what = 1;
                                		handler.sendMessage(msg);
									}
                                }
                                searchListAdapter.notifyDataSetChanged();
                            }
                        });
		
		_list.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
		_list.setOnScrollListener(touchListener.makeScrollListener());
		
	}
	
	private void setViewDisplay(){		
		searchData = DiscoverSearchTestData.getSearchPlaces(); 
		if (!searchData.equals(null) && searchData.size() != 0 ) {
			_noSearchLayout.setVisibility(View.GONE);
			_searchListLyaout.setVisibility(View.VISIBLE);
			_list.setAdapter(searchListAdapter);
			return;
		}
		
	}
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				_noSearchLayout.setVisibility(View.VISIBLE);
				_searchListLyaout.setVisibility(View.GONE);
			}
		};
	};
	
}
