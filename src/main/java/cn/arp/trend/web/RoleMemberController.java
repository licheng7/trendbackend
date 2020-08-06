package cn.arp.trend.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.entity.RoleMember;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.RoleService;
import cn.arp.trend.tools.PageParam;
import cn.arp.trend.tools.ResponseBuilder;

@RestController
@RequestMapping("/roles/{roleId}/members")
@RequirePermission(roles="admin")
public class RoleMemberController extends BaseController {
	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<List<String>> list(@PathVariable("roleId") int roleId, @RequestParam("q") String q,
			PageParam page) {
		Page<RoleMember> members = roleService.findMembers(roleId, q, page.toPageRequest());
		List<String> userids = new ArrayList<>();
		for (RoleMember memberShip : members) {
			userids.add(memberShip.getUserId());
		}
		return ResponseBuilder.build(userids, members.getTotalElements());
	}

	@PostMapping
	public void addMembers(@PathVariable("roleId") int roleId, @RequestBody List<String> userIds) throws RestError {
		roleService.addMembers(roleId, userIds);
	}

	@DeleteMapping(params="userId")
	public void removeMemer(@PathVariable("roleId") int roleId, @RequestParam("userId") String userId) {
		roleService.removeMemeber(roleId, userId);
	}

	@DeleteMapping
	public void removeAllmembers(@PathVariable("roleId")int roleId){
		roleService.removeAllMembers(roleId);
	}
	@PostMapping(params = "m=delete")
	public void removeMembers(@PathVariable("roleId") int roleId, @RequestBody List<String> userIds) {
		roleService.removeMembers(roleId, userIds);
	}
}
