package org.chinenv.onroad.bean;

import org.chinenv.onroad.R;

public class GudidPageDatas {
		
	
	private static int[] TitleId = new int[]{R.string.guide_title_first
									, R.string.guide_title_second
									, R.string.guide_title_third
									, R.string.guide_title_fourth}; 
	
	private static int[] ContentId = new int[]{R.string.guide_content_first
									, R.string.guide_content_second
									, R.string.guide_content_third
									, R.string.guide_content_fourth}; 
	
	private static final int[] 	ImageID = new int[]{ R.drawable.appintro1
									, R.drawable.appintro2
									, R.drawable.appintro3
									, R.drawable.appintro4 };
	
	private static final int[] 	BtnBgID = new int[]{ R.color.guid_btn_bg_color_first
									, R.color.guid_btn_bg_color_secondt
									, R.color.guid_btn_bg_color_third
									, R.color.guid_btn_bg_color_fourth };

	public static int[] getTitleId() {
		return TitleId;
	}

	public void setTitleId(int[] titleId) {
		TitleId = titleId;
	}

	public static int[] getContentId() {
		return ContentId;
	}

	public void setContentId(int[] contentId) {
		ContentId = contentId;
	}

	public static int[] getImageid() {
		return ImageID;
	}

	public static int[] getBtnbgid() {
		return BtnBgID;
	}
	
}
