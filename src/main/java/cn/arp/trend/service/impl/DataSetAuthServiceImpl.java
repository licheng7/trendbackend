package cn.arp.trend.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.arp.trend.service.DataSetAuthService;
import cn.arp.trend.service.MenuRoleService;
import cn.arp.trend.tools.IOUtils;

@Service
public class DataSetAuthServiceImpl implements DataSetAuthService {
	private Map<String, Set<String>> datasetToMenuUrl = new HashMap<>();
	private static Logger log = Logger.getLogger(DataSetAuthService.class);
	@Autowired
	private MenuRoleService menuService;
	public DataSetAuthServiceImpl() throws IOException{
		
		String json = IOUtils.readFromClassPath("menus.json", getClass().getClassLoader(), "UTF-8");
		MenuMappingFileParser file =new MenuMappingFileParser();
		datasetToMenuUrl = file.doFinal(json);
		log.info("Total "+ datasetToMenuUrl.size()+" dataset mapping found.");
	}
	
	@Override
	public boolean canAccess(List<Integer> roleIds, String dataset) {
		if (dataset==null || roleIds==null || roleIds.size()==0){
			return false;
		}
		
		Set<String> urls = datasetToMenuUrl.get(dataset);
		if (urls==null){
			return false;
		}
		
		return menuService.countCanAccessMenus(roleIds, urls)>0;
	}

}
