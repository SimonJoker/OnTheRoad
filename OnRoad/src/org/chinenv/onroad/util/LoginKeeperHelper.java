/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chinenv.onroad.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 该类定义了微博授权时所需要的参数。
 * 
 * @author SINA
 * @since 2013-10-07
 */
public class LoginKeeperHelper {
    private static final String PREFERENCES_NAME = "org_chinenv_onroad";

    private static final String PART_NAME           = "part_name";
    
    private static final String LOGIN_STATUS_PREFERENCES_NAME = "org_chinenv_onroad_login_status";
    private static final String HASE_LOGINED           = "has_login";

    
    /**
     * 保存授权登陆的第三方平台  对象到 SharedPreferences。
     * 
     * @param context 应用程序上下文环境
     * @param token   Token 对象
     */
    public static void writeLoginPartName(Context context, String partName) {
        if (null == context || null == partName) {
            return;
        }
        
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putString(PART_NAME, partName);
        editor.commit();
    }
    
    
    
    /**
     * 保存当前的登陆状态 对象到 SharedPreferences。
     * 
     * @param context 应用程序上下文环境
     * @param loginStatus   是否已登录 
     */
    public static void wirteLoginStatus(Context context, boolean loginStatus) {
        if (null == context) {
            return;
        }
        
        SharedPreferences pref = context.getSharedPreferences(LOGIN_STATUS_PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.putBoolean(HASE_LOGINED, loginStatus);
        editor.commit();
    }

    /**
     * 从 SharedPreferences 读取  授权登陆的第三方平台 信息。
     * 
     * @param context 应用程序上下文环境
     * 
     * @return 返回 授权登陆的第三方平台 对象
     */
    public static String readLoginPartName(Context context) {
        if (null == context) {
            return null;
        }
        
        String partName;
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        partName = pref.getString(PART_NAME, "");
        return partName;
    }
    
    /**
     * 从 SharedPreferences 读取  授权登陆的第三方平台 信息。
     * 
     * @param context 应用程序上下文环境
     * 
     * @return 返回 授权登陆的第三方平台 对象
     */
    public static boolean readLoginStatus(Context context) {
        if (null == context) {
            return false;
        }
        
        boolean loginStatus;
        SharedPreferences pref = context.getSharedPreferences(LOGIN_STATUS_PREFERENCES_NAME, Context.MODE_APPEND);
        loginStatus = pref.getBoolean(HASE_LOGINED, false);
        return loginStatus;
    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     * 
     * @param context 应用程序上下文环境
     */
    public static void clear(Context context) {
        if (null == context) {
            return;
        }
        
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
    
    
    /**
     * 清空 SharedPreferences 中用户登陆状态
     * 
     * @param context 应用程序上下文环境
     */
    public static void clearLoginStatus(Context context) {
        if (null == context) {
            return;
        }
        
        SharedPreferences pref = context.getSharedPreferences(LOGIN_STATUS_PREFERENCES_NAME, Context.MODE_APPEND);
        Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
