package cn.arp.statistic.tools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.alibaba.druid.util.StringUtils;
/**
 * 
 * @author xxj
 * 对排序字段进行解析，分页查询排序sort=字段,-字段
 * 字段名称前无-号升序，-降序
 *
 */
public class SortParamParser {
	public static List<Order> parse(String str) {
		List<Order> list = new ArrayList<Order>();
		if (!StringUtils.isEmpty(str)) {
			String[] paramStr = str.split(",");
			for (String p : paramStr) {
				Order param = parseOne(p);
				if (param != null) {
					list.add(param);
				}
			}
		}
		return list;
	}
	
	private static Order parseOne(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		Direction direction;
		String key ;
		if (str.startsWith("-")) {
			direction = Direction.DESC;
			key = str.substring(1);
		}else if (str.startsWith("+")){
			direction = Direction.ASC;
			key = str.substring(1);
		}else {
			direction = Direction.ASC;
			key = str;
		}
		return new Order(direction, key);
	}
}
