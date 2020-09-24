package cn.arp.trend.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MenuMappingFileParser {
	private Map<String, Set<String>> map = new HashMap<>();

	public Map<String, Set<String>> doFinal(String json) {
		JSONArray arrays = (JSONArray) JSONObject.parse(json);
		for (Object obj : arrays) {
			parseMenuObject((JSONObject) obj);
		}
		return map;
	}

	private void parseMenuObject(JSONObject menuMapping) {
		String menuUrl = menuMapping.getString("url");
		for (Object setname : menuMapping.getJSONArray("datasets")) {
			addToMap(menuUrl, (String) setname);
		}
	}

	private void addToMap(String menuUrl, String setname) {
		Set<String> set = map.get(setname);
		if (set == null) {
			set = new HashSet<String>();
			map.put((String) setname, set);
		}
		set.add(menuUrl);
	}
}
