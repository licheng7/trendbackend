package cn.arp.trend.service;

import java.util.List;

import cn.arp.trend.entity.Menu;

public interface MenuRoleService {
	void updateMenuRoleMapping(String roleId, List<String> menuIds, String createUser);

	List<Menu> findRoleMenus(List<String> roleIds);

	List<String> findRoleMenus(String roleId);
}