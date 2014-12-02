package org.chinenv.onroad.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyThemeListViewPager extends ViewPager {
	public MyThemeListViewPager(Context context){
		super(context);
	}

	
	public MyThemeListViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		float mDownX = 0;
		float mDownY = 0;
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = ev.getX();
			mDownY = ev.getY();
			getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(ev.getX() - mDownX) > 12
					|| (Math.abs(ev.getX() - mDownX) - Math.abs(ev.getY() - mDownY)) > 12) {
				getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
			}else{
				getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(false);
			}
			break; 
		case MotionEvent.ACTION_UP:
			if (mDownX == ev.getX() && mDownY == ev.getY()) {
				getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
}
