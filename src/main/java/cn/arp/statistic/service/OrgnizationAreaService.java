package cn.arp.statistic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.arp.statistic.entity.OrgnizationArea;
import cn.arp.statistic.error.RestError;

public interface OrgnizationAreaService {
	Page<OrgnizationArea> find(String q, Pageable page);

	OrgnizationArea create(OrgnizationArea org) throws RestError;

	OrgnizationArea update(OrgnizationArea org);

	OrgnizationArea find(String orgId);

	void delete(String orgId);

	void delete(List<String> orgIds);

	List<OrgnizationArea> find(List<String> orgIds);
	
	String findOrgId(String ip);
}
