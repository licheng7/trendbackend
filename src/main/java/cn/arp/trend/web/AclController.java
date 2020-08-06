package cn.arp.trend.web;

import java.util.List;

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

import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.entity.Acl;
import cn.arp.trend.entity.VAcl;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.AclService;
import cn.arp.trend.tools.PageParam;
import cn.arp.trend.tools.ParamUtils;
import cn.arp.trend.tools.ResponseBuilder;

@RestController
@RequestMapping("/acls")
@RequirePermission(roles="admin")
public class AclController extends BaseController {
	@Autowired
	private AclService aclService;

	@PostMapping
	public Acl create(@RequestBody Acl t) throws RestError {
		checkExist(t);
		t.setId(-1);
		return aclService.save(t);
	}

	@GetMapping("/{id}")
	public Acl findOne(@PathVariable("id") Integer id) throws RestError {
		return aclService.find(id);
	}

	@GetMapping
	public ResponseEntity<List<VAcl>> list(@RequestParam(name = "q") String q, PageParam page) throws RestError {
		Page<VAcl> result = aclService.find(q, page.toPageRequest());
		return ResponseBuilder.build(result);
	}

	@PostMapping(params = "m=get")
	public List<Acl> query(@RequestBody List<Integer> ids) throws RestError {
		List<Integer> filtered = ParamUtils.filterNull(ids);
		if (filtered != null && filtered.size() > 0) {
			return aclService.findAll(ids);
		} else {
			return null;
		}
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable("id") Integer id) throws RestError {
		aclService.remove(id);
	}

	@PostMapping(params = "m=delete")
	public void remove(@RequestBody List<Integer> ids) throws RestError {
		List<Integer> filtered = ParamUtils.filterNull(ids);
		if (filtered != null && filtered.size() > 0) {
			aclService.remove(ids);
		}
	}

	@PutMapping("/{id}")
	public Acl update(@PathVariable("id") Integer id, @RequestBody Acl t) throws RestError {
		if (id.equals(t.getId())) {
			checkExist(t);
			return aclService.save(t);
		} else {
			throw RestError.badArgument("更新的内容中的ID必须与路径上的ID保持一致。");
		}
	}

	private void checkExist(Acl t) throws RestError {
		Acl old = aclService.find(t.getUserId(), t.getOrgId());
		if (old!=null){
			throw RestError.badArgument("用户"+t.getUserId()+"已被授权可以访问"+t.getOrgId());
		}
	}

}
