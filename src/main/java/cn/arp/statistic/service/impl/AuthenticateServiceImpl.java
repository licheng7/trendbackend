package cn.arp.statistic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.arp.statistic.auth.OrgPrincipal;
import cn.arp.statistic.auth.RolePrincipal;
import cn.arp.statistic.auth.UserPrincipal;
import cn.arp.statistic.auth.UserSubject;
import cn.arp.statistic.entity.Role;
import cn.arp.statistic.entity.VAcl;
import cn.arp.statistic.service.AclService;
import cn.arp.statistic.service.AuthenticateService;
import cn.arp.statistic.service.OrgnizationAreaService;
import cn.arp.statistic.service.RoleService;
import cn.vlabs.umt.oauth.UserInfo;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
	private static final String SUPER_USER = "superUser";
	private static final String ACL_KEY = "ACL";
	@Autowired
	private AclService aclService;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrgnizationAreaService orgService;

	@Override
	public boolean canAccessAll(HttpServletRequest request) {
		UserSubject subject = loadSubject(request);
		if (subject != null) {
			return subject.hasRole(SUPER_USER);
		}
		return false;
	}
	
	@Override
	public boolean canAccessHost(HttpServletRequest request, String ip) {
		UserSubject subject = loadSubject(request);
		if (subject != null&&!StringUtils.isEmpty(ip)) {
			String orgid = orgService.findOrgId(ip.substring(0,ip.lastIndexOf(".")));
			return subject.hasRole(SUPER_USER)?true:(subject.getOrgs().stream().filter(w->String.valueOf(w.getOrgId()).equals(orgid)).findAny().isPresent()&&!StringUtils.isEmpty(orgid));
		}
		return false;
	}

	@Override
	public boolean canAccessOrg(HttpServletRequest request, String orgId) {
		UserSubject subject = loadSubject(request);
		if (subject != null) {
			return subject.hasRole(SUPER_USER) || subject.canAccess(orgId);
		}
		return false;
	}

	@Override
	public boolean hasRole(HttpServletRequest request, String roleName) {
		UserSubject subject = loadSubject(request);
		if (subject != null) {
			return subject.hasRole(roleName);
		}
		return false;
	}

	@Override
	public void saveSession(HttpServletRequest request, UserInfo user) {
		UserSubject subject = new UserSubject();
		setUser(user, subject);
		addRoles(subject, user);
		addAcls(subject, user);
		HttpSession session = request.getSession();
		session.setAttribute(ACL_KEY, subject);
	}

	private void addAcls(UserSubject subject, UserInfo user) {
		List<VAcl> acls = aclService.findAcls(user.getCstnetId());
		if (acls != null) {
			List<OrgPrincipal> orgs = new ArrayList<>();
			for (VAcl acl : acls) {
				orgs.add(new OrgPrincipal(acl.getOrgId(), acl.getOrgName()));
			}
			subject.setOrgs(orgs);
		}
	}

	private void addRoles(UserSubject subject, UserInfo user) {
		List<Role> roles = roleService.findUserRoles(user.getCstnetId());
		if (roles != null) {
			List<RolePrincipal> rolePrincipals = new ArrayList<>();
			for (Role role : roles) {
				rolePrincipals.add(new RolePrincipal(role.getName(), role.getDescription()));
			}
			subject.setRoles(rolePrincipals);
		}
	}

	public UserSubject loadSubject(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserSubject subject = (UserSubject) session.getAttribute(ACL_KEY);
		return subject;
	}

	private void setUser(UserInfo user, UserSubject subject) {
		subject.setUser(new UserPrincipal(user.getCstnetId(), user.getTrueName()));
	}

	@Override
	public boolean containAtLeastOneRole(HttpServletRequest request, String[] roles) {
		UserSubject subject = loadSubject(request);
		if (subject != null) {
			for (String role : roles) {
				if (subject.hasRole(role)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasLogin(HttpServletRequest request) {
		return loadSubject(request) != null;
	}

	

}
