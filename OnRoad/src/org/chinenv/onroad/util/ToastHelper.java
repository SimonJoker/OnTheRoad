package org.chinenv.onroad.util;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
	static public void showShort(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	static public void showLong(Context context,int text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
	
	static public void showLong(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}
