package cn.arp.trend.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.CurrentSession;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.auth.UserSubject;
import cn.arp.trend.data.model.LiteMenu;
import cn.arp.trend.entity.Menu;
import cn.arp.trend.service.MenuRoleService;

@RestController
@RequestMapping("/profile")
@RequirePermission
public class ProfileContoller {
	@Autowired
	private MenuRoleService mrService;

	@GetMapping("/mine")
	public UserSubject mine() {
		return CurrentSession.getSubject();
	}

	@GetMapping("/menus")
	public List<LiteMenu> myMenus() {
		List<Menu> all = mrService.findRoleMenus(CurrentSession.getRoleIds());
		List<LiteMenu> list = new ArrayList<>();
		if (all!=null){
			for (Menu m :all){
				list.add(new LiteMenu(m));
			}
		}
		return list;
	}
}
