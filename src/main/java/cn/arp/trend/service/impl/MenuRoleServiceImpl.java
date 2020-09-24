package cn.arp.trend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.arp.trend.entity.Menu;
import cn.arp.trend.entity.MenuRoleRelation;
import cn.arp.trend.repository.MenuRoleDAO;
import cn.arp.trend.service.EventListener;
import cn.arp.trend.service.EventService;
import cn.arp.trend.service.MenuRoleService;
import cn.arp.trend.tools.ParamUtils;
import cn.arp.trend.tools.UUIDGenerator;

@Service
public class MenuRoleServiceImpl implements MenuRoleService {

	@Autowired
	private MenuRoleDAO dao;
	@Autowired
	private EventService events;

	@PostConstruct
	public void init() {
		events.addListener("DeleteMenu", new EventListener() {
			@Override
			public void actionPerformed(Object param) {
				dao.deleteByMenuId((String) param);
			}
		});
		events.addListener("DeleteMenus", new EventListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(Object param) {
				dao.deleteByMenuIds((List<String>) param);
			}
		});
		events.addListener("DeleteRole", new EventListener() {
			@Override
			public void actionPerformed(Object param) {
				dao.deleteByRoleId((Integer) param);
			}
		});
		events.addListener("DeleteRoles", new EventListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(Object param) {
				dao.deleteByRoleIds((List<String>) param);
			}
		});
	}

	@Override
	@Transactional
	public void updateMenuRoleMapping(Integer roleId, List<String> menuIds, String createUser) {
		dao.deleteByRoleId(roleId);
		List<MenuRoleRelation> list = new ArrayList<MenuRoleRelation>();
		if (menuIds != null) {
			for (String menuId : menuIds) {
				MenuRoleRelation mr = new MenuRoleRelation();
				mr.setId(UUIDGenerator.generateId());
				mr.setRoleId(roleId);
				mr.setCreateTime(new Date());
				mr.setCreateUserid(createUser);
				mr.setMenuId(menuId);
				list.add(mr);
			}
		}
		dao.saveAll(list);
	}

	@Override
	public List<Menu> findRoleMenus(List<Integer> roleIds) {
		List<Integer> filtered = ParamUtils.filterNull(roleIds);
		if (filtered!=null && filtered.size()>0){
			return dao.findRoleMenus(filtered);
		}else{
			return null;
		}
	}

	@Override
	public List<String> findRoleMenus(Integer roleId) {
		assert roleId!=null : "输入的角色Id不能为空";
		List<MenuRoleRelation> list = dao.findByRoleId(roleId);
		ArrayList<String> menuIds = new ArrayList<String>();
		for (MenuRoleRelation m :list){
			menuIds.add(m.getMenuId());
		}
		return menuIds;
	}

	@Override
	public int countCanAccessMenus(List<Integer> roleIds, Set<String> urls) {
		return dao.count(roleIds, urls);
	}

}
