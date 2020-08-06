package cn.arp.trend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.entity.Menu;
import cn.arp.trend.service.MenuService;

@RestController
@RequestMapping("/menus")
@RequirePermission(roles = "admin")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menus;
	
	@PostMapping
	public Menu create(@RequestBody Menu menu) {
		return menus.create(menu);
	}

	@PutMapping("/{menuId}")
	public void update(@PathVariable("menuId") String menuId, @RequestBody Menu menu) {
		menus.update(menu);
	}

	@GetMapping("/{menuId}")
	@RequirePermission
	public Menu find(String menuId) {
		return menus.find(menuId);
	}

	@PostMapping(params = "m=get")
	@RequirePermission
	public List<Menu> find(@RequestBody List<String> menuIds) {
		return menus.find(menuIds);
	}

	@DeleteMapping("/{menuId}")
	public void delete(@PathVariable("menuId") String menuId) {
		menus.remove(menuId);
	}

	@PostMapping(params = "m=delete")
	public void delete(@RequestBody List<String> menuIds) {
		menus.remove(menuIds);
	}
	
	@GetMapping("/{menuId}/children")
	@RequirePermission
	public List<Menu> findChildren(@PathVariable("menuId")String menuId){
		return menus.findChildren(menuId);
	}
}
