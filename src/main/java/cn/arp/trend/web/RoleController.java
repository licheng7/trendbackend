package cn.arp.trend.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.Audit;
import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.entity.Role;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.RoleService;
import cn.arp.trend.tools.PageParam;
import cn.arp.trend.tools.ParamUtils;
import cn.arp.trend.tools.ResponseBuilder;

@RestController
@RequestMapping("/roles")
@RequirePermission(roles="admin")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/{id}")
	public Role findOne(@PathVariable("id") Integer id) throws RestError {
		return roleService.findById(id);
	}

	@PostMapping(params = "m=get")
	public List<Role> query(@RequestBody List<Integer> ids) throws RestError {
		List<Integer> filtered = ParamUtils.filterNull(ids);
		if (filtered != null && filtered.size() > 0) {
			return roleService.findByIds(filtered);
		} else {
			return null;
		}
	}

	@GetMapping
	public ResponseEntity<List<Role>> list(@RequestParam(name = "q") String q, PageParam page) throws RestError {
		Page<Role> result = roleService.find(q, page.toPageRequest());
		return ResponseBuilder.build(result);
	}

	@PostMapping
	@Audit(desc="创建角色")
	public Role create(@RequestBody Role t) throws RestError {
		this.checkExist(t);
		t.setId(-1);
		return roleService.create(t);
	}

	@PutMapping("/{id}")
	@Audit(desc="更新角色")
	public Role update(@PathVariable("id") Integer id, @RequestBody Role t) throws RestError {
		t.setId(id);
		Role r = roleService.findById(id);
		if (r != null) {
			return roleService.update(t);
		} else {
			throw RestError.badArgument("请求更新的角色未找到(id=" + id + ")");
		}
	}

	private void checkExist(Role t) throws RestError {
		Optional<Role> old = roleService.findByName(t.getName());
		if (old.isPresent()) {
			throw RestError.badArgument("名称为" + t.getName() + "的角色已存在。");
		}
	}

	@DeleteMapping("/{id}")
	@Audit(desc="删除角色")
	public void remove(@PathVariable("id") Integer id) throws RestError {
		roleService.delete(id);
	}

	@PostMapping(params = "m=delete")
	@Audit(desc="批量删除角色")
	public void remove(@RequestBody List<Integer> ids) throws RestError {
		List<Integer> filtered = ParamUtils.filterNull(ids);
		if (filtered != null && filtered.size() > 0) {
			roleService.deleteByIds(ids);
		}
	}
}
