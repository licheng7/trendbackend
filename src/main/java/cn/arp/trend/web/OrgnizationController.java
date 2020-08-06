package cn.arp.trend.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import cn.arp.trend.entity.OrgnizationArea;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.AclService;
import cn.arp.trend.service.OrgnizationAreaService;
import cn.arp.trend.service.impl.CacheUtil;
import cn.arp.trend.tools.PageParam;
import cn.arp.trend.tools.ParamUtils;
import cn.arp.trend.tools.ResponseBuilder;

@RestController
@RequestMapping("/orgs")
@RequirePermission
public class OrgnizationController extends BaseController {
	@Autowired
	private OrgnizationAreaService service;
	@Autowired
	private CacheUtil cache;

	@Autowired
	private AclService aclService;
	
	@GetMapping
	public ResponseEntity<List<OrgnizationArea>> list(@RequestParam("q") String q, PageParam param) {
		Page<OrgnizationArea> page = service.find(q, param.toPageRequest());
		return ResponseBuilder.build(page);
	}

	@GetMapping("/{orgId}")
	public OrgnizationArea find(@PathVariable("orgId") String orgId) {
		return service.find(orgId);
	}
	
	@GetMapping("/{orgId}/acls")
	public ResponseEntity<List<Acl>> listAcls(@PathVariable("orgId") String orgId,@RequestParam(name="q")String q, PageParam param){
		Page<Acl> page = aclService.findOrgAcls(orgId,q,  param.toPageRequest());
		return ResponseBuilder.build(page);
	}

	@PostMapping(params = "m=get")
	public List<OrgnizationArea> findAll(@RequestBody List<String> orgIds) {
		List<String> filtered = ParamUtils.filterNull(orgIds);
		if (filtered != null && filtered.size() > 0) {
			return service.find(filtered);
		}else{
			return null;
		}
	}

	@PostMapping
	@RequirePermission(roles="admin")
	public OrgnizationArea create(@RequestBody OrgnizationArea org) throws RestError {
		return service.create(org);
	}

	@PutMapping("/{orgId}")
	public OrgnizationArea update(@PathVariable("orgId") String orgId, @RequestBody OrgnizationArea org) {
		org.setOrgId(orgId);
		return service.update(org);
	}

	@PostMapping(params = "m=delete")
	public void removeAll(@RequestBody List<String> orgIds) {
		List<String> filtered = ParamUtils.filterNull(orgIds);
		if (filtered != null && filtered.size() > 0) {
			service.delete(filtered);
		}
	}

	@DeleteMapping("/{orgId}")
	public void remove(@PathVariable("orgId") String orgId) {
		if (StringUtils.isNotEmpty(orgId)) {
			service.delete(orgId);
		}
	}
	
	@GetMapping("/refreshCache")
	public void refreshOrgAreCache(){
		cache.refreshOrgAreaMap();
	}
}
