package org.chinenv.onroad.weight;

import org.chinenv.onroad.loopview.LoopViewPager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyLoopViewpager extends LoopViewPager {

	public MyLoopViewpager(Context context){
		super(context);
	}

	
	public MyLoopViewpager(Context context, AttributeSet attrs) {
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
			getParent().getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(ev.getX() - mDownX) > 12
					|| (Math.abs(ev.getX() - mDownX) - Math.abs(ev.getY() - mDownY)) > 12) {
				getParent().getParent().requestDisallowInterceptTouchEvent(true);
			}else{
				getParent().getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	

}
