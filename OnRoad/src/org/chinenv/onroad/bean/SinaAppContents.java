package org.chinenv.onroad.bean;

public class SinaAppContents {
	
	public interface Constants {
		public static final String APP_KEY = "827868301"; // 应用的APP_KEY
		public static final String REDIRECT_URL = "https://github.com/SimonJoker";// 应用的回调页
		
		public static final String APP_SECRET = "aa4daab82e9d7b63ec962f03f565a57a";
		// 应用申请的高级权限
		public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
				+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
				+ "follow_app_official_microblog," + "invitation_write";
		
		/** 通过 code 获取 Token 的 URL */
	    public static final String OAUTH2_ACCESS_TOKEN_URL = "https://open.weibo.cn/oauth2/access_token";
		
		}

}
