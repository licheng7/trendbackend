package cn.arp.trend.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParamUtils {
	public static boolean containNull(Collection<?> collection) {
		for (Object obj : collection) {
			if (obj == null) {
				return false;
			}
		}
		return true;
	}
	
	public static<T> List<T> filterNull(List<T> collection){
		if (isEmpty(collection) || !containNull(collection)) {
			return collection;
		}
		List<T> list = new ArrayList<T>();
		for (T t:collection){
			if (t!=null){
				list.add(t);
			}
		}
		return list;
	}
	
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.size() == 0);
	}
}
