package org.chinenv.onroad.bean;

import java.util.ArrayList;
import java.util.List;

import org.chinenv.onroad.R;


public class StaticData {
	
	static Integer[] aDrawable = new Integer[]{ R.drawable.a1, R.drawable.a2, R.drawable.a3 };
	static Integer[] bDrawable = new Integer[]{ R.drawable.b1, R.drawable.b2, R.drawable.b3 };
	static Integer[] cDrawable = new Integer[]{ R.drawable.c1, R.drawable.c2, R.drawable.c3 };
	static Integer[] dDrawable = new Integer[]{ R.drawable.d1, R.drawable.d2, R.drawable.d3 };
	static Integer[] eDrawable = new Integer[]{ R.drawable.e1, R.drawable.e2, R.drawable.e3 };
	static Integer[] fDrawable = new Integer[]{ R.drawable.f1, R.drawable.f2, R.drawable.f3 };
	
	public static List<Integer[]> getDrawble(){
		List<Integer[]> list = new ArrayList<Integer[]>();
		list.add(aDrawable);
		list.add(bDrawable);
		list.add(cDrawable);
		list.add(dDrawable);
		list.add(eDrawable);
		list.add(fDrawable);
		
		return list;
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
	
	final static String[] Description = new String[]{"热门目的地", "本周特惠目的地", "这周末", "最受追捧目的地", "购物圣地", "家庭聚会"};

	public static String[] getDescription() {
		return Description;
	}
	

}
