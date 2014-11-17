package org.chinenv.onroad.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @author way
 * */
public class JudgeIsFirstUseUtil 
{

	private static String WHAT_IS_NEW_PRE_NAME = "what_is_new_pre_name";
	
	private static String FIRST_USE_FLAG = "first_use_flag";
	
    public static boolean isFirstUse(Context context)
    {
    	SharedPreferences sharedPreferences = context.getSharedPreferences(WHAT_IS_NEW_PRE_NAME, Activity.MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean(FIRST_USE_FLAG, true);
        return flag;
    }
    
    
    public static void saveFirstUseFlag(Context context)
    {
    	SharedPreferences sharedPreferences = context.getSharedPreferences(WHAT_IS_NEW_PRE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_USE_FLAG, false);
        editor.commit();
    }
    
}
