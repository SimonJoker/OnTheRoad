package org.chinenv.onroad.statictestdata;

import java.util.ArrayList;
import java.util.List;

import org.chinenv.onroad.R;

import android.content.Intent;


public class StaticData {
	
	static Integer[] aDrawable = new Integer[]{ R.drawable.a0, R.drawable.a1, R.drawable.a2, R.drawable.a3, 
								R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7 };
	static Integer[] bDrawable = new Integer[]{ R.drawable.b0, R.drawable.b1, R.drawable.b2 };
	static Integer[] cDrawable = new Integer[]{ R.drawable.c0, R.drawable.c1, R.drawable.c2, 
								R.drawable.c3, R.drawable.c4, R.drawable.c5 };
	static Integer[] dDrawable = new Integer[]{ R.drawable.d0, R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4 };
	static Integer[] eDrawable = new Integer[]{ R.drawable.e0, R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4 };
	static Integer[] fDrawable = new Integer[]{ R.drawable.f0, R.drawable.f1 };
	static Integer[] gDrawable = new Integer[]{ R.drawable.g0, R.drawable.g1, R.drawable.g2, R.drawable.g3 };
	
	static Integer[] tDrawable = new Integer[]{ R.drawable.t0, R.drawable.t1, R.drawable.t2, 
							R.drawable.t3, R.drawable.t4, R.drawable.t5, R.drawable.t6 };
	
	
	static Boolean[] bFlag = new Boolean[]{true, true, true, false, true, true, false};
	
	public static List<Integer[]> getDrawble(){
		List<Integer[]> list = new ArrayList<Integer[]>();
		list.add(aDrawable);
		list.add(bDrawable);
		list.add(cDrawable);
		list.add(dDrawable);
		list.add(eDrawable);
		list.add(fDrawable);
		list.add(gDrawable);
		
		return list;
	}
	
	public static List<Integer[]> getThemeData(){
		List<Integer[]> list = new ArrayList<Integer[]>();
		list.add(aDrawable);
		list.add(bDrawable);
		list.add(cDrawable);
		list.add(dDrawable);
		list.add(eDrawable);
		list.add(fDrawable);
		list.add(gDrawable);
		
		return list;
	}

	
	public static Integer[] getTouxiangIcon(){
		return tDrawable;
	}
	
	
	public static Integer[] getPrice(){
		return new Integer[]{256, 456, 234, 118, 368, 400, 211};
	}

	
	public static Boolean[] getFlag(){
		return bFlag;
	}
	
	public static void setFlag(int position, boolean flag){
		bFlag[position] = flag;
	}
	
	static String[] aCity = new String[]{"纽约", "香港", "夏威夷"};
	static String[] bCity = new String[]{"长沙", "上海", "重庆"};
	static String[] cCity = new String[]{"珠海", "扬州", "南宁"};
	static String[] dCity = new String[]{"北京", "里约热内卢", "齐齐哈尔"};
	static String[] eCity = new String[]{"香港中环", "深圳华强北", "重庆解放碑"};
	static String[] fCity = new String[]{"成都", "内蒙草原", "天津"};
	
	
	public static List<String[]> getCity(){
		List<String[]> list = new ArrayList<String[]>();
		list.add(aCity);
		list.add(bCity);
		list.add(cCity);
		list.add(dCity);
		list.add(eCity);
		list.add(fCity);
		
		return list;
	}
	
	final static String[] Title = new String[]{"最热门", "评价最高", "这周末", "音乐类", "户外、运动", "文化、史蕴", "爱心公益"};

	public static String[] getTitle() {
		return Title;
	}
	
	
	
	
}
