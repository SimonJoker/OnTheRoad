package org.chinenv.onroad.util;

import org.chinenv.onroad.R;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class ImageLoaderHelper {

	private Context mContext;
	
	public ImageLoaderHelper(Context mContext) {
		this.mContext = mContext;
	}
	
	public DisplayImageOptions option1 = new DisplayImageOptions.Builder()
												.showImageOnLoading(R.drawable.user_icon_default)
												.showImageForEmptyUri(R.drawable.user_icon_default)
												.showImageOnFail(R.drawable.user_icon_default)
												.cacheInMemory(true)
												.cacheOnDisk(true)
												.considerExifParams(true)
												.displayer(new SimpleBitmapDisplayer())
												.build();
	
	public DisplayImageOptions getUserIconOptions(){
		return option1;
	}
}
