package cn.arp.trend.web;

import java.util.ArrayList;
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
import cn.arp.trend.data.model.MenuTo;
import cn.arp.trend.entity.Menu;
import cn.arp.trend.service.MenuService;

@RestController
@RequestMapping("/menus")
@RequirePermission(roles = "admin")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menus;
	
	@PostMapping
	public Menu create(@RequestBody MenuTo menu) {
		return menus.create(menu.toEntity());
	}

	@GetMapping
	public List<MenuTo> findAll(){
		return toMenuToList(menus.findAll());
	}
	@PutMapping("/{menuId}")
	public void update(@PathVariable("menuId") String menuId, @RequestBody MenuTo menu) {
		menus.update(menu.toEntity());
	}

	@GetMapping("/{menuId}")
	@RequirePermission
	public MenuTo find(String menuId) {
		return new MenuTo(menus.find(menuId));
	}

	@PostMapping(params = "m=get")
	@RequirePermission
	public List<MenuTo> find(@RequestBody List<String> menuIds) {
		List<Menu> list = menus.find(menuIds);
		return toMenuToList(list);
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
	public List<MenuTo> findChildren(@PathVariable("menuId")String menuId){
		return toMenuToList(menus.findChildren(menuId));
	}
	

	private List<MenuTo> toMenuToList(List<Menu> list) {
		List<MenuTo> result = new ArrayList<MenuTo>();
		if (list!=null){
			for (Menu m:list){
				result.add(new MenuTo(m));
			}
		}
		return result;
	}
}
