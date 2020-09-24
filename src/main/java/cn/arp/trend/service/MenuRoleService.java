package cn.arp.trend.service;

import java.util.List;
import java.util.Set;

import cn.arp.trend.entity.Menu;

public interface MenuRoleService {
	void updateMenuRoleMapping(Integer roleId, List<String> menuIds, String createUser);

	List<Menu> findRoleMenus(List<Integer> roleIds);

	List<String> findRoleMenus(Integer roleId);

	int countCanAccessMenus(List<Integer> roleIds, Set<String> urls);
}