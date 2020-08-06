package cn.arp.statistic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.arp.statistic.entity.Acl;
import cn.arp.statistic.entity.VAcl;

public interface AclService {
	List<VAcl> findAcls(String userId);
	Acl save(Acl acl);
	Acl find(String userId, String orgId);
	Acl find(Integer id);
	List<Acl> findAll(List<Integer> ids);
	Page<VAcl> find(String q, Pageable page);
	void remove(Collection<Integer> ids);
	void remove(Integer id);
	Page<Acl> findOrgAcls(String orgId, String q, Pageable page);
	Page<VAcl> findUserAcls(String userId, String q, Pageable page);
}
