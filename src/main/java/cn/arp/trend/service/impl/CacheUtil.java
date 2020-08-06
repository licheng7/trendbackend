package cn.arp.trend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.arp.trend.entity.OrgnizationArea;
import cn.arp.trend.repository.OrgnizationAreaDAO;

@Service
public class CacheUtil {
	@Autowired
	private OrgnizationAreaDAO orgAreaDao;
	
	@Cacheable("orgArea")
	public HashMap<String, String> getOrgAreaMap(){
		List<OrgnizationArea> orgAreaLists = this.orgAreaDao.findAll();
		return (HashMap<String, String>) orgAreaLists.stream().collect(Collectors.toMap(OrgnizationArea::getIp, OrgnizationArea::getOrgId));
	}
	
	@CachePut("orgArea")
	public HashMap<String, String> refreshOrgAreaMap(){
		List<OrgnizationArea> orgAreaLists = this.orgAreaDao.findAll();
		return (HashMap<String, String>) orgAreaLists.stream().collect(Collectors.toMap(OrgnizationArea::getIp, OrgnizationArea::getOrgId));
	}
}
