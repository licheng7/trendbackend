package cn.arp.trend.service;

import java.util.List;

import cn.arp.trend.entity.Menu;

/**
 * 菜单服务
 * @author xiejj@cnic.cn
 *
 */
public interface MenuService {
	/**
	 * 查找一个菜单项的具体内容
	 * @param id
	 * @return
	 */
	Menu find(String id);
	/**
	 * 创建一个菜单
	 * @param menu
	 * @return
	 */
	Menu create(Menu menu);
	/**
	 * 更新一个菜单
	 * @param menu
	 * @return
	 */
	Menu update(Menu menu);
	/**
	 * 删除一个菜单
	 * @param id
	 */
	void remove(String id);
	/**
	 * 删除一批菜单
	 * @param ids
	 */
	void remove(List<String> ids);
	/**
	 * 查找一批菜单
	 * @param ids
	 * @return
	 */
	List<Menu> find(List<String> ids);
	/**
	 * 查找当前菜单的子菜单
	 * @param menuId
	 * @return
	 */
	List<Menu> findChildren(String menuId);
	
}
