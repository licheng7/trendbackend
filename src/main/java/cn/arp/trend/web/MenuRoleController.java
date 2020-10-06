package cn.arp.trend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.CurrentSession;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.service.MenuRoleService;

@RestController
@RequirePermission
@RequestMapping("/roles/{roleId}/menus")
public class MenuRoleController {
	@Autowired
	private MenuRoleService menuRoles;
	
	@GetMapping
	public List<String> findAccessibleMenus(@PathVariable("roleId")Integer roleId){
		return menuRoles.findRoleMenus(roleId);
	}
	
	@PutMapping
	@RequirePermission(roles="admin")
	@Audit(desc="配置角色可访问菜单")
	public void changeAccessibleMenus(@PathVariable("roleId")Integer roleId, @RequestBody List<String> menuIds){
		menuRoles.updateMenuRoleMapping(roleId, menuIds, CurrentSession.getUserId());
	}
}
