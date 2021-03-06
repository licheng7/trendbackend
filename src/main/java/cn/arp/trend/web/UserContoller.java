package cn.arp.trend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.trend.auth.RequirePermission;
import cn.arp.trend.entity.VAcl;
import cn.arp.trend.service.AclService;
import cn.arp.trend.tools.PageParam;
import cn.arp.trend.tools.ResponseBuilder;

@RestController
@RequestMapping("/users")
@RequirePermission(roles="admin")
public class UserContoller {
	@Autowired
	private AclService aclService;
	@GetMapping("/{userId}/acls")
	public ResponseEntity<List<VAcl>> findUserAcls(@PathVariable("userId")String userId,@RequestParam(name="q")String q, PageParam param){
		Page <VAcl> page = aclService.findUserAcls(userId,q, param.toPageRequest());
		return ResponseBuilder.build(page);
	}
}
