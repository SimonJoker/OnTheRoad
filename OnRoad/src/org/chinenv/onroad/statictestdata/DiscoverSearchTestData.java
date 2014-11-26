package org.chinenv.onroad.statictestdata;

import java.util.ArrayList;
import java.util.List;

public class DiscoverSearchTestData {
	
	static List<String> searchPlaces = new ArrayList<String>();

	public static List<String> getSearchPlaces() {
		return searchPlaces;
	}

	public static void setSearchPlaces(String Places) {
		searchPlaces.add(Places);
	}
	
	
	public static void removeItem(int postion){
		searchPlaces.remove(postion);
	}
}
