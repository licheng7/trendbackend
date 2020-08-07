package cn.arp.trend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.arp.trend.entity.Menu;
import cn.arp.trend.repository.MenuDAO;
import cn.arp.trend.service.EventService;
import cn.arp.trend.service.MenuService;
import cn.arp.trend.tools.ParamUtils;
import cn.arp.trend.tools.UUIDGenerator;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDao;
	
	@Autowired
	private EventService events;

	@Override
	public Menu find(String id) {
		Optional<Menu> m = menuDao.findById(id);
		if (m.isPresent()) {
			return m.get();
		} else {
			return null;
		}
	}

	@Override
	public Menu create(Menu menu) {
		Date now = new Date();
		menu.setCreateTime(now);
		menu.setUpdateTime(now);
		menu.setId(UUIDGenerator.generateId());
		Optional<Menu> fatherMenu = menuDao.findById(menu.getParentId());
		if (!fatherMenu.isPresent()) {
			throw new IllegalArgumentException("ParentMenu can't be found.");
		}
		menu.setMenuLevel(fatherMenu.get().getMenuLevel() + 1);
		menu.setMenuSeq(fatherMenu.get().getMenuSeq() + "." + menu.getId());
		return menuDao.save(menu);
	}

	@Override
	public Menu update(Menu menu) {
		if (StringUtils.isEmpty(menu.getId())) {
			throw new IllegalArgumentException("更新菜单内容时，必须提供菜单ID");
		}
		return menuDao.save(menu);
	}

	@Override
	@Transactional
	public void remove(String id) {
		if (StringUtils.isNotEmpty(id)) {
			menuDao.deleteById(id);
			events.fireEvent("DeleteMenu", id);
		}
	}

	@Override
	@Transactional
	public void remove(List<String> ids) {
		List<String> filtered = ParamUtils.filterNull(ids);
		if (filtered != null && !filtered.isEmpty()) {
			menuDao.deleteByIds(filtered);
			events.fireEvent("DeleteMenus", filtered);
		}
	}

	@Override
	public List<Menu> find(List<String> ids) {
		List<String> filtered = ParamUtils.filterNull(ids);
		if (filtered != null && !filtered.isEmpty()) {
			return menuDao.findAllById(filtered);
		} else {
			return null;
		}
	}

	@Override
	public List<Menu> findChildren(String menuId) {
		assert menuId!=null && menuId.length()>0:"父菜单的ID不能为空";
		return menuDao.findByParentId(menuId);
	}

	@Override
	public List<Menu> findAll() {
		return menuDao.findAll();
	}

}
